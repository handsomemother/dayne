<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/includes/inc_envSetup.jsp" %>
<html>
<body>
	<ul>
		<li>
			<a href="${contextPath}/scheduler.do">修改</a>
		</li>
		<li>
			<a href="${contextPath}/scheduler.do?action=stop">停止</a>
		</li>
		<li>
			<a href="${contextPath}/scheduler.do?action=start">启动</a>
		</li>
	</ul>
</body>
</html>
