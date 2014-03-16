$(document).ready(function() {
	$.formValidator.initConfig({
		formid : "sysForm",
		onerror : function(msg) {
			alert(msg);
			return false;
		}
	});

	$("#name").formValidator({
		onshow : " ",
		oncorrect : " "
	}).inputValidator({
		min : 1,
		max : 20,
		onerrormax : "用户名字过长",
		onerrormin : "用户名字不能为空"	
	});

	$("#userName").formValidator({
		onshow : " ",
		oncorrect : " "
	}).inputValidator({
		min : 1,
		max : 20,
		onerrormax : "账号过长",
		onerrormin : "账号不能为空"	
	}).ajaxValidator(
			{
				datatype : "text",
				async : true,
				url : contextPath
						+ "/userInfo.do?action=userNameValidate&id="
						+ $("#id").val(),
				success : function(data) {
					if (data == 'true') {
						return true;
					} else {
						return false;
					}
				},
				error : function(jqXHR, textStatus,
						errorThrown) {
					alert("服务器没有返回数据，可能服务器忙，请重试"
							+ errorThrown);
				},
				onerror : "该用户名不可用，请更换用户名",
				onwait : "正在对用户名进行合法性校验，请稍候..."
			}).defaultPassed();
	
	$("#passWord").formValidator({
		onshow : " ",
		oncorrect : " "
	}).inputValidator({
		min : 1,
		empty : {
			leftempty : false,
			rightempty : false,
			emptyerror : "密码两边不能有空符号"
		},
		onerror : "密码不能为空"
	});
	
	$("#passWordVerify").formValidator({
		onshow : " ",
		oncorrect : " "
	}).inputValidator({
		min : 1,
		empty : {
			leftempty : false,
			rightempty : false,
			emptyerror : "重复密码两边不能有空符号"
		},
		onerror : "重复密码不能为空"
	}).compareValidator({
		desid : "passWord",
		operateor : "=",
		onerror : "2次密码不一致"
	});
});
