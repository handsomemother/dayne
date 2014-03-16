<%@ page language="java" contentType="text/html; charset=utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/includes/inc_envSetup.jsp" %>
<%@ include file="/includes/inc_js.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<html>
<head>
    <title>智慧家庭服务平台监控系统</title>
    <link href="${contextPath}/css/style.css" rel="stylesheet" media="all"/>
	<script type="text/javascript">
		if(window.top!=window.self){ 
			 window.open("${contextPath}/login.do","_top");
		}
	</script>
</head>
<body class="log_bg">
    <div class="log_title" >
        <h1>智慧家庭服务平台监控系统</h1>
    </div>
    <div class="log_main">
        <div class="fl pic">
        </div>
        <div class="fr pv20" >
            <form action="${contextPath}/login.do?action=valid" method="post" class="std_form">
                <p class="verify_err" >
	                <c:if test="${fn:length(message)>0}">
						<font color="red">错误提示：${message}</font>
					</c:if>
				</p>
                <p>
                    <label class="lab">账号：</label><input type="text" name="userName" value="${userName}" class="ipt_txt">
                </p>
                <p>
                    <label class="lab">密码：</label><input type="password" name="passWord" class="ipt_txt">
                </p>
                <p>
                    <label class="lab"></label>
                    <input type="submit" class="btn btn_deep" value="登录">
                </p>
            </form>
        </div>

    </div>
    <div class="log_foot">
        <p>
            <font color="red">version 1.0</font> Copyright @ 广州中国科学院软件应用技术研究所
        </p>
    </div>
</body>
</html>