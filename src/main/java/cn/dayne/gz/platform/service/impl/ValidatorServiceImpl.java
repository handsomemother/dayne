package cn.dayne.gz.platform.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.dayne.gz.platform.dto.DataDTO;
import cn.dayne.gz.platform.dto.SourceDTO;
import cn.dayne.gz.platform.email.MailSenderInfo;
import cn.dayne.gz.platform.email.SimpleMailSender;
import cn.dayne.gz.platform.entity.Contacter;
import cn.dayne.gz.platform.entity.EmailConfig;
import cn.dayne.gz.platform.entity.Source;
import cn.dayne.gz.platform.entity.SourceLog;
import cn.dayne.gz.platform.service.ContactGroupService;
import cn.dayne.gz.platform.service.EmailConfigService;
import cn.dayne.gz.platform.service.SourceLogService;
import cn.dayne.gz.platform.service.SourceService;
import cn.dayne.gz.platform.service.ValidatorService;
import cn.dayne.gz.platform.util.AppContextUtil;
import cn.dayne.gz.platform.util.BeanCopyUtil;
import cn.dayne.gz.platform.util.CommonConstant;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 接口校验核心逻辑
 * 
 * FIXME：
 * 因该项目周期太短，而且定位为展示与基本检测
 * 所以把邮件逻辑先写死，待扩展
 * 
 * @author 
 * @date 2012-3-12
 */
@Service("validatorService")
public class ValidatorServiceImpl implements ValidatorService {
	
	private static final String CONTENT_TYPE = "application/x-www-form-urlencoded; charset=utf-8";
	
	private final Logger log = Logger.getLogger(ValidatorServiceImpl.class);
	
	/**
	 * 数据源服务
	 */
	@Resource
	private SourceService sourceSerivce;
	
	/**
	 * 日志记录服务
	 */
	@Resource
	private SourceLogService sourceLogSerivce;
	
	/**
	 * 联系人组服务
	 */
	@Resource
	private ContactGroupService contactGroupService;
	
	/* (non-Javadoc)
	 * @see cn.ac.iscas.gz.service.ValidatorService#toCheckAndSendEmail(cn.ac.iscas.gz.entity.Source)
	 */
	@Override
	public void toCheckAndSendEmail(Source source) {
		
		//创建httpclient
		HttpClient httpClient = new HttpClient();
		
		//设置超时
		//httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);  
		
		//开始时间
		long startTime = System.currentTimeMillis();
		
		//新建监控日志类
		SourceLog sourceLog = buildSourceLog(source);
		sourceLog.setStartTime(new Date(startTime));
		
		try{
			if(CommonConstant.REQUEST_TYPE_POST.equals(source.getRequestType())){
				postHandle(httpClient, source, sourceLog, startTime);
			}else{
				getHandle(httpClient, source, sourceLog, startTime);
			}
		}catch(UnknownHostException e){
			Exception ex = new Exception("无法解析地址 " + e.getMessage()+",请确认该服务器能否正常访问");
			exceptionHandler(source, startTime, sourceLog, ex);
			log.error(e);
		}catch (Exception e) {
			exceptionHandler(source, startTime, sourceLog, e);
			log.error(e);
		}finally{
			//最后更新数据源
			sourceSerivce.update(source);
			//保存记录
			sourceLogSerivce.submit(sourceLog);
		}
	}

	/**
	 * 错误处理
	 * 
	 * @param source
	 * @param startTime
	 * @param sourceLog
	 * @param e
	 */
	private void exceptionHandler(Source source, long startTime,
			SourceLog sourceLog, Exception e) {
		//判断是否应该发送邮件
		if(isSend(source)){
			//邮箱发送地址
			String toAddress = getAddress(source);
			if(!"".equals(toAddress)){
				//邮件标题
				String title = "数据源："+source.getSourceName()+" 出现异常";
				//邮件内容
				String content = buildExceptionInfo(source,e);
				//发送邮件
				boolean isSended = sendEmail(title,content,toAddress);
				
				log.info("------发送邮件开始--------");
				if(isSended){
					//记录发送邮件时间
					source.setLastSendEmailTime(new Date());
					log.info("发送邮件：" + title +" 邮件发送地址："+toAddress);
				}else{
					log.error("发送邮件失败");
				}
				log.info("------发送邮件结束--------");
			}else{
				log.warn("数据源：["+source.getSourceName()+"]发生错误时，邮件发送地址为空");
			}
		}
		//设置日志信息，更新数据源信息
		setLogInfoAndUpdateSourceInfo(source,sourceLog,startTime,CommonConstant.STATUS_UNUSUAL,"异常信息："+e.getMessage());
	}

	/**
	 * 对get类型请求的处理逻辑
	 * 
	 * @param httpClient
	 * @param source
	 * @param sourceLog
	 * @param startTime
	 * @throws IOException
	 * @throws HttpException
	 */
	private void getHandle(HttpClient httpClient, Source source,
			SourceLog sourceLog, long startTime) throws IOException,
			HttpException {
		GetMethod getMethod = new GetMethod(source.getUrlAddress());
		
		getMethod.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=utf-8");
		
		httpClient.executeMethod(getMethod);
		
		//主要校验逻辑
		baseValidator(source,sourceLog,getMethod,startTime);
	}

	/**
	 * 对post类型请求的处理逻辑
	 * 
	 * @param httpClient
	 * @param source
	 * @param sourceLog
	 * @param startTime
	 * @throws IOException
	 * @throws HttpException
	 */
	private void postHandle(HttpClient httpClient, Source source,
			SourceLog sourceLog, long startTime) throws IOException,
			HttpException {
		PostMethod postMethod = new PostMethod(source.getUrlAddress());
		postMethod.setRequestHeader("Content-Type",	CONTENT_TYPE);
		
		//如果存在参数则设置参数
		if(!StringUtils.isBlank(source.getParam())){
			NameValuePair[]nvps = getParams(source.getParam());
			postMethod.addParameters(nvps);
		}
		//模拟请求
		httpClient.executeMethod(postMethod);
		
		//主要校验逻辑
		baseValidator(source,sourceLog,postMethod,startTime);
	}
	
	
	/**
	 * 核心校验逻辑
	 * 
	 * @param source
	 * @param sourceLog
	 * @param method
	 * @param startTime
	 */
	private void baseValidator(Source source,SourceLog sourceLog,HttpMethodBase method,long startTime){
		if (method.getStatusCode() == HttpStatus.SC_OK) {
			//若接口为电信接口，需要进一步判断
			if(CommonConstant.TYPE_TELECOM == source.getType()){
				telcomCkeck(method);
			}
			//设置日志信息，更新数据源信息
			setLogInfoAndUpdateSourceInfo(source,sourceLog,startTime,CommonConstant.STATUS_NORMAL,"正常,请求状态码为:"+method.getStatusCode());
		} else {
			throw new RuntimeException("状态码异常 "+method.getStatusCode());
		}
	}
	
	
	
	
	/**
	 * 构建邮件发送内容
	 * 
	 * FIXME:时间不够，暂时写死，以后使用模板方式
	 * 
	 * @param source
	 * @param e
	 * @return
	 */
	private String buildExceptionInfo(Source source,Exception e){
		
		SourceDTO sourceDTO = BeanCopyUtil.copy(source, SourceDTO.class);
		StringBuilder contentBuilder = new StringBuilder();
		contentBuilder.append("监控源名称："+sourceDTO.getSourceName());
		contentBuilder.append("\n监控地址："+sourceDTO.getUrlAddress());
		contentBuilder.append("\n监控源类型："+sourceDTO.getDisplayType());
		contentBuilder.append("\n监控源所属系统："+sourceDTO.getDisplaySys());
		contentBuilder.append("\n异常信息："+e.getMessage());
		
		return contentBuilder.toString();
	}

	/**
	 * 电信接口校验
	 * 
	 * 电信接口比较特殊，在此单独校验
	 * 
	 * @param postMethod
	 */
	private void telcomCkeck(HttpMethodBase method) {
		try{
			ObjectMapper mapper = new ObjectMapper();
			String response = inputStream2String(method.getResponseBodyAsStream());
			DataDTO dto = mapper.readValue(response, DataDTO.class);
			if(!CommonConstant.TELECOM_NORMAL_RESULT.equals(dto.getCode())){
				throw new RuntimeException("异常状态码 " + dto.getCode()+"，如对异常码不清楚请查阅《电信数据交互接口定义文档》");
			}
		}catch(Exception e){
			throw new RuntimeException("电信接口异常，"+ e.getMessage() );
		}
	}
	
	/**
	 * 获取参数并且构建参数数组
	 * 
	 * @param param 数据源记录的参数，形式如：name = admin ; password = 123456
	 * @return
	 */
	private NameValuePair[] getParams(String param){
		String[]p = param.split(";");
		List<NameValuePair> pList = new ArrayList<NameValuePair>();
		for(String temp:p){
			if(!temp.contains("=")){
				throw new RuntimeException("参数格式有错,正常格式如：a=1");
			}
			String[]args = temp.split("=");
			pList.add(new NameValuePair(args[0],args[1]));
		}
		return pList.toArray(new NameValuePair[pList.size()]);
	}
	
	/**
	 * 输入流转字符串
	 * 
	 * @param is
	 * @return
	 * @throws IOException
	 */
	private String inputStream2String(InputStream is) throws IOException{
	    BufferedReader in = new BufferedReader(new InputStreamReader(is,"UTF-8"));
	    StringBuffer buffer = new StringBuffer();
	    String line = "";
	    while ((line = in.readLine()) != null){
	      buffer.append(line);
	    }
	    return buffer.toString();
	}
	
	/**
	 * 获取邮件发送地址
	 * 
	 * @param source 数据源
	 * @return
	 */
	private String getAddress(Source source){
		String toAddress = "";
		for(Contacter c : contactGroupService.getSelectedContacters(source.getContactGroup().getId())){
			toAddress = toAddress + c.getEmail() +";";
		}
		return toAddress;
	}
	
	/**
	 * 根据数据源信息创建日志
	 * 
	 * @param source 数据源
	 * @return
	 */
	private SourceLog buildSourceLog(Source source){
		SourceLog sourceLog = BeanCopyUtil.copy(source, SourceLog.class);
		
		sourceLog.setId(0);
		
//		sourceLog.setSourceName(source.getSourceName());
//		sourceLog.setMonitorLevel(source.getMonitorLevel());
//		sourceLog.setSysCode(source.getSysCode());
//		sourceLog.setType(source.getType());
//		sourceLog.setUrlAddress(source.getUrlAddress());
//		sourceLog.setResponseTime(source.getResponseTime());
		
		return sourceLog;
	}
	
	/**
	 * 更新数据源信息，设置日志信息
	 * 
	 * @param source 数据源
	 * @param sourceLog 日志
	 * @param startTime 开始执行时间
	 * @param status 接口状态
	 * @param logResultMessage 日志结果描述
	 */
	private void setLogInfoAndUpdateSourceInfo(Source source,SourceLog sourceLog,long startTime,int status,String logResultMessage){
		//更新数据源信息
		long endTime =  System.currentTimeMillis();
		float useTime = (endTime - startTime)/1000f;
		source.setRecentlyResponseTime(useTime);
		source.setStatus(status);
		
		//日志设置
		sourceLog.setEndTime(new Date(endTime));
		sourceLog.setRecentlyResponseTime(useTime);
		sourceLog.setStatus(status);
		sourceLog.setResult(logResultMessage);
		sourceLog.setLogTime(new Date());
		sourceLog.setSourceId(source.getId());
	}
	
	/**
	 * 判读是否发送邮件
	 * 
	 * @param source 数据源
	 * @return
	 */
	private boolean isSend(Source source){
		Date lastSendTime = source.getLastSendEmailTime();
		//等于空时，相当于没有发送过
		if(lastSendTime == null){
			return true;
		}
		float interval = (System.currentTimeMillis() - lastSendTime.getTime())/(1000f*60*60);
		if(interval >= source.getEmailInterval()){
			return true;
		}
		return false;
	}
	
	/**
	 * 发送邮件
	 * 
	 * @param title 邮件标题
	 * @param content 邮件内容
	 * @param toAddress 发送地址
	 */
	private boolean sendEmail(String title,String content,String toAddress){
		//获取邮件服务
		EmailConfigService emailConfigService = (EmailConfigService) AppContextUtil.getBean("emailConfigService");
		EmailConfig config = emailConfigService.getEmailConfig();
		
		//邮件配置不能为空
		Assert.assertNotNull(config);
		
		// 这个类主要是设置邮件
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost(config.getMailServerHost());
		mailInfo.setMailServerPort(config.getMailServerPort());
		mailInfo.setValidate(true);
		mailInfo.setUserName(config.getSysEmailAddress());
		mailInfo.setPassword(config.getEmailPassWord());// 您的邮箱密码
		mailInfo.setFromAddress(config.getSysEmailAddress());
		mailInfo.setToAddress(toAddress);
		//抄送，暂时不需要
		//mailInfo.setCcAddress("214223894@qq.com");
		
		mailInfo.setSubject(title);
		mailInfo.setContent(content);
		// 这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		boolean isSended = sms.sendTextMail(mailInfo);// 发送文体格式
		//sms.sendHtmlMail(mailInfo);// 发送html格式
		
		return isSended;
	}
	

	/* (non-Javadoc)
	 * @see cn.ac.iscas.gz.service.ValidatorService#ckeck(cn.ac.iscas.gz.entity.Source)
	 */
	@Override
	public void ckeck(Source source) {
		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(source.getUrlAddress());
		postMethod.setRequestHeader("Content-Type",	CONTENT_TYPE);
				
		//开始时间
		long startTime = System.currentTimeMillis();
		
		try{
			//如果存在参数则设置参数
			if(!StringUtils.isBlank(source.getParam())){
				NameValuePair[]nvps = getParams(source.getParam());
				postMethod.addParameters(nvps);
			}
			
			//模拟请求
			httpClient.executeMethod(postMethod);
			
			if (postMethod.getStatusCode() == HttpStatus.SC_OK) {
				//若接口为电信接口，需要进一步判断
				if(CommonConstant.TYPE_TELECOM == source.getType()){
					telcomCkeck(postMethod);
				}
				//设置日志信息，更新数据源信息
				updateSourceInfo(source,startTime,CommonConstant.STATUS_NORMAL);
			} else {
				throw new RuntimeException();
			}
		}catch (Exception e) {
			//设置日志信息，更新数据源信息
			updateSourceInfo(source,startTime,CommonConstant.STATUS_UNUSUAL);
			log.error(e);
		}finally{
			//最后更新数据源
			sourceSerivce.update(source);
		}
	}
	
	/**
	 * 更新数据源信息
	 * 
	 * @param source 数据源
	 * @param startTime 开始执行时间
	 * @param status 接口状态
	 */
	private void updateSourceInfo(Source source,long startTime,int status){
		//更新数据源信息
		long endTime =  System.currentTimeMillis();
		float useTime = (endTime - startTime)/1000f;
		source.setRecentlyResponseTime(useTime);
		source.setStatus(status);
	}

}
