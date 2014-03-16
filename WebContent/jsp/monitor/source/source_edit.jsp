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
    <script type="text/javascript" src="${contextPath}/jsp/monitor/source/js/sourceFormValidator.js"></script>
</head>
<body>
    <div class="pr10">
        <div class="hr"></div>
        <!--表单内容-->
        <form:form id="sysForm" action="${contextPath}/source.do?action=submit" method="post" cssClass="std_form">
            <h3 class="caption">
            	<c:choose>
            		<c:when test="${command.id>0}">修改</c:when>
            		<c:otherwise>添加</c:otherwise>
            	</c:choose>
            	监控源
            </h3>
            <!-- 隐藏ID -->
            <form:hidden path="id"/>
            <!-- 隐藏最近响应时间 -->
            <form:hidden path="recentlyResponseTime"/>
            <!-- 隐藏最后一次发邮件时间 -->
            <form:hidden path="lastSendEmailTime"/>
            
            <table class="edit_table">
            	<tr>
            		<td>监控源名称：</td>
            		<td>
            			<form:input path="sourceName" cssClass="ipt_txt" cssStyle="width: 220px;"/>
            		</td>
            		<td><div id="sourceNameTip" class="formValidator_info"></div></td>
            	</tr>
            	<tr>
            		<td>监控地址：</td>
            		<td>
            			<form:input path="urlAddress" cssClass="ipt_txt" cssStyle="width: 220px;"/>
            		</td>
            		<td><div id="urlAddressTip" class="formValidator_info"></div></td>
            	</tr>
            	<tr>
            		<td>正常响应时间：</td>
            		<td>
            			<form:input path="responseTime" cssClass="ipt_txt" cssStyle="width: 220px;"/>
            			<font color="red">s</font>
            		</td>
            		<td><div id="responseTimeTip" class="formValidator_info"></div></td>
            	</tr>
            	<tr>
            		<td>邮件发送间隔>=：</td>
            		<td>
            			 <form:input path="emailInterval" cssClass="ipt_txt" cssStyle="width: 220px;"/> 
            			 <font color="red">h</font>
            		</td>
            		<td><div id="emailIntervalTip" class="formValidator_info"></div></td>
            	</tr>
            	<tr>
            		<td>请求类型：</td>
            		<td>
            			<form:select path="requestType" class="sel" onchange="selectChange();">
            				<form:option value="post" label="post"/>
            				<form:option value="get" label="get"/>
            			</form:select>
            			<div>
	            			<font size="2" color="#23396B">
	            				*注意选择监控源提供的请求方式
	            			</font>
            			</div>
            		</td>
            	</tr>
            	<tr>
            		<td>接口类型：</td>
            		<td>
            			<form:select path="type" cssClass="sel">
							<form:option value="2">REST接口</form:option>
							<form:option value="3">WS接口</form:option>
							<form:option value="4">数据库</form:option>
							<form:option value="5">网页</form:option>
							<form:option value="1">电信接口</form:option>
						</form:select>
            		</td>
            	</tr>
            	<tr>
            		<td>所属系统：</td>
            		<td>
            			<form:select path="sysCode" cssClass="sel">
							<form:option value="1">智慧家庭服务平台</form:option>
							<form:option value="2">智慧家居控制平台</form:option>
						</form:select>
            		</td>
            	</tr>
            	<tr>
            		<td>监控级别：</td>
            		<td>
            			<form:select path="monitorLevel" cssClass="sel">
							<form:option value="1">高</form:option>
							<form:option value="2">中</form:option>
							<form:option value="3">低</form:option>
						</form:select>
            		</td>
            	</tr>
            	<tr>
            		<td>接口状态：</td>
            		<td>
            			<form:select path="status" class="sel">
							<form:option value="1" selected="selected">正常</form:option>
							<form:option value="0">异常</form:option>
						</form:select> 
            		</td>
            	</tr>
            	<tr>
            		<td>联系人组：</td>
            		<td>
            			<form:select path="contactGroup.id" id="groupId" items="${command.contactGroupMap}" class="sel">
            				<form:options itemValue="${key}" itemLabel="${value}"/>
            			</form:select>
            			<div>
            				<font size="2" color="#23396B">*如果不存在联系组请先建立</font>
            			</div>
            		</td>
            		<td><div id="groupIdTip" class="formValidator_info"></div></td>
            	</tr>
            	
            	<tr id="post_param" style="display: none">
            		<td>POST参数信息：</td>
            		<td>
            			<form:textarea path="param" rows="5" cols="30" cssStyle="font-size:12px"/>
            			<div>
            				<font size="2" color="#23396B">
            				*如果不带参数不需填写，
            				</font>
            				<br>
            				<font size="2" color="#23396B">
            				若多个参数只是用;隔开,如：a=1;b=2
            				</font>
            			</div>
            		</td>
            	</tr>
            	
            	<tr>
            		<td>备注信息：</td>
            		<td>
            			<form:textarea path="remark" rows="5" cols="30" cssStyle="font-size:12px"/>
            		</td>
            		<td><div id="remarkTip" class="formValidator_info"></div></td>
            	</tr>
            </table>
            <p/>
            <div class="hr mb20"></div>
            <p class="b_area">
                <input type="button" value="保存" class="b_btn b_btn_deep" onclick="sourceSubmit();"/>
                <input type="button" value="返回" class="b_btn" onclick="javascript:history.back();"/>
            </p>
        </form:form>
    </div>
    <script type="text/javascript">
   		$(document).ready(function(){
			if('${command.requestType}'=='get'){
				$("#post_param").hide();
			}else{
				$("#post_param").show();
			}
    	});
    
    	function selectChange(){
    		if($("#requestType").val()=='post'){
    			$("#post_param").show();
    		}else{
    			$("#post_param").hide();
    		}
    	}
    	function sourceSubmit(){
    		var flag = true;
    		var param = $("#param").val();
    		if(param != ""){
    			$.each( param.split(";"), function(i,temp){
        			if(temp.indexOf("=")<0){
        				flag = false;
        			}
        		});
    		}
    		if(!flag){
    			new SysAlert({
                    type:'err',
                    body:'参数格式有误，请确认!参数格式应如:a=1;b=2'
                    });    			
    		}else{
    			$("#sysForm").submit();
    		}
    	}
    </script>
</body>
</html>