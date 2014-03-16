<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/includes/inc_envSetup.jsp" %>
<%@ include file="/includes/inc_js.jsp" %>
<%@ include file="/includes/inc_css.jsp" %>
<html>
<head>
    <title>监控系统</title>
</head>
<body>

    <div class="frame_top" id="frame_top">
        <div class="topline">
            <a class="fl logo">
                <img src="${contextPath }/images/logo.jpg" title="智慧家庭服务系统" alt="智慧家庭服务系统">
            </a>
            <div class="fr comlink">
                <span>欢迎你，<label>${USER_INFO.name}</label></span>
                [<a href="${contextPath}/login_out.do">退出</a>]
            </div>
        </div>
		<!--tab内容-->
        <div class="tabbar" id="tabbar">
            <ul>
                <li id="one" class="on">
                    <b class="m_ico m_ico_setting"></b>
                    <a href="${contextPath}/index.do" onclick="oneClick();">监控管理</a>
                    <span></span>
                </li>
                <li id="two">
                    <b class="m_ico m_ico_setting"></b>
                    <a href="${contextPath}/monitoring.do" target="main_frame" onclick="twoClick();">系统监控</a>
                    <span></span>
                </li>
            </ul>
        </div>
    </div>
	<!--tab内容结束-->
	
    <!--侧边菜单-->
    <%@ include file="/jsp/monitor/monitor_left.jsp" %>
     
    <!--正式内容-->
    <div class="frame_main" id="frame_main">
        <div class="layout" >
            <iframe id="main_frame" class="main_frame" name="main_frame" frameborder="0" width="100%" height="100%" src="${contextPath}/source.do?action=list">

            </iframe>
        </div>
        <!--end of layout-->
    </div>
	<script type="text/javascript">
		function twoClick(){
			$("#one").removeAttr("class");
			$("#two").attr("class","on");
		}
	</script>
</body>
</html>