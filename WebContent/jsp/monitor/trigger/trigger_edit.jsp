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
             <h3 class="caption">配置说明</h3>
            <%-- <a href="${contextPath}/jsp/monitor/trigger/trigger_explain.jsp">配置说明</a> --%>
           	<div style="height: 200px;">
           	<div style="height: 20px;">
           		配置格式: [秒] [分] [小时] [日] [月] [周] [年]
           		<a href="${contextPath}/jsp/monitor/trigger/trigger_help.jsp">更多帮助</a>
           	</div>
         	<table border="1" width="500px;" class="edit_table">
         		<tr>
         			<td>序号 </td>
         			<td>说明</td>
         			<td>是否必填 </td>
         			<td>允许填写的值</td>
         			<td>允许的通配符</td>
         		</tr>
         		<tr>
         			<td>1</td>
         			<td>秒 </td>
         			<td>是 </td>
         			<td>0-59</td>
         			<td>, - * /</td>
         		</tr>
         		<tr>
         			<td>2</td>
         			<td>分</td>
         			<td>是 </td>
         			<td>0-59</td>
         			<td>, - * /</td>
         		</tr>
         		<tr>
         			<td>3</td>
         			<td>小时</td>
         			<td>是 </td>
         			<td> 0-23</td>
         			<td>, - * /</td>
         		</tr>
         		<tr>
         			<td>4</td>
         			<td>日</td>
         			<td>是 </td>
         			<td>1-31</td>
         			<td>, - * ? / L W</td>
         		</tr>
         		<tr>
         			<td>5</td>
         			<td>月</td>
         			<td>是 </td>
         			<td>1-12 or JAN-DEC</td>
         			<td>, - * /</td>
         		</tr>
         		<tr>
         			<td>6</td>
         			<td>周 </td>
         			<td>是 </td>
         			<td>1-7 or SUN-SAT</td>
         			<td>, - * ? / L #</td>
         		</tr>
         		<tr>
         			<td>7</td>
         			<td>年 </td>
         			<td>否 </td>
         			<td>  empty 或 1970-2099</td>
         			<td>, - * /</td>
         		</tr>
         	</table>
    	</div>  
        <div class="hr"></div>
        <!--表单内容-->
        <form:form id="sysForm1" action="${contextPath}/scheduler.do?action=submit" method="post" cssClass="std_form" commandName="highTrigger">
             <h3 class="caption">高频触发器</h3>
            <!-- 隐藏ID -->
            <form:hidden path="id"/>
            <!-- 隐藏频率级别 -->
            <form:hidden path="monitoringLevel"/>
            <table class="edit_table">
            	<tr>
            		<td>触发时间表达式：</td>
            		<td width="200px;">
            			<form:input path="time" id="time1" cssClass="ipt_txt"/>
            		</td>
            		<td><input type="button" value="保存" class="b_btn b_btn_deep" onclick="triggerSubmit('sysForm1','time1');"/></td>
            	</tr>
            </table>
            <p/>
            <div class="hr mb20"></div>
        </form:form>
        
        <!--表单内容-->
        <form:form id="sysForm2" action="${contextPath}/scheduler.do?action=submit" method="post" cssClass="std_form" commandName="middleTrigger">
            <h3 class="caption">中频触发器</h3>
            <!-- 隐藏ID -->
            <form:hidden path="id"/>
            <!-- 隐藏频率级别 -->
            <form:hidden path="monitoringLevel"/>
            <table class="edit_table">
            	<tr>
            		<td>触发时间表达式：</td>
            		<td width="200px;">
            			<form:input path="time" id="time2" cssClass="ipt_txt"/>
            		</td>
            		<td><input type="button" value="保存" class="b_btn b_btn_deep" onclick="triggerSubmit('sysForm2','time2');"/></td>
            	</tr>
            </table>
            <p/>
            <div class="hr mb20"></div>
        </form:form>
        
        <!--表单内容-->
        <form:form id="sysForm3" action="${contextPath}/scheduler.do?action=submit" method="post" cssClass="std_form" commandName="lowTrigger">
            <h3 class="caption">低频触发器</h3>
            <!-- 隐藏ID -->
            <form:hidden path="id"/>
            <!-- 隐藏频率级别 -->
            <form:hidden path="monitoringLevel"/>
            <table class="edit_table">
            	<tr>
            		<td>触发时间表达式：</td>
            		<td width="200px;">
            			<form:input path="time" id="time3" cssClass="ipt_txt"/>
            		</td>
            		<td><input type="button" value="保存" class="b_btn b_btn_deep" onclick="triggerSubmit('sysForm3','time3');"/></td>
            	</tr>
            </table>
            <p/>
            <div class="hr mb20"></div>
        </form:form>
    </div>
    <div>
	    <input type="button" value="暂停" class="b_btn b_btn_deep" onclick="suspend();"/>
	    <input type="button" value="启动" class="b_btn b_btn_deep" onclick="restart();"/>
    </div>
    <script type="text/javascript">
    
    	function triggerSubmit(formId,timeId){
  			var time = $("#"+timeId).val();
  			if(time.length<1){
  				new SysAlert({
  	                  type:'err',
  	                  body:'触发时间表达式不能为空'
  	            });
  				return false;
  			}
  			var flag = "false";  
  			$.ajax({
				type : "POST",
				async : false,
				url : contextPath + "/scheduler.do?action=validate",
				data: "cron="+time, 
				dataType: "text",
				success : function(msg) {
					flag = msg;
				}
			});
  			
  			if(flag=='true'){
  				 $.ajax({
 					type : "POST",
 					async : false,
 					url : contextPath + "/scheduler.do?action=submit",
 					data: $('#'+formId).serialize(), 
 					dataType: "text",
 					success : function(msg) {
 						new SysAlert({type:'suc',body : msg});
 					}
 				});
  			}else{
  				new SysAlert({
	                  type:'err',
	                  body:'表达式格式错误'
	            });
  			}
  			
  			 
    	}
    	
    	/* 
    	$(document).ready(function(){
    		  $("#sysForm1").submit(function(e){
    			  var time1 = $("#time1").val();
    			  if(time1.length<1){
    				  new SysAlert({
    	                    type:'err',
    	                    body:'触发时间表达式不能为空'
    	                    });
    				  return false;
    			  }
    			  $.ajax({
  					type : "POST",
  					async : false,
  					url : contextPath + "/scheduler.do?action=submit",
  					data: $('#sysForm1').serialize(), 
  					dataType: "text",
  					success : function(msg) {
  						new SysAlert({type:'suc',body : msg});
  					}
  				});
    			  return false;
    		  });
    		  $("#sysForm2").submit(function(e){
    			  if(time2.length<1){
    				  new SysAlert({
    	                    type:'err',
    	                    body:'触发时间表达式不能为空'
    	                    });
    				  return false;
    			  }
    			  
    			  $.ajax({
  					type : "POST",
  					async : false,
  					url : contextPath + "/scheduler.do?action=submit",
  					data: $('#sysForm2').serialize(), 
  					dataType: "text",
  					success : function(msg) {
  						new SysAlert({type:'suc',body : msg});
  					}
  				});
    			  return false;
    		  });
    		  $("#sysForm3").submit(function(e){
    			  if(time3.length<1){
    				  new SysAlert({
    	                    type:'err',
    	                    body:'触发时间表达式不能为空'
    	                    });
    				  return false;
    			  }
    			  $.ajax({
  					type : "POST",
  					async : false,
  					url : contextPath + "/scheduler.do?action=submit",
  					data: $('#sysForm3').serialize(), 
  					dataType: "text",
  					success : function(msg) {
  						new SysAlert({type:'suc',body : msg});
  					}
  				});
    			  return false;
    		  });
    	});
    	 */
    	function suspend(){
    		if (confirm("是否确认执行<暂停>操作")){
	    		$.ajax({
						type : "POST",
						async : false,
						url : contextPath + "/scheduler.do?action=stop",
						dataType: "text",
						success : function(msg) {
							new SysAlert({body : msg});
						}
				});
    		}
    	}
    	
    	function restart(){
    		if (confirm("是否确认执行<重启动>操作")){
	    		$.ajax({
						type : "POST",
						async : false,
						url : contextPath + "/scheduler.do?action=start",
						dataType: "text",
						success : function(msg) {
							new SysAlert({body : msg});
						}
				});
    		}
    	}
    </script>
</body>
</html>