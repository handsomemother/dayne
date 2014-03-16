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
<form:form id="search" action="${contextPath}/source.do?action=list" method="post" class="std_form" commandName="criteria">
    <div class="pr10">
		<div class="hr"></div>
		<h3 class="caption">数据源列表</h3>
        <div class="std_filter">
			<p>
				<label class="lab">监控源名称：</label>
				<form:input path="sourceName" cssClass="ipt_txt"/>
				<label class="lab">所属系统：</label>
				<form:select path="sysCode" class="sel">
					<form:option value="0">不限</form:option>
					<form:option value="1">智慧家庭服务平台</form:option>
					<form:option value="2">智慧家居控制平台</form:option>
				</form:select>
				<label class="lab">接口状态：</label>
				<form:select path="status" cssClass="sel">
					<form:option value="">不限</form:option>
					<form:option value="1">正常</form:option>
					<form:option value="0">异常</form:option>
				</form:select>	
            </p>
			<p>
				<label class="lab">监控频率：</label>
				<form:select path="monitorLevel" cssClass="sel">
					<form:option value="0">不限</form:option>
					<form:option value="1">高</form:option>
					<form:option value="2">中</form:option>
					<form:option value="3">低</form:option>
				</form:select>
				<label class="lab">监控类别：</label>
				<form:select path="type" cssClass="sel">
					<form:option value="0">不限</form:option>
					<form:option value="1">电信接口</form:option>
					<form:option value="2">REST接口</form:option>
					<form:option value="3">WS接口</form:option>
					<form:option value="4">数据库</form:option>
					<form:option value="5">网页</form:option>
				</form:select> 
				<!-- js 方法在分页jsp中 -->
				<label class="lab"></label>
				<input type="button" class="btn btn_deep" value="查询" onclick="searchSubmit();"/> 
			</p>
        </div>
        <div class="std_ctrl">
             
            
            <a href="${contextPath}/source.do?action=create" id="a_addItem">
            	<b class="s_ico s_ico_add"></b>添加监控源
            </a>|
            <a href="#" id="a_delItem"><b class="s_ico s_ico_del"></b>批量删除</a>
        </div>
        <div class="std_tdiv">
            <table id="table1" class="std_table" border="1" >
			<thead>
                <tr>
                    <th class="col0 tc"><input type="checkbox" class="fid" value=""></th>
                    <th class="col4 sort" sortable="true" datatype="num" >编号<b class="s_ico s_ico_sor_nev"></b></th>
                    <th class="col2 tc">监控源名称</th>
                    <th class="col2 tc">监控地址</th>
                    <th class="col1 tc">所属系统</th>
                    <th class="col6 tc">接口类型</th>
                    <th class="col5 tc">监控频率</th>
                    <th class="col2 tc">响应速度</th>
                    <th class="col6 tc">状态</th>
                    <th class="col1 tc">操作</th>
                </tr>
                </thead>
                <tbody>
	                <c:forEach items="${sourceList}" var="source">
	        			<tr>
		                    <td class="tc"><input type="checkbox" class="fid" value="${source.id}"></td>
		                    <td class="tc">${source.id}</td>
		                    <td class="tc" title="${source.sourceName}">${source.sourceName}</td>
		                    <td class="tc" title="${source.urlAddress}">${source.urlAddress}</td>
		                    <td class="tc">${source.displaySys}</td>
		                    <td class="tc">${source.displayType}</td>
		                    <td class="tc">${source.displayMonitorLevel}</td>
		                    <td>
								<div class="load">
		                        	<c:choose>
		                        		<c:when test="${source.recentlyResponseTime > source.responseTime }">
			                        		<span class="l_con linb">
					                            <span style="width:100%;" class="l_in l_in_err"> </span>
					                        </span>
		                        		</c:when>
		                        		<c:otherwise>
		                        			<span class="l_con linb">
		                                		<span style="width:${(source.recentlyResponseTime / source.responseTime)*100}%;" class="l_in"> </span>
		                            		</span>
		                        		</c:otherwise>
		                        	</c:choose>
		                            <span class="linb l_tp">${source.recentlyResponseTime}s</span>
		                        </div>
							</td>
		                    <td class="tc">
		                    	<c:choose>
		                    		<c:when test="${source.status==1 }">
		                    			<a href="javascript:void(0);" class="state_on" title="正常"></a>
		                    		</c:when>
		                    		<c:otherwise>
		                    			<a href="javascript:void(0);" class="state_off" title="异常"></a>
		                    		</c:otherwise>
		                    	</c:choose>
		                    </td>
		                    <td class=" tc">
		                        <a href="${contextPath}/source.do?action=view&id=${source.id}" class="unl">查看</a> |
		                        <a href="${contextPath}/source.do?action=update&id=${source.id}" class="unl">修改</a> |
		                        <a href="${contextPath}/source.do?action=delete&id=${source.id}" class="unl">删除</a>
		                    </td>
	               		</tr>
	        		</c:forEach>
                </tbody>
                <tfoot>
                	<tr>
						<td colspan="10" class="tf_r">
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
		var table = new SysTable({
			id : "table1",
			sortable : true
		});

		$("#a_delItem").click(function() {
			var msg = table.getSelItem().length > 0 ? "表单提交。" : "你没有选择任何项目。";
			var items = table.getSelItem();
			var ids = "";
			$.each(items, function(i, item) {
				ids = ids + item.value + ",";
			});
			if (table.getSelItem().length > 0) {
				if (confirm("是否确认执行批量删除操作")){
					$.ajax({
						type : "POST",
						async : false,
						url : contextPath + "/source.do?action=deleteAll",
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