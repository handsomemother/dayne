<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/includes/inc_envSetup.jsp" %>
<%@ include file="/includes/inc_js.jsp" %>
<%@ include file="/includes/inc_css.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>监控系统</title>
    <link href="${contextPath}/css/custom.css" rel="stylesheet" media="all" />
    <script type="text/javascript" src="${contextPath}/jsp/monitor/contact/js/contactFormValidator.js"></script>
</head>
<body>
    <div class="pr10">
        <div class="hr"></div>
        <!--表单内容-->
        <form:form id="sysForm" action="${contextPath}/contacter.do?action=submit" method="post" cssClass="std_form">
            <h3 class="caption">
            	<c:choose>
            		<c:when test="${command.id>0}">
            			编辑
            		</c:when>
            		<c:otherwise>
            			添加
            		</c:otherwise>
            	</c:choose>
            	联系人
            </h3>
            <!-- 隐藏ID -->
            <form:hidden path="id"/>
            <form:hidden path="createDate"/>
            <table class="edit_table">
           		 <tr>
            		<td>联系人名称：</td>
            		<td>
            			<form:input path="name" cssClass="ipt_txt"/>
            		</td>
            		<td><div id="nameTip" class="formValidator_info"></div></td>
            	</tr>
            	<tr>
            		<td>联系人EMAIL：</td>
            		<td>
            			<form:input path="email" cssClass="ipt_txt"/>
            		</td>
            		<td><div id="emailTip" class="formValidator_info"></div></td>
            	</tr>
            	<tr>
            		<td>联系人电话：</td>
            		<td>
            			<form:input path="tel" cssClass="ipt_txt"/>
            		</td>
            		<td><div id="telTip" class="formValidator_info"></div></td>
            	</tr>
            </table>
            <div class="hr mb20"></div>
            <p class="b_area">
                <input type="submit" value="保存" class="b_btn b_btn_deep"/>
                <input type="button" value="返回" class="b_btn" onclick="javascript:history.back();"/>
            </p>
        </form:form>
        
    </div>
</body>
</html>