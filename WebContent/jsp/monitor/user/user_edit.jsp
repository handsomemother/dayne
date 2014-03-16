<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/includes/inc_envSetup.jsp" %>
<%@ include file="/includes/inc_js.jsp" %>
<%@ include file="/includes/inc_css.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>监控系统</title>
    <link href="${contextPath}/css/custom.css" rel="stylesheet" media="all" />
	<%-- <script type="text/javascript" src="${contextPath}/jsp/monitor/user/js/passwordformValidator.js"></script> --%>
	<script type="text/javascript" src="${contextPath}/jsp/monitor/user/js/userFormValidator.js"></script>
</head>
<body>
    <div class="pr10">
        <div class="hr"></div>
        <!--表单内容-->
        <form:form id="sysForm" action="${contextPath}/userInfo.do?action=submit" method="post" cssClass="std_form">
            <h3 class="caption">添加用户</h3>
            <!-- 隐藏ID -->
            <form:hidden path="id"/>
            <table class="edit_table">
            	<tr>
            		<td>账号名称：</td>
            		<td>
            			<form:input path="userName" cssClass="ipt_txt"/>
            		</td>
            		<td><div id="userNameTip" class="formValidator_info"></div></td>
            	</tr>
            	<tr>
            		<td>用户密码：</td>
            		<td>
            			<form:password path="passWord" cssClass="ipt_txt" showPassword="true"/>
            		</td>
            		<td><div id="passWordTip" class="formValidator_info"></div></td>
            	</tr>
            	<tr>
            		<td>重复密码：</td>
            		<td>
            			<form:password path="passWordVerify" cssClass="ipt_txt" showPassword="true"/>
            		</td>
            		<td><div id="passWordVerifyTip" class="formValidator_info"></div></td>
            	</tr>
            	<tr>
            		<td>用户名称：</td>
            		<td>
            			<form:input path="name" cssClass="ipt_txt"/>
            		</td>
            		<td><div id="nameTip" class="formValidator_info"></div></td>
            	</tr>
            	<tr>
            		<td>是否启用：</td>
            		<td>
            			<form:select path="status" cssClass="sel">
							<form:option value="true">启用</form:option>
							<form:option value="false">禁用</form:option>
						</form:select>
            		</td>
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