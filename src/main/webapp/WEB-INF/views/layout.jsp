<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras"	prefix="tilesx"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setAttribute("path", request.getContextPath());
%>
<tilesx:useAttribute id="list" name="scriptItems" classname="java.util.List" />
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8"/>
<title><tiles:insertAttribute name="title" /></title>
<meta name="author" content="DeathGhost" />
<link rel="stylesheet" type="text/css" href="${path }/css/style.css">
<link rel="stylesheet" type="text/css" href="${path }/css/bootstrap.css">
<!--[if lt IE 9]>
<script src="js/html5.js"></script>
<![endif]-->
<script src="${path }/js/jquery.js"></script>
<script src="${path }/js/jquery-1.11.1.min.js"></script>
<script src="${path }/js/jquery.mCustomScrollbar.concat.min.js"></script>
<c:if test="${list != null }">
	<c:forEach var="item" items="${list}">
<script type="text/javascript" src="${pageContext.request.contextPath}/${item }"></script>
	</c:forEach>
</c:if>
<script>
$(function(){
	var seq = '<tiles:insertAttribute name="seq"></tiles:insertAttribute>';
	if(seq != '-1'){
		var aimMenu = $('.seq[seq="'+seq+'"]');
		if(aimMenu.length > 0){		
			$(aimMenu[0]).addClass('active');
		}
	}
	
})
</script>
</head>
<body>
<!--header-->
<header>
 <h1><img src="images/admin_logo.png"/></h1>
 <ul class="rt_nav">
  <li><a href="http://www.mycodes.net" target="_blank" class="website_icon">站点首页</a></li>
  <li><a href="#" class="set_icon">账号设置</a></li>
  <li><a href="${path}/loginOut" class="quit_icon">安全退出</a></li>
 </ul>
</header>
<!--aside nav-->
<!--aside nav-->
<aside class="lt_aside_nav content mCustomScrollbar">
 <h2><a href="index.html">起始页</a></h2>
 <ul>
  <li>
   <dl>
    <dt>系统管理</dt>
    <!--当前链接则添加class:active-->
    <dd><a href="${path }/sys/role/list" class="seq" seq="10">角色管理</a></dd>
    <dd><a href="${path}/sys/user/list" class="seq" seq="11">用户管理</a></dd>
    <dd><a href="recycle_bin.html">商品回收站示例</a></dd>
   </dl>
  </li>
  <li>
   <dl>
    <dt>系统管理</dt>
    <dd><a href="${path}/workFlow/listWorkFlow" class="seq" seq="20">系统流程</a></dd>
    <dd><a href="${path}/workFlow/leave/index" class="seq" seq="21">请假管理</a></dd>
    <dd><a href="${path}/workFlow/waiting/index" class="seq" seq="22">待办事项</a></dd>
   </dl>
  </li>
 </ul>
</aside>
<section class="rt_wrap content mCustomScrollbar">

 <div class="rt_content">
         <tiles:insertAttribute name="body" />
 </div>
</section>
</body>
</html>
