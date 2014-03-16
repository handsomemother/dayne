<!-- 设置jsp运行环境，包括引入tablib, 引入类，预设置变量 -->
<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/includes/inc_taglib.jsp" %>
<%@ page isELIgnored="false"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
