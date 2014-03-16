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
    <script type="text/javascript" src="${contextPath}/jsp/monitor/email/js/emailConfigFormValidator.js"></script>
</head>
<body>
    <div class="pr10">
        <div class="hr"></div>
        <!--表单内容-->
        <form:form id="sysForm" action="${contextPath}/emailConfig.do?action=submit" method="post" cssClass="std_form">
            <h3 class="caption">邮件配置</h3>
            <!-- 隐藏ID -->
            <form:hidden path="id"/>
            
            <table class="edit_table">
           		 <tr>
            		<td>邮件服务器：</td>
            		<td>
            			<form:input path="mailServerHost" cssClass="ipt_txt"/>
            		</td>
            		<td><div id="mailServerHostTip" class="formValidator_info"></div></td>
            	</tr>
            	<tr>
            		<td>邮件服务器端口：</td>
            		<td>
            			<form:input path="mailServerPort" cssClass="ipt_txt"/>
            		</td>
            		<td><div id="mailServerPortTip" class="formValidator_info"></div></td>
            	</tr>
            	<tr>
            		<td>邮箱地址：</td>
            		<td>
            			<form:input path="sysEmailAddress" cssClass="ipt_txt"/>
            		</td>
            		<td><div id="sysEmailAddressTip" class="formValidator_info"></div></td>
            	</tr>
            	<tr>
            		<td>邮箱密码：</td>
            		<td>
            			<form:password path="emailPassWord" showPassword="true" cssClass="ipt_txt"/>
            		</td>
            		<td><div id="emailPassWordTip" class="formValidator_info"></div></td>
            	</tr>
            </table>
            <div class="hr mb20"></div>
            <p class="b_area">
                <input type="submit" value="保存" class="b_btn b_btn_deep"/>
                <input type="button" value="返回" class="b_btn" onclick="javascript:history.back();"/>
            </p>
        </form:form>
    </div>
    <script type="text/javascript">
    	if("${result}"=='success'){
    		new SysAlert({body : "操作成功"});
    	}
    </script>
</body>
</html>