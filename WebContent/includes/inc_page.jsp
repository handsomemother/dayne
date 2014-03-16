<%@ page language="java" contentType="text/html; charset=utf-8" %>

<input type="hidden" id="page" name="page"/>

<span>共${criteria.totalNum}条记录</span>

<c:if test="${criteria.page>1}"><a href="#" onclick="first();"></c:if>
<span>首页</span>
<c:if test="${criteria.page>1}"></a></c:if>
                        
<c:if test="${criteria.page>1}"><a href="#" onclick="pre();"></c:if>
<span>上一页</span>
<c:if test="${criteria.page>1}"></a></c:if>
                        
<c:if test="${criteria.page<criteria.totalPage}"><a href="#" onclick="next();"></c:if>
<span>下一页</span>
<c:if test="${criteria.page<criteria.totalPage}"></a></c:if>
        
<c:if test="${criteria.page<criteria.totalPage}"><a href="#" onclick="end();"></c:if>
<span>末页</span>
<c:if test="${criteria.page<criteria.totalPage}"></a></c:if>
        
<select onchange="goToPage();" id="toPage">
	<c:forEach var="i"  begin="1" end="${criteria.totalPage}">
		<option value="${i}" <c:if test="${i==criteria.page}">selected</c:if>>${i}/${criteria.totalPage}</option>
	</c:forEach>
</select>
 
<script>
function pre(){
	var page = ${criteria.page};
	$("#page").attr("value",page-1);
	$("#search").submit();
}

function next(){
	var page = ${criteria.page};
	$("#page").attr("value",page+1);
	$("#search").submit();
}

function goToPage(){
	$("#page").attr("value",$("#toPage").val());
	$("#search").submit();
}

function first(){
	$("#page").attr("value",1);
	$("#search").submit();
}

function end(){
	var totalPage = ${criteria.totalPage};
	$("#page").attr("value",totalPage);
	$("#search").submit();
}

function searchSubmit(){
	$("#page").attr("value",1);
	$("#search").submit();
}
</script> 

