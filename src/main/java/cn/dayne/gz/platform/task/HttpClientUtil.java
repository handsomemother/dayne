package cn.dayne.gz.platform.task;

import java.util.Date;

import junit.framework.Assert;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;

import cn.dayne.gz.platform.email.MailSenderInfo;
import cn.dayne.gz.platform.email.SimpleMailSender;
import cn.dayne.gz.platform.entity.EmailConfig;
import cn.dayne.gz.platform.entity.Source;
import cn.dayne.gz.platform.service.EmailConfigService;
import cn.dayne.gz.platform.service.SourceService;
import cn.dayne.gz.platform.util.AppContextUtil;


/**
 * 模拟电信请求，数据接口
 * 
 * @author yeqiuming
 * @date 2012-10-25
 *
 */
public class HttpClientUtil {
	
	private static final String CONTENT_TYPE = "application/x-www-form-urlencoded; charset=utf-8";
	
	private final Logger log = Logger.getLogger(HttpClientUtil.class);
	
	public void toCheckAndSendEmail(SourceService sourceSerivce,Source source,String toAddress) throws Exception{
		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(source.getUrlAddress());
		postMethod.setRequestHeader("Content-Type",	CONTENT_TYPE);
		long startTime = System.currentTimeMillis();
		try{
			httpClient.executeMethod(postMethod);
		}catch (Exception e) {
			if(isSend(source)){
				String title = "数据源"+source.getSourceName()+" 出现异常";
				String content = "数据源"+source.getSourceName()+" 出现异常，异常信息："+e.getMessage();
				sendEmail(title,content,toAddress);
				source.setLastSendEmailTime(new Date());
				sourceSerivce.update(source);
				System.out.println("is send");
			}
			throw e;
		}
		
		if (postMethod.getStatusCode() == HttpStatus.SC_OK) {
			long endTime =  System.currentTimeMillis();
			float useTime = (endTime - startTime)/1000f;
			source.setRecentlyResponseTime(useTime);
			sourceSerivce.update(source);
			log.info("成功,使用时间:"+useTime+"s");
		} else {
			log.error("请求失败");
			if(isSend(source)){
				String title = "数据源"+source.getSourceName()+" 出现异常";
				String content = "数据源"+source.getSourceName()+" 出现异常，返回StatusCode为："+postMethod.getStatusCode();
				sendEmail(title,content,toAddress);
				
				source.setLastSendEmailTime(new Date());
				sourceSerivce.update(source);
				
				System.out.println("is send");
			}
		}
	}
	
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
	
	private void sendEmail(String title,String content,String toAddress){
		EmailConfigService emailConfigService = (EmailConfigService) AppContextUtil.getBean("emailConfigService");
		EmailConfig config = emailConfigService.getEmailConfig();

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
		//mailInfo.setCcAddress("2399556850@qq.com");
		
		mailInfo.setSubject(title);
		mailInfo.setContent(content);
		// 这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		sms.sendTextMail(mailInfo);// 发送文体格式
		//sms.sendHtmlMail(mailInfo);// 发送html格式
	}
}
