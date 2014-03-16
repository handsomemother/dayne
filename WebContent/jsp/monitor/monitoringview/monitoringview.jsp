<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/includes/inc_envSetup.jsp" %>
<%@ include file="/includes/inc_js.jsp" %>
<%@ include file="/includes/inc_css.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>监控系统</title>
    <!-- 
     <style>
      body {
        text-align: center;
		font-family: Arial;
      }
      
      #g1 {
        width:400px; height:320px;
        display: inline-block;
        margin: 1em;
		border: 1px soild #202020;
		box-shadow: 0px 0px 15px #101010;
		margin-top: 120px;
		border-radius: 8px;
      }
      
      p {
        display: block;
        width: 400px;
        margin: 2em auto;
        text-align: center;
		border-top: 1px soild #CCC;
		border-bottom: 1px soild #CCC;
		background: #333333;
		padding:10px 0px;
		color: #CCC;
		text-shadow: 1px 1px 25px #000000;
		border-radius: 0px 0px 5px 5px;
		box-shadow: 0px 0px 10px #202020;
      }
    </style>
     -->
    <script src="${contextPath}/js/justGage/raphael.2.1.0.min.js"></script>
    <script src="${contextPath}/js/justGage/justgage.1.0.1.min.js"></script>
    <script>
      var g1;
      window.onload = function(){
        var g1 = new JustGage({
          id: "g1", 
          value: getRandomInt(0, 30), 
          min: 0,
          max: 100,
          title: "Speedometer",
          label: "km/h",
		  levelColors: [ "#222222","#555555","#CCCCCC"]    
        });
      
        setInterval(function() {
          g1.refresh(getRandomInt(0, 80));
        }, 800);
      };
    </script>
</head>
<body>
    <div class="pr10">
		 <div id="g1"></div>
		 <div id="g1"></div>
    </div>
</body>
</html>