package cn.dayne.gz.platform.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.dayne.gz.platform.entity.UserInfo;
import cn.dayne.gz.platform.util.CommonConstant;

/**
 * 简单认证
 * 
 * @author yeqiuming
 *
 */
public class SecurityFilter extends HttpServlet implements Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//FilterConfig可用于访问Filter的配置信息
	private FilterConfig config;
	
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		
		 HttpServletRequest request=(HttpServletRequest)servletRequest;     
         HttpServletResponse response  =(HttpServletResponse) servletResponse;  
         
        UserInfo userInfo = (UserInfo)request.getSession().getAttribute(CommonConstant.USER_INFO);
		if(userInfo == null){
			boolean isValid = true;
			String [] unProtectedUris = config.getInitParameter("unProtectedUri").split(";");
			for(String uri:unProtectedUris){
				if(request.getRequestURI().contains(uri)){
					isValid = false;
				}
			}
			if(isValid){
				response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");	
				return;
			}
		}
		filterChain.doFilter(request, response);
		return;
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

}
