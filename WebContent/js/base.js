/**
 * Created by IntelliJ IDEA.
 * Author: Gyare
 * Date: 12-1-5
 */
/*初始化顶部菜单项交互*/
function initTab(){
    $('#tabbar li').mouseover(function(){
        $(this).addClass('on');
    }).mouseleave(function(){
         $(this).removeClass('on');
    })
}

/*初始化侧边与主体区域高度*/
function initFrameHeight(){
    /*初始化侧边菜单高度*/
    var gap_h = $('#frame_top').height() + 16;
    var w_h = Math.max( $(window).height(),500);
    //console.log("document.body.scrollHeight:"+$(window).height()+"; w_h:" + w_h + "; gap_h:" + gap_h);
    $('#frame_side').height( w_h - gap_h);  //侧边栏高度
    $('#sector_tree').height( w_h - gap_h -44); //树目录高度
    $('#frame_main').height( w_h - gap_h )  //主体内容高度
}

/*初始化侧边菜单项交互*/
function initSidebar(){

    $('#sidebar .unit').click(function(){
        $(this).next('ul').slideToggle('fast');/*缩放子菜单*/
        $(this).find('b').toggle();/*更换标题+-图标*/
    })
    $('#side_toggle').click(function(){
       $(this).toggleClass('toggle_show');/*切换图标*/
       $('#sidebar').toggle();/*显示|隐藏侧边菜单*/
       $('#frame_main').toggleClass('frame_main_w');/*切换主体区域margin-left值*/
    });
    $('#sidebar a').click(function(){
        $('#main_frame').attr('src', $(this).attr('href'));
        $('#sidebar li').removeClass('sel'); /*去除所有li选择样式*/
        $(this).parent().addClass('sel'); /*添加选择样式*/
    })
}

/*ajax提交form表单*/
function ajaxSubmitForm(formId,callback,express) {
	var express = express == undefined ? true : express ;
    var iframe = document.getElementById(formId + "_submitTarget");
    if (!iframe) {
        try{
            iframe = document.createElement("<iframe id='" + formId + "_submitTarget' name='" + formId + "_submitTarget'>");
            //兼容IE67，如此iframe才能被IE当前的DOM树找到
        }catch(e){
            iframe = document.createElement("iframe");
            iframe.id = formId + "_submitTarget";
            iframe.name = formId + "_submitTarget";
        }
        iframe.style.display = "none";
        document.body.appendChild(iframe);
    }
    var count = 0;
    $(iframe).load(function() {
        if(count++){ return };//防止一次监听多次执行
        var svr = this.contentWindow.document.body.innerHTML,//服务器返回json字符串，如{"code":"success"}
		result = express ? eval('(' + svr + ')') : svr; //根据express判断是否对返回结果进行解释
        if (typeof callback == "function") {
            callback.call(callback,result);
        } else {
            alert(svr);
        }
    })

    var form = document.getElementById(formId);
    form.setAttribute("target", formId + "_submitTarget");
    form.submit();
}

//获取包含内补丁的高度和宽度
function getPadHeight(obj) {
    return parseInt(obj.css('paddingTop')) + parseInt(obj.css('paddingBottom'));
}
function getPadWidth(obj) {
    return parseInt(obj.css('paddingLeft')) + parseInt(obj.css('paddingRight'));
}

/*--------------------------------------公共初始化----------------------------------------*/
$(document).ready(function() {
    initFrameHeight();//初始化页面框架高度
    initSidebar();//初始化侧边菜单
})
window.onresize = function() {
    initFrameHeight();//重定义页面框架高度
}
