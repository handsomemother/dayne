package cn.dayne.gz.platform.web;

import java.text.ParseException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quartz.SchedulerException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.dayne.gz.platform.entity.UserInfo;
import cn.dayne.gz.platform.query.UserInfoCriteria;
import cn.dayne.gz.platform.service.UserInfoService;
import cn.dayne.gz.platform.util.CommonConstant;
import cn.dayne.gz.platform.util.Md5Util;

@Controller
@RequestMapping("/login.do")
public class LoginController {
	
	private final static String LOGIN_PAGE = "jsp/login";
	
	private final static String INDEX = "/index.do";
	
	@Resource
	private UserInfoService userInfoService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String login(HttpServletRequest request,HttpServletResponse response,ModelMap model) throws SchedulerException, ParseException {
	
		return LOGIN_PAGE;
	}
	
	@RequestMapping(params = "action=valid" ,method =  RequestMethod.POST )
	public String valid(HttpServletRequest request,HttpServletResponse response,ModelMap model) throws SchedulerException, ParseException {
		
		String userName = request.getParameter("userName")==null?"":request.getParameter("userName");
		String passWord = request.getParameter("passWord")==null?"":request.getParameter("passWord");
		
		//账号回传回页面
		request.setAttribute("userName", userName);
		
		//用户名或密码为空直接返回
		if("".equals(userName)){
			setErrorMessage(request,"账号不能为空");
			return LOGIN_PAGE;
		}else if("".equals(passWord)){
			setErrorMessage(request,"密码不能为空");
			return LOGIN_PAGE;
		}
		
		UserInfoCriteria criteria = new UserInfoCriteria();
		//按用户名查询
		criteria.setUserName(userName);
		UserInfo userInfo = userInfoService.queryByCriteria(criteria);
		
		//判断账号是否存在
		if(userInfo!=null){
			boolean isValid = Md5Util.isValid(userInfo.getPassWord(), passWord, CommonConstant.SALT);
			//判断密码是否正确
			if(isValid){
				//判断用户状态
				if(userInfo.getStatus()){
					request.getSession().setAttribute(CommonConstant.USER_INFO, userInfo);
					return "redirect:"+ INDEX;
				}else{
					setErrorMessage(request,"该用户已被禁用");
				}
			}else{
				setErrorMessage(request,"密码错误");
			}
		}else{
			setErrorMessage(request,"账号不存在");
		}
		return LOGIN_PAGE;
	}
	
	/**
	 * 设置错误信息
	 * 
	 * @param request
	 * @param msg
	 */
	private void setErrorMessage(HttpServletRequest request,String msg){
		request.setAttribute("message", msg);
	}

}
