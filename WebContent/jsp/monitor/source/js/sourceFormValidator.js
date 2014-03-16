$(document).ready(function() {
	$.formValidator.initConfig({
		formid : "sysForm",
		onerror : function(msg) {
			alert(msg);
			return false;
		}
	});

	$("#sourceName").formValidator({
		onshow : " ",
		oncorrect : " "
	}).inputValidator({
		min : 1,
		max : 30,
		onerrormax : "数据源名字过长",
		onerrormin : "数据源名字不能为空"
	});

	$("#urlAddress").formValidator({
		onshow : " ",
		oncorrect : " "
	}).inputValidator({
		min : 1,
		max : 350,
		onerrormax : "监控源url地址过长",
		onerrormin : "监控源url地址不能为空"
	}).regexValidator({
		regexp : "(http|https|ftp)://[^s]*",
		dataType:"string",
		onerror : "输入URL格式有误"
	});
	
	$("#responseTime").formValidator({
		onshow : " ",
		oncorrect : " "
	}).inputValidator({
		min : 1,
		onerror : "正常响应时间不能为空"
	});
	
	$("#emailInterval").formValidator({
		onshow : " ",
		oncorrect : " "
	}).inputValidator({
		min : 1,
		onerror : "邮件发送间隔不能为空"
	});
	
	$("#groupId").formValidator({
		onshow : " ",
		oncorrect : " "
	}).inputValidator({
		min : 0,
		onerror : "联系人组不能为空"
	});
	
	$("#remark").formValidator({
		onshow : " ",
		oncorrect : " "
	}).inputValidator({
		max : 300,
		onerror : "备注信息过长,不能超过300字符"
	});
});
