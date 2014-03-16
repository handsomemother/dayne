<%@ page language="java" contentType="text/html; charset=utf-8" %>
<!-- 引入公共的js库  -->
<script type="text/javascript" src="${contextPath}/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${contextPath}/js/dialog.js"></script>
<script type="text/javascript" src="${contextPath}/js/table.js"></script>
<script type="text/javascript" src="${contextPath}/js/base.js"></script>
<script type="text/javascript" src="${contextPath}/js/dataChangeList.js"></script>
<script type="text/javascript" src="${contextPath}/component/datepicker/WdatePicker.js"></script>
<!-- 校验 -->
<script type="text/javascript" src="${contextPath}/component/formValidator/js/formValidator.js" ></script>
<script type="text/javascript" src="${contextPath}/component/formValidator/js/formValidatorRegex.js"></script>

<script>
	<!-- 定义全局的项目路径变量给js使用-->
	var pathName=window.document.location.pathname;
	var contextPath = pathName.substring(0,pathName.substr(1).indexOf('/')+1);
</script> 