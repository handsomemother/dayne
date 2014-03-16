$(document).ready(function() {
	$.formValidator.initConfig({
		formid : "sysForm",
		onerror : function(msg) {
			alert(msg);
			return false;
		},
		onsuccess : function(msg){
			return true;
		}
	});

	$("#mailServerHost").formValidator({
		onshow : " ",
		oncorrect : " "
	}).inputValidator({
		min : 1,
		max : 100,
		onerrormax : "邮件服务器地址过长",
		onerrormin : "邮件服务器地址不能为空"	
	});

	$("#mailServerPort").formValidator({
		onshow : " ",
		oncorrect : " "
	}).inputValidator({
		min : 1,
		max : 20,
		onerrormax : "邮件服务器端口过长",
		onerrormin : "邮件服务器端口不能为空"	
	});
	
	$("#sysEmailAddress").formValidator({
		onshow : " ",
		oncorrect : " "
	}).inputValidator({
		min : 6,
		max : 100,
		onerror : "您输入的邮箱非法"
	}).regexValidator({
		regexp : "^([\\w-.]+)@(([[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.)|(([\\w-]+.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(]?)$",
		onerror : "您输入的邮箱格式不正确"
	});
	
	$("#emailPassWord").formValidator({
		onshow : " ",
		oncorrect : " "
	}).inputValidator({
		min : 1,
		onerror : "邮箱密码不能为空"
	});
	
	
});
