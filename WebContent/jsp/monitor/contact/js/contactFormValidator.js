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
		onerrormax : "联系人名字过长",
		onerrormin : "联系人名字不能为空"
	});
	
	$("#email").formValidator({
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
	
//	$("#tel").formValidator({
//		onshow : " ",
//		oncorrect : " "
//	}).inputValidator({
//		min : 1,
//		onerror : "手机号码不能为空"
//	}).regexValidator({
//		regexp : "^(13|15|18)[0-9]{9}$",
//		onerror : "您输入手机号码非法"
//	});
	
});
