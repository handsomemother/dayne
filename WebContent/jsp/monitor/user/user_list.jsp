<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/includes/inc_envSetup.jsp" %>
<%@ include file="/includes/inc_js.jsp" %>
<%@ include file="/includes/inc_css.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>监控系统</title>
</head>
<body >
<form:form id="search" action="${contextPath}/userInfo.do?action=list" method="post" class="std_form" commandName="criteria">
    <div class="pr10">
		<div class="hr"></div>
		<h3 class="caption">用户列表</h3>
        <div class="std_filter">
			<p>
				<label class="lab">用户账号：</label>
				<form:input path="userName" cssClass="ipt_txt"/>
				<label class="lab">用户名称：</label>
				<form:input path="name" cssClass="ipt_txt"/>
				<label class="lab">用户状态：</label>
				<form:select path="status" cssClass="sel">
					<form:option value="">不限</form:option>
					<form:option value="true">正常</form:option>
					<form:option value="false">禁用</form:option>
				</form:select>
				<!-- js 方法在分页jsp中 -->
				<label class="lab"></label>
				<input type="button" class="btn btn_deep" value="查询" onclick="searchSubmit();"/> 
			</p>
        </div>
        <div class="std_ctrl">
            <!-- <a href="#" id="a_delItem"><b class="s_ico s_ico_del"></b>批量禁用</a>| -->
            <a href="${contextPath}/userInfo.do?action=create" id="a_addItem">
            	<b class="s_ico s_ico_add"></b>添加用户
            </a>
        </div>
        <div class="std_tdiv">
            <table id="table1" class="std_table" border="1" >
			<thead>
                <tr>
                    <!-- <th class="col0 tc"><input type="checkbox" class="fid" value=""></th> -->
                    <th class="col4 tc sort" sortable="true" datatype="num" >编号<b class="s_ico s_ico_sor_nev"></b></th>
                    <th class="col2 tc">用户名字</th>
                    <th class="col2 tc">用户账号</th>
                    <th class="col6 tc">用户状态</th>
                    <th class="col1 tc">操作</th>
                </tr>
                </thead>
                <tbody>
	                <c:forEach items="${userInfoList}" var="user">
	        			<tr>
		                    <%-- <td class="col0 tc"><input type="checkbox" class="fid" value="${user.id}"></td> --%>
		                    <td class="tc">${user.id}</td>
		                    <td title="${user.name}" class="tc">${user.name}</td>
		                    <td title="${user.userName}" class="tc">${user.userName}</td>
		                    <td class="tc">${user.displayStatus}</td>
		                    <td class="tc">
		                        <a href="${contextPath}/userInfo.do?action=update&id=${user.id}" class="unl">修改</a> |
		                        <a href="${contextPath}/userInfo.do?action=use&id=${user.id}" class="unl">启用</a> |
		                        <%-- <a href="${contextPath}/userInfo.do?action=forbidden&id=${user.id}" class="unl">禁用</a> --%>
		                        <a href="#" onclick="forbidden(${user.id});" class="unl">禁用</a>
		                        
		                    </td>
	               		</tr>
	        		</c:forEach>
                </tbody>
                <tfoot>
                	<tr>
						<td colspan="5" class="tf_r">
							<!-- 引入分页jsp -->
                			<%@ include file="/includes/inc_page.jsp" %> 
					     </td>
					</tr>
                </tfoot>
            </table>
        </div>
    </div>
</form:form>
<script type="text/javascript">
	function forbidden(id){
		if (confirm("是否确认执行禁用操作")){
			window.location.href=contextPath+"/userInfo.do?action=forbidden&id="+id;
		}
	};

	$(document).ready(function() {
		/*----初始化表格----*/
		var table = new SysTable({
			id : "table1",
			sortable : true,
			autoSelect : false
		});
		/*----暂不使用----*/
		$("#a_delItem").click(function() {
			var msg = table.getSelItem().length > 0 ? "表单提交。" : "你没有选择任何项目。";
			var items = table.getSelItem();
			var ids = "";
			$.each(items, function(i, item) {
				ids = ids + item.value + ",";
			});
			if (table.getSelItem().length > 0) {
				$.ajax({
					type : "POST",
					async : false,
					url : contextPath + "/userInfo.do?action=forbiddenAll",
					data : "ids=" + ids,
					dataType: "text",
					success : function(msg) {
						if(msg == "success"){
							$("#search").submit();
							
						}else{
							new SysAlert({body : "删除错误"});
						}
					}
				});
			} else {
				new SysAlert({body : msg});
			};
		});
	});
</script>

</body>
</html>