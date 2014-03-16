<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/includes/inc_envSetup.jsp" %>
<%@ include file="/includes/inc_js.jsp" %>
<%@ include file="/includes/inc_css.jsp" %>
<html>
<head>
    <title>System Layout 1</title>
</head>
<body>
    <div class="pr10">
        <div class="hr"></div>
        <div class="std_filter">
        </div>
        <form id="sysForm" action="" method="post" enctype="" class="std_form">
            <h3 class="caption">模板设置</h3>
			<p >
                <label class="lab">模板名称：</label>
                <input class="ipt_txt" type="text"/>
            </p>
           
            <p class="p20">
                <!--<label class="lab">祝福语内容：</label>-->
                <textarea rows="" cols="" class="tarea" style="width: 50%;"></textarea><br/>
                <span class="t_tip lh180" >当前已经输入45字，最多输入400字。</span>
            </p>
            <div class="hr mb20"></div>
            <p class="b_area">
                <input type="submit" value="保存" class="b_btn b_btn_deep"/> <input type="submit" value="查找" class="b_btn b_btn_deep"/>
            </p>
        </form>
    </div>
	<div>
	        <div class="std_ctrl">
            <a href="#" id="a_addItem"><b class="s_ico s_ico_add"></b>批量删除</a> |
        </div>
        <div class="std_tdiv">
            <table class="std_table" border="1" id="table1" >
			<thead>
                <tr>
                    <th class="col0 tc"><input type="checkbox" class="fid" value=""></th>
                    <th class="col1 sort">编号<b class="s_ico s_ico_sor_nev"></b></th>
                    <th class="col3 sort">名称<b class="s_ico s_ico_sor_nev"></b></th>
                    <th class="col2 sort">内容<b class="s_ico s_ico_sor_nev"></b></th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td class="col0 tc"><input type="checkbox" class="fid" value="" ></td>
                    <td>0011</td>
                    <td title="政务服务接口"><a href="staff_userDetail.html">政务服务接口</a></td>
                    <td>http://xxxx.abc.com</td>
                </tr>
				
                
                </tbody>
                
                <tfoot>
                
                </tfoot>
            </table>
        </div>
    </div>


</body>
</html>