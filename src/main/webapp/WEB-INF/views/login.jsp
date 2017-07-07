<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<title>后台登录</title>
<meta name="author" content="DeathGhost" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
<style>
body {
	height: 100%;
	background: #16a085;
	overflow: hidden;
}

canvas {
	z-index: -1;
	position: absolute;
}
</style>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/verificationNumbers.js?v=0.2"></script>
<script src="${pageContext.request.contextPath}/js/Particleground.js"></script>
<script>
$(document).ready(function() {
  //粒子背景特效
  $('body').particleground({
    dotColor: '#5cbdaa',
    lineColor: '#5cbdaa'
  });
  //验证码
  validate();
});

function validate(){
	  $.ajax({
	     type: 'POST',
	     url: '${pageContext.request.contextPath}/captcha',
	     data: {a:'b'},
	     dataType : 'json',
	     success:function(data){
	    	 createCode(data);
	     }
	   });
	 }
</script>
</head>
<body>
<form action="${path}login" method="post">
<dl class="admin_login">
 <dt>
  <strong>后台管理系统</strong>
  <em>Management System</em>
 </dt>
  <dt>
  <em><font color="red">${msg }</font></em>
 </dt>
 <dd class="user_icon">
  <input type="text" placeholder="账号" class="login_txtbx" name="username" value="${username}"/>
 </dd>
 <dd class="pwd_icon">
  <input type="password" placeholder="密码" class="login_txtbx" name="password"/>
 </dd>
 <dd class="val_icon">
  <div class="checkcode">
    <input type="text" name="captcha" placeholder="验证码" maxlength="4" class="login_txtbx">
    <canvas class="J_codeimg" id="myCanvas" onclick="validate()">对不起，您的浏览器不支持canvas，请下载最新版浏览器!</canvas>
  </div>
  <input type="button" value="验证码刷新" class="ver_btn" onClick="validate();">
 </dd>
 <dd>
  <input type="submit" value="立即登录" class="submit_btn"/>
 </dd>
 <dd>
  <p>© 版权所有</p>
  <p>20200224-1</p>
 </dd>
</dl>
</form>
</body>
</html>
