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
       		<h3 class="caption">检测信息</h3>
            <table class="edit_table">
            	<tr>
            		<td>监控源名称：</td>
            		<td>
            			${sourceDTO.sourceName}
            		</td>
            	</tr>
            	<tr>
            		<td>监控地址：</td>
            		<td>
            			${sourceDTO.urlAddress}
            		</td>
            	</tr>
            	<tr>
            		<td>接口类型：</td>
            		<td>
						${sourceDTO.displayType}
            		</td>
            	</tr>
            	<tr>
            		<td>所属系统：</td>
            		<td>
						${sourceDTO.displaySys}
            		</td>
            	</tr>
            	<tr>
            		<td>监控级别：</td>
            		<td>
						${sourceDTO.displayMonitorLevel}
            		</td>
            	</tr>
            	<tr>
            		<td>邮件联系人组：</td>
            		<td>
            			${sourceDTO.contactGroup.groupName}
            		</td>
            	</tr>
            	<tr>
            		<td>邮件发送间隔：</td>
            		<td>
            			${sourceDTO.emailInterval}
            		</td>
            	</tr>
            	<c:if test="${sourceDTO.status==1}">
	            	<tr>
	            		<td>正常响应时间：</td>
	            		<td>
	            			${sourceDTO.responseTime}
	            		</td>
	            	</tr>
	            	<tr>
						<td>实际响应时间：</td>
						<td>
							<c:choose>
								<c:when	test="${sourceDTO.recentlyResponseTime > sourceDTO.responseTime }">
									<span class="l_con linb"> 
										<span style="width: 100%;" class="l_in l_in_err"> </span> 
									</span>
								</c:when>
								<c:otherwise>
									<span class="l_con linb"> 
										<span style="width:${(sourceDTO.recentlyResponseTime/sourceDTO.responseTime)*100}%;" class="l_in"></span> 
									</span>
								</c:otherwise>
							</c:choose> <span class="linb l_tp">${sourceDTO.recentlyResponseTime}s</span>
						</td>
					</tr>
            	</c:if>
            	<tr>
            		<td>接口状态：</td>
            		<td>
            			<c:choose>
		                	<c:when test="${sourceDTO.status==1}">
		                    	<a href="javascript:void(0);" class="state_on" title="正常"></a>
		                    </c:when>
		                    <c:otherwise>
		                    	<a href="javascript:void(0);" class="state_off" title="异常"></a>
		                    </c:otherwise>
		                </c:choose>
            		</td>
            	</tr>
            	<tr>
            		<td>参数信息：</td>
            		<td>
            			${sourceDTO.param}
            		</td>
            	</tr>
            	<tr>
            		<td>备注信息：</td>
            		<td>
            			${sourceDTO.remark}
            		</td>
            	</tr>
            	<tr>
            		<td>重检结果：</td>
            		<td>
            			<c:choose>
		                	<c:when test="${sourceDTO.status==1}">
		                    	<c:if test="${sourceDTO.recentlyResponseTime<sourceDTO.responseTime }">
									<font color="#1B3160">正常，上一次响应速度较慢原因可能由于网络不稳或数据量较大导致</font>            			
            					</c:if>
            					<c:if test="${sourceDTO.recentlyResponseTime>sourceDTO.responseTime }">
									<font color="FF7200">正常，但响应速度还较预期慢，请检测网络或接口，或预期响应速度是否设置适中</font>          			
            					</c:if>
		                    </c:when>
		                    <c:otherwise>
		                    	<font color="red">监控源出现异常,系统已发邮件通知相关人员</font>
		                    </c:otherwise>
		                </c:choose>
            		</td>
            	</tr>
            </table>
            <div class="hr mb20"></div>
            <p class="b_area">
                <input type="button" value="返回" class="b_btn" onclick="blackCheck();"/>
            </p>
    </div>
    <script type="text/javascript">
    	function blackCheck(){
    		window.location.href= contextPath+'/monitoring.do?page='+${page};
    	};
    </script>
</body>
</html>