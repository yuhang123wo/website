<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
 <link rel="stylesheet" type="text/css" href="${path }/css/style.css">
<div class="rt_content">
		<div class="page_title">
			<h2 class="fl">角色管理</h2>
		</div>
		<form action="${path}/workFlow/listWorkFlow" id="searchForm" method="POST">
		   <input type="text" class="textbox textbox_225" placeholder="输入..." name="name" value="${name}"/> 
	       <input type="submit" value="查询" class="group_btn" />&nbsp;&nbsp;&nbsp;&nbsp;
		</form>
		<table class="table">
			<tr>
				<th>ID</th>
				<th>流程名</th>
				<th>说明</th>
				<th>创建时间</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${list.content}" var="rl">
			<tr>
				<td class="center">${rl.id}</td>
				<td>${rl.name}</td>
				<td class="center">${rl.remark }</td>
				<td class="center"><fmt:formatDate value="${rl.createTime}" pattern="yyyy/MM/dd HH:mm:ss"/></td>
				<td>${rl.state }</td>
				<td class="center">
				 <a href="javascript:void(0)" title="编辑" class="link_icon">&#101;</a> 
			</tr>
			</c:forEach>
		</table>
		<tags:pagination page="${list}" paginationSize="10"/>
	</div>
