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
<form:form id="search" action="${contextPath}/contacter.do?action=list" method="post" class="std_form" commandName="criteria">
    <div class="pr10">
        <div class="hr"></div>
        <h3 class="caption">联系人列表</h3>
        <div class="std_filter">
			<p>
				<label class="lab">姓名：</label>
				<form:input path="name" cssClass="ipt_txt"/>
				<label class="lab">邮件：</label>
				<form:input path="email" cssClass="ipt_txt"/>
				<label class="lab">电话：</label>
				<form:input path="tel" cssClass="ipt_txt"/>
				<label class="lab"></label>
				<input type="button" class="btn btn_deep" value="查询" onclick="searchSubmit();"/> 
			</p>
        </div>
        <div class="std_ctrl">
            <a href="${contextPath}/contacter.do?action=create" id="a_addItem"><b class="s_ico s_ico_add"></b>添加联系人</a> |
            <a href="#" id="a_delItem"><b class="s_ico s_ico_del"></b>批量删除</a>
        </div>
        <div class="std_tdiv">
            <table class="std_table" border="1" id="table1" >
				<thead>
                <tr>
                    <th class="col4 tc"><input type="checkbox" class="fid" value=""></th>
                    <th class="tc sort" sortable="true" datatype="num" >编号<b class="s_ico s_ico_sor_nev"></b></th>
                    <th class="tc">姓名</th>
                    <th class="tc">邮件</th>
                    <th class="tc">电话</th>
					<th class="tc">添加时间</th>
					<th class="tc">操作</th>
                </tr>
                </thead>
                <tbody>
                	<c:forEach items="${contacterList}" var="contacter">
                		<tr>
	                		<td class="tc"><input type="checkbox" class="fid" value="${contacter.id}"></td>
	                		<td class="tc">${contacter.id}</td>
	                		<td class="tc">${contacter.name}</td>
	                		<td class="tc">${contacter.email}</td>
	                		<td class="tc">${contacter.tel}</td>
	                		<td class="tc">${contacter.displayDate}</td>
	                		<td class="tc">
			                    <a href="${contextPath}/contacter.do?action=update&id=${contacter.id}" class="unl">修改</a> |
			                    <a href="${contextPath}/contacter.do?action=delete&id=${contacter.id}" class="unl">删除</a>
			                </td>
		                </tr>
                	</c:forEach>
                </tbody>
                <tfoot>
                <tr>
                    <td colspan="7" class="tf_r">
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
    $(document).ready(function() {
		/*----初始化表格----*/
        var table = new SysTable({id:"table1",sortable:true});

		$("#a_delItem").click(function() {
			var msg = table.getSelItem().length > 0 ? "表单提交。" : "你没有选择任何项目。";
			var items = table.getSelItem();
			var ids = "";
			$.each(items, function(i, item) {
				ids = ids + item.value + ",";
			});
			if (table.getSelItem().length > 0) {
				if(confirm("是否确认执行批量删除操作")){
					$.ajax({
						type : "POST",
						async : false,
						url : contextPath + "/contacter.do?action=deleteAll",
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
				}
			} else {
				new SysAlert({body : msg});
			};
		});
	});

    </script>

</body>
</html>