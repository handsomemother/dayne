/**
 * 设置验证提示信息
 * 
 * @param triggerName
 * @param formValidator
 * @author luohui
 */
$(document).ready( function() {
	$.formValidator.initConfig({
        formid: "form1",
        onerror: function(msg){
            alert(msg)
        },
        onsuccess: function(){
            alert('ddd');
            return false;
        }
    });
    $("#usename").formValidator({
        onshow: "&nbsp",
        onfocus: "账号1-10个字符",
        oncorrect: "账号可用"
    }).inputValidator({
        min: 1,
        max: 10,
        onerror: "账号非法"
    }).regexValidator({
        regexp: "username",
        datatype: "enum",
        onerror: "账号非法"
    });
    
    $("#password1").formValidator({
        onshow: "&nbsp",
        onfocus: "密码不能为空",
        oncorrect: "&nbsp"
    }).inputValidator({
        min: 5,
        empty: {
            leftempty: false,
            rightempty: false,
            emptyerror: "密码错误"
        },
        onerror: "密码不能低于5位"
    });
    $("#password2").formValidator({
        onshow: "&nbsp",
        onfocus: "两次密码必须一致",
        oncorrect: "&nbsp"
    }).inputValidator({
        min: 5,
        empty: {
            leftempty: false,
            rightempty: false,
            emptyerror: "重复密码两边不能有空符号"
        },
        onerror: "重复密码不能为空"
    }).compareValidator({
        desid: "password1",
        operateor: "=",
        onerror: "2次密码不一致"
    });
    //.defaultPassed();
    $("#nl").formValidator({
        onshow: "&nbsp",
        onfocus: "只能输入1-99之间的数字",
        oncorrect: "&nbsp"
    }).inputValidator({
        min: 1,
        max: 99,
        type: "value",
        onerrormin: "你输入的值必须大于等于1",
        onerror: "&nbsp"
    });//.defaultPassed();
    $("#csny").focus(function(){
        WdatePicker({
            skin: 'whyGreen',
            oncleared: function(){
                $(this).blur();
            },
            onpicked: function(){
                $(this).blur();
            }
        })
    }).formValidator({
        onshow: "&nbsp",
        onfocus: "请输入的出生日期，不能全部是0哦",
        oncorrect: "&nbsp"
    }).inputValidator({
        min: "1900-01-01",
        max: "2050-01-01",
        type: "date",
        onerror: "日期必须在\"1900-01-01\"和\"2000-01-01\"之间"
    });//.defaultPassed();
    $("#sfzh").formValidator({
        onshow: "&nbsp",
        onfocus: "输入15或18位的身份证",
        oncorrect: "&nbsp"
    }).functionValidator({
        fun: isCardID
    });
    $("#email").formValidator({
        onshow: "&nbsp",
        onfocus: "邮箱6-100个字符,输入正确了才能离开焦点",
        oncorrect: "&nbsp",
        defaultvalue: "@"
        //   forcevalid: true
    }).inputValidator({
        min: 6,
        max: 100,
        onerror: "你输入的邮箱长度非法,请确认"
    }).regexValidator({
        regexp: "^([\\w-.]+)@(([[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.)|(([\\w-]+.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(]?)$",
        onerror: "你输入的邮箱格式不正确"
    });
    
    $("#ewjy").formValidator({
        onshow: "&nbsp",
        onfocus: "如果你输入\"猫冬\"，额外校验就会成功",
        oncorrect: "&nbsp"
    }).inputValidator({
        min: 1,
        onerror: "这里至少要一个字符,请确认"
    }).functionValidator({
        fun: function(val, elem){
            if (val == "猫冬") {
                return true;
            }
            else {
                return "额外校验失败,想额外校验通过，请输入\"猫冬\""
            }
        }
    });
    $("#Tel_number").formValidator({
        tipid: "teltip",
        onshow: "&nbsp",
        onfocus: "电话号码7到8位数字",
        oncorrect: "&nbsp"
    }).regexValidator({
        regexp: "^\\d{7,8}$",
        onerror: "电话号码不正确"
    });
    $("#Tel_ext").formValidator({
        tipid: "teltip",
        onshow: "请输入分机号码",
        onfocus: "分机号码1到5位数字",
        oncorrect: "&nbsp"
    }).regexValidator({
        regexp: "^\\d{1,5}$",
        onerror: "分机号码不正确"
    });
    
    
    
    $("#shouji").formValidator({
        empty: true,
        onshow: "&nbsp",
        onfocus: "你要是输入了，必须输入正确",
        oncorrect: "&nbsp",
        onempty: "&nbsp"
    }).inputValidator({
        min: 11,
        max: 11,
        onerror: "手机号码必须是11位的,请确认"
    }).regexValidator({
        regexp: "mobile",
        datatype: "enum",
        onerror: "你输入的手机号码格式不正确"
    });

}); 

