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
    <script type="text/javascript" src="${contextPath}/jsp/monitor/contact/js/groupFormValidator.js"></script>
    
</head>
<body>
    <div class="pr10">
        <div class="hr"></div>
        <!--表单内容-->
        <form:form id="sysForm" action="${contextPath}/contactGroup.do?action=submit" method="post" cssClass="std_form">
            <h3 class="caption">添加联系人组</h3>
            <!-- 隐藏ID -->
            <form:hidden path="id"/>
            <table class="edit_table">
           		 <tr>
            		<td>联系组名称：</td>
            		<td>
            			<form:input path="groupName" cssClass="ipt_txt"/>
            		</td>
            		<td><div id="groupNameTip" class="formValidator_info"></div></td>
            	</tr>
            	<tr>
            		<td>联系人：</td>
            		<td>
            			<div id="dataChangeList" class="dataChangeList">
							<div class="area">
								<span>待选人员</span> 
								<select id="left" multiple="multiple">
									<c:forEach items="${command.allContacters}" var="item">
										<option value="${item.key}">${item.value}</option>
									</c:forEach>
								</select>
							</div>
							<div class="break toolbar">
								<b title="添加联系人" class="x_ico x_ico_right" onclick="btnclick('add', 'left', 'right');"></b>
								<b title="删除联系人" class="x_ico x_ico_left" onclick="btnclick('sub', 'left', 'right');"></b> 
								<b title="添加所有人" class="x_ico x_ico_allright" onclick="btnclick('addAll', 'left', 'right');"></b> 
								<b title="移除所有人" class="x_ico x_ico_allleft" onclick="btnclick('subAll', 'left', 'right');"></b>
							</div>
							<div class="area">
								<span>已选人员</span> 
								<select id="right" multiple="multiple">
									<c:forEach items="${command.selectedContacters}" var="item">
										<option value="${item.key}">${item.value}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<form:hidden path="contacterIds" id="contacterIds"/>						 
            		</td>
            	</tr>
            	<tr>
            		<td>备注：</td>
            		<td>
            			<form:textarea path="remark" cssStyle="font-size:12px"/>
            		</td>
            		<td><div id="remarkTip" class="formValidator_info"></div></td>
            	</tr>
            </table>
            <p/>
            <div class="hr mb20"></div>
            <p class="b_area">
                <input type="button" value="保存" class="b_btn b_btn_deep" onclick="submitClick();"/>
                <input type="button" value="返回" class="b_btn" onclick="javascript:history.back();"/>
            </p>
        </form:form>
        
    </div>
    
    <script type="text/javascript">
    	function submitClick(){
    		var contacterIds = "";
    		//右侧人员选择栏
    		$("#right option").each(function() {
    			contacterIds = contacterIds+$(this).val()+',';
            });
    		if(contacterIds==""){
    			//new SysAlert({type:'err',body:'联系人为空,'});
    			//return false;
    			
    			if (confirm("联系人为空，是否确认创建空组?"))  {  
    				$("#contacterIds").attr("value",contacterIds);
            		$("#sysForm").submit();
    			}else{ 
    				return false;
    			};
    		}else{
    			$("#contacterIds").attr("value",contacterIds);
        		$("#sysForm").submit();
    		}
    	}
	    function btnclick(option, leftid, rightid) {
	        if (option == "add") {
	            if ($("#" + leftid + " option:selected").length > 0) {
	                $("#" + leftid + " option:selected").each(function(){
	                	//左侧已选项clone
	                	var leftOption = $(this).clone();
	                	//标识右侧是否已经存在
	                	var isExt = false;
	                	$("#" + rightid + " option").each(function() {
	                		if($(leftOption).val()==$(this).val()){
	                			isExt = true;
	                		}
	                	});
	                	if(!isExt){
	                		$(leftOption).appendTo($("#" + rightid));
	                	}else{
	                		alert("该项已选");
	                	}
	                    $(this).removeAttr("selected");
	                });
	            }
	            else {
	                alert("请您先选择");
	            }
	        }
	        if (option == "sub") {
	            if ($("#" + rightid + " option:selected").length > 0) {
	                $("#" + rightid + " option:selected").each(function() {
	                    $(this).remove();
	                });
	            }
	            else {
	                alert("请您先选择");
	            }
	        }
	        if(option == "addAll"){
	        	$("#" + rightid + " option").each(function() {
                    $(this).remove();
                });
	        	$("#" + leftid + " option").each(function() {
                    $(this).clone().appendTo($("#" + rightid));
                });
	        }
	        if (option == "subAll") {
	        	$("#" + rightid + " option").each(function() {
                    $(this).remove();
                });
	        }
	    }
	</script>

    
    
</body>
</html>