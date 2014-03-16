package cn.dayne.gz.platform.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.dayne.gz.platform.util.CommonConstant;

@Controller
@RequestMapping("/login_out.do")
public class LoginOutController {

	private final static String LOGIN_PAGE = "jsp/login.jsp";

	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
	public String login_out(HttpServletRequest request,HttpServletResponse response){
		if(request.getSession().getAttribute(CommonConstant.USER_INFO)!=null){
			request.getSession().removeAttribute(CommonConstant.USER_INFO);
			request.getSession().invalidate();  
		}
		
		return "redirect:" + LOGIN_PAGE;
	}

}
