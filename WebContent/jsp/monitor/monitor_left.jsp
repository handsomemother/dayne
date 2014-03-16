<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" %>

	<!--tab内容结束-->
	
    <!--侧边菜单-->
    <div class="frame_side" id="frame_side" >
       <!--侧边菜单主体 -->
       <div class="sidebar fl " id=sidebar >
           <div class="sidebar_item first unit">
               <!-- class="unit" 为initSidebar初始化需要-->
               <b class="s_ico s_ico_rep" ></b> <!--展开-->
               <b class="s_ico s_ico_spr" style="display: none;"></b> <!--收缩-->
               <span style="cursor:pointer;">监控源管理</span>
           </div>
           <ul class="n_sub1" >
               <li class="sel">
               		<a target="main_frame" href="${contextPath}/source.do?action=list">监控源列表</a>
               </li>
           </ul>
           <div class="sidebar_item unit">
               <b class="s_ico s_ico_rep" style="display: none;" ></b>
               <b class="s_ico s_ico_spr" ></b>
               <span style="cursor:pointer;">监控记录</span>
           </div>
           <ul class="n_sub1" style="display: none;">
               <li>
               		<a target="main_frame" href="${contextPath}/sourceLog.do?action=list">监控记录列表</a>
               	</li>
           </ul>
		   
           <div class="sidebar_item unit">
               <b class="s_ico s_ico_rep" style="display: none;"></b>
               <b class="s_ico s_ico_spr"></b>
               <span style="cursor:pointer;">邮件配置</span>
           </div>
           <ul class="n_sub1" style="display: none;">
				<li><a target="main_frame" href="${contextPath}/emailConfig.do?action=update">邮件配置</a></li>
				<li><a target="main_frame" href="${contextPath}/contacter.do?action=list">联系人管理</a></li>
				<li><a target="main_frame" href="${contextPath}/contactGroup.do?action=list">联系人组管理</a></li>
               <!-- <li><a target="main_frame" href="jsp/monitor/monitor_template.jsp">模板管理</a></li> -->
           </ul>
			<div class="sidebar_item unit">
               <b class="s_ico s_ico_rep" style="display: none;"></b>
               <b class="s_ico s_ico_spr"></b>
               <span style="cursor:pointer;">系统管理</span>
           </div>
           <ul class="n_sub1" style="display: none;">
               <li><a target="main_frame" href="${contextPath}/userInfo.do?action=list">用户管理</a></li>
               <li><a target="main_frame" href="${contextPath}/scheduler.do">触发器配置</a></li>
               <li><a target="main_frame" href="${contextPath}/task.do">手动执行任务</a></li>
           </ul>
           <div class="sidebar_foot">
           </div>
       </div>
       <!--收缩按钮-->
       <div class="sidegap fl">
           <a id="side_toggle" class="toggle toggle_hide" href="javascript:void(0);"></a>
       </div>
    </div>
    <script type="text/javascript">
    $(document).ready(function(){
    	$("#sidebar a").click(function(){
    		$("#two").removeAttr("class");
			$("#one").attr("class","on");
    	});
    });
	</script>
    