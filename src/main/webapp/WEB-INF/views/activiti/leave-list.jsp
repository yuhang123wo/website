<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
 <link rel="stylesheet" type="text/css" href="${path }/css/style.css">
<script type="text/javascript">
   function toView(){
	   window.location.href="${path}/workFlow/leave/addView";
   }
</script>
<div class="rt_content">
		<div class="page_title">
			<h2 class="fl">请假管理</h2>
		</div>
		<form action="${path}/workFlow/leave/index" id="searchForm" method="POST">
		   <input type="text" class="textbox textbox_225" placeholder="输入流程名..." name="name" value="${name}"/> 
	       <input type="submit" value="查询" class="group_btn" />&nbsp;&nbsp;&nbsp;&nbsp;
	       <input type="button" onclick="toView()" class="group_btn" value="请假"/>
		</form>
		<table class="table">
			<tr>
				<th>ID</th>
				<th>标题</th>
				<th>内容</th>
				<th>请假时间(小时)</th>
				<th>开始时间</th>
				<th>结束时间</th>
				<th>创建时间</th>
				<th>状态</td>
				<th>操作</th>
			</tr>
			<c:forEach items="${list.content}" var="rl">
			<tr>
				<td class="center">${rl.id}</td>
				<td class="center">${rl.title}</td>
				<td class="center">${rl.content}</td>
				<td class="center">${rl.dayCount}</td>
				<td class="center">${rl.startTime }</td>
				<td class="center">${rl.endTime}</td>
				<td class="center"><fmt:formatDate value="${rl.createTime}" pattern="yyyy/MM/dd HH:mm:ss"/></td>
				<td></td>
				<td class="center">
				 <a href="javascript:void(0)" title="详细" class="link_icon">&#118;</a></td>
			</tr>
			</c:forEach>
		</table>
		<tags:pagination page="${list}" paginationSize="10"/>
	</div>
