<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/includes/inc_envSetup.jsp" %>
<%@ include file="/includes/inc_js.jsp" %>
<%@ include file="/includes/inc_css.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>监控系统</title>
</head>
<body >
<form:form id="search" class="std_form" action="${contextPath}/sourceLog.do?action=list" method="post" commandName="criteria">
    <div class="pr10">
        <div class="hr"></div>
        <h3 class="caption">数据源日志列表</h3>
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
					<label class="lab">访问频率：</label>
					<form:select path="monitorLevel" cssClass="sel">
						<form:option value="0">不限</form:option>
						<form:option value="1">高</form:option>
						<form:option value="2">中</form:option>
						<form:option value="3">低</form:option>
					</form:select>
                </p>
				<p>
					<label class="lab">监控类别：</label>
					<form:select path="type" cssClass="sel">
						<form:option value="0">不限</form:option>
						<form:option value="1">电信接口</form:option>
						<form:option value="2">REST接口</form:option>
						<form:option value="3">WS接口</form:option>
						<form:option value="4">数据库</form:option>
						<form:option value="5">网页</form:option>
					</form:select> 
					<label class="lab">接口状态：</label>
					<form:select path="status" cssClass="sel">
						<form:option value="">不限</form:option>
						<form:option value="1">正常</form:option>
						<form:option value="0">异常</form:option>
					</form:select>
					<label class="lab">记录时间：</label>
					<span class="ipt_extxt"> 
						<form:input path="startTime" id="startTime"/><b class="x_ico x_ico_date"></b>
					</span>
					-
					<span class="ipt_extxt"> 
						<form:input path="endTime" id="endTime"/><b class="x_ico x_ico_date"></b>
					</span>
					<input type="button" class="btn btn_deep" value="查询" onclick="searchSubmit();"/> 
				</p>
        </div>
        <div class="std_ctrl">
            <a href="#" id="a_delItem"><b class="s_ico s_ico_del"></b>批量删除</a>
        </div>
        <div class="std_tdiv">
            <table class="std_table" border="1" id="table1" >
				<thead>
                <tr>
                    <th class="col0 tc"><input type="checkbox" class="fid" value=""></th>
                    <th class="col1 tc sort" sortable="true" datatype="num">编号<b class="s_ico s_ico_sor_nev"></b></th>
                    <th class="col2 tc">监控源名称</th>
                    <th class="col2 tc">所属系统</th>
                    <th class="col2 tc">记录时间</th>
                    <th class="col2 tc">监控地址</th>
                    <th class="col2 tc">开始时间</th>
					<th class="col2 tc">结束时间</th>
					<th class="col3 tc">响应速度</th>
					<th class="col2 tc">返回结果</th>
                    <th class="col2 tc">监控类别</th>
                    <th class="col1 tc">状态</th>
                    <th class="col1 tc">操作</th>
                </tr>
                </thead>
                <tbody>
                	<c:forEach items="${sourceLogList}" var="sourceLog">
                		<tr>
                			<td class="col0 tc"><input type="checkbox" class="fid" value="${sourceLog.id}"></td>
                			<td class="tc">${sourceLog.id}</td>
                			<td class="tc">${sourceLog.sourceName}</td>
		                    <td class="tc">${sourceLog.displaySys}</td>
		                    <td class="tc"><fmt:formatDate value="${sourceLog.logTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		                    <td class="tc" title="${sourceLog.urlAddress}">${sourceLog.urlAddress}</td>
		                    <td class="tc"><fmt:formatDate value="${sourceLog.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td class="tc"><fmt:formatDate value="${sourceLog.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td>
		                        <div class="load">
		                        	<c:choose>
		                        		<c:when test="${sourceLog.recentlyResponseTime > sourceLog.responseTime }">
			                        		<span class="l_con linb">
					                            <span style="width:100%;" class="l_in l_in_err"> </span>
					                        </span>
		                        		</c:when>
		                        		<c:otherwise>
		                        			<span class="l_con linb">
		                                		<span style="width:${(sourceLog.recentlyResponseTime/sourceLog.responseTime)*100}%;" class="l_in"></span>
		                            		</span>
		                        		</c:otherwise>
		                        	</c:choose>
		                            <span class="linb l_tp">${sourceLog.recentlyResponseTime}s</span>
		                        </div>
		                    </td>
							<td class="tc" title="${sourceLog.result}">${sourceLog.result}</td>
		                    <td class="tc">
		                        ${sourceLog.displayType}
		                    </td>
		                    <td class="tc">
		                        <c:choose>
		                    		<c:when test="${sourceLog.status==1}">
		                    			<a href="javascript:void(0);" class="state_on" title="正常"></a>
		                    		</c:when>
		                    		<c:otherwise>
		                    			<a href="javascript:void(0);" class="state_off" title="异常"></a>
		                    		</c:otherwise>
		                    	</c:choose>
		                    </td>
		                     <td class="tc">
		                    	<a href="${contextPath}/sourceLog.do?action=view&id=${sourceLog.id}">查看</a>
		                   	 </td>
                		</tr>
                	</c:forEach>
                
                </tbody>
                <tfoot>
                	<tr>
						<td colspan="13" class="tf_r">
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
						url : contextPath + "/sourceLog.do?action=deleteAll",
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
		//初始化日期选择
		$('#startTime').focus(function() {
			WdatePicker({
				dateFmt : 'yyyy-MM-dd HH:mm',
				alwaysUseStartDate : true
			});
		});
		$('#endTime').focus(function() {
			WdatePicker({
				dateFmt : 'yyyy-MM-dd HH:mm',
				alwaysUseStartDate : true
			});
		});
		
	});
</script>

</body>
</html>