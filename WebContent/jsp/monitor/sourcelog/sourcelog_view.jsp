<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/includes/inc_envSetup.jsp" %>
<%@ include file="/includes/inc_js.jsp" %>
<%@ include file="/includes/inc_css.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>监控系统</title>
    <link href="${contextPath}/css/custom.css" rel="stylesheet" media="all" />
</head>
<body>
    <div class="pr10">
        <div class="hr"></div>
       		<h3 class="caption">查看监控源日志</h3>
            <table class="edit_table">
            	<tr>
            		<td>监控源名称：</td>
            		<td>
            			${sourceLog.sourceName}
            		</td>
            	</tr>
            	<tr>
            		<td>监控地址：</td>
            		<td>
            			${sourceLog.urlAddress}
            		</td>
            	</tr>
            	<tr>
            		<td>参数信息：</td>
            		<td>
            			${sourceLog.param}
            		</td>
            	</tr>
            	<tr>
            		<td>请求类型：</td>
            		<td>
						${sourceLog.requestType}
            		</td>
            	</tr>
            	<tr>
            		<td>接口类型：</td>
            		<td>
						${sourceLog.displayType}
            		</td>
            	</tr>
            	<tr>
            		<td>所属系统：</td>
            		<td>
						${sourceLog.displaySys}
            		</td>
            	</tr>
            	<tr>
            		<td>监控级别：</td>
            		<td>
						${sourceLog.displayMonitorLevel}
            		</td>
            	</tr>
            	<tr>
            		<td>正常响应时间：</td>
            		<td>
            			${sourceLog.responseTime}
            		</td>
            	</tr>
				<tr>
					<td>实际响应时间：</td>
					<td><c:choose>
							<c:when	test="${sourceLog.recentlyResponseTime > sourceLog.responseTime }">
								<span class="l_con linb"> 
									<span style="width: 100%;" class="l_in l_in_err"> </span> 
								</span>
							</c:when>
							<c:otherwise>
								<span class="l_con linb"> 
									<span style="width:${(sourceLog.recentlyResponseTime/sourceLog.responseTime)*100}%;" class="l_in"></span> 
								</span>
							</c:otherwise>
						</c:choose> <span class="linb l_tp">${sourceLog.recentlyResponseTime}s</span>
					</td>
				</tr>
				<tr>
	            	<td>接口状态：</td>
	            	<td>
	            		<c:choose>
			                <c:when test="${sourceLog.status==1}">
			                   <a href="javascript:void(0);" class="state_on" title="正常"></a>
			                </c:when>
			                <c:otherwise>
			                   <a href="javascript:void(0);" class="state_off" title="异常"></a>
			              	</c:otherwise>
			            </c:choose>
	            	</td>
	            </tr>
            	<tr>
            		<td>返回结果：</td>
            		<td>
            			${sourceLog.result}
            		</td>
            	</tr>
            </table>
            <div class="hr mb20"></div>
            <p class="b_area">
                <input type="button" value="返回" class="b_btn" onclick="javascript:history.back();"/>
            </p>
    </div>
</body>
</html>