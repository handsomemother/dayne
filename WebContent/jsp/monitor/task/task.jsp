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
    <style type="text/css">
    	.edit_table tr{height: 20px; text-align: center;}
    </style>
</head>
<body>
    <div class="pr10">
     	<div class="hr"></div>
            <h3 class="caption">手动执行任务</h3>
            <table class="edit_table">
            	<tr>
            		<td>手动执行高频：</td>
            		<td><input type="button" value="手动执行" class="b_btn b_btn_deep" onclick="doHighTask();"/></td>
            	</tr>
            </table>
            <div style="height: 25px;"></div>

            <table class="edit_table">
            	<tr>
            		<td>手动执行中频：</td>
            		<td><input type="button" value="手动执行" class="b_btn b_btn_deep" onclick="doMiddleTask();"/></td>
            	</tr>
            </table>
           <div style="height: 25px;"></div>
    
            <table class="edit_table">
            	<tr>
            		<td>手动执行低频：</td>
            		<td><input type="button" value="手动执行" class="b_btn b_btn_deep" onclick="doLowTask();"/></td>
            	</tr>
            </table>
	</div>

    <script type="text/javascript">
    	function doHighTask(){
    		doTask('high');
    	}
		function doMiddleTask(){
			doTask('middle');
    	}
		function doLowTask(){
			doTask('low');
    	}
    	function doTask(param){
    		$.ajax({
					type : "GET",
					async : false,
					url : contextPath + "/task.do?action="+param,
					dataType: "text",
					success : function(msg) {
						new SysAlert({body : msg});
					}
			});
    	}
    	
    </script>
</body>
</html>