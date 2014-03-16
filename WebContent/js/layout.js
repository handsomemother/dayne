/**
 * Created by IntelliJ IDEA.
 * Author: Gyare
 * Date: 12-1-5
 */
function initTab(){
    $('#tabbar li').mouseover(function(){
        $(this).addClass('on');
    }).mouseleave(function(){
         $(this).removeClass('on');
    })
}

function initFrameHeight(){
    /*初始化侧边菜单高度*/
    var gap_h = $('#frame_top').height() + 16;
    var w_h = Math.max( $(window).height(),500);
//    alert( $(window).height() );
//    console.log("document.body.scrollHeight:"+$(window).height()+"; w_h:" + w_h + "; gap_h:" + gap_h);
    $('#frame_side').height( w_h - gap_h);  //侧边栏高度
    $('#sector_tree').height( w_h - gap_h -44); //树目录高度
    $('#main_frame').height( w_h - gap_h )  //主体内容高度
}

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
    $('#sidebar li').click(function(){
        $('#sidebar li').removeClass('sel'); /*去除所有li选择样式*/
        $(this).addClass('sel'); /*添加选择样式*/
    })
}

