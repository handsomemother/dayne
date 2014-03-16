/*
 * Copyright (C) 2011 NFSChina Inc., All Rights Reserved.
 */
package cn.dayne.gz.platform.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * 用来禁止客户端使用缓存。
 * 
 * @author 
 * @version 1.0, 2012-02-14
 */
public class ClearCacheFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain filterChain) throws IOException, ServletException {
    	
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("Cache-Control", "no-cache");
        httpResponse.setHeader("Cache-Control", "no-store");
        httpResponse.setHeader("Pragma", "no-cache");
        httpResponse.setDateHeader("Expires", -1);
        
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    	
    }
}
