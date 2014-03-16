<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="/includes/inc_envSetup.jsp"%>
<%@ include file="/includes/inc_js.jsp"%>
<%@ include file="/includes/inc_css.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>监控系统</title>
</head>
<body>
	<div id="dataChangeList" class="dataChangeList">
		<div class="area">
			<span>待选人员</span> 
			<select multiple="true">
				<option value="冯剑辉">冯剑辉</option>
				<option value="郭天苗">郭天苗</option>
				<option value="高权">高权</option>
				<option value="魏劲松">魏劲松</option>
			</select>
		</div>
		<div class="break toolbar">
			<b title="右移" class="x_ico x_ico_right" opt="fromLeft"></b> <b
				title="左移" class="x_ico x_ico_left" opt="fromRight"></b> <b
				title="全部右移" class="x_ico x_ico_allright" opt="allFromLeft"></b> <b
				title="全部左移" class="x_ico x_ico_allleft" opt="allFromRight"></b>
		</div>
		<div class="area">
			<span>已选人员</span> 
			<select multiple="true">
				<option value="刘铭航">刘铭航</option>
				<option value="区伟鸿">区伟鸿</option>
				<option value="岑镇荣">岑镇荣</option>
			</select>
		</div>
	</div>
	<script type="text/javascript">
    	new dataChangeList({
        	id:'dataChangeList'
	    });
	</script>
</body>
</html>