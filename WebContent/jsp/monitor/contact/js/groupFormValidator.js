$(document).ready(function() {
	$.formValidator.initConfig({
		formid : "sysForm",
		onerror : function(msg) {
			//alert(msg);
			return false;
		}
	});
	$("#groupName").formValidator({
		onshow : " ",
		oncorrect : " "
	}).inputValidator({
		min : 1,
		max : 30,
		onerrormax : "联系人组名字过长",
		onerrormin : "联系人组名字不能为空"
	});
	
	$("#remark").formValidator({
		onshow : " ",
		oncorrect : " "
	}).inputValidator({
		max : 150,
		onerror : "备注信息过长,不能超过150字符"
	});
});
