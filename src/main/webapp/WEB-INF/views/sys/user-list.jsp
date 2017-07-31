<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<script type="text/javascript">
   function toView(){
	   window.location.href="${path}/sys/role/addView";
   }
</script>
	<div class="rt_content">
		<div class="page_title">
			<h2 class="fl">用户管理</h2>
		</div>
		<form action="${path}/sys/role/list" id="searchForm" method="POST">
		   <input type="text" class="textbox textbox_225" placeholder="用户名..." name="username" value="${username}"/> 
	       <input type="submit" value="查询" class="group_btn" />&nbsp;&nbsp;&nbsp;&nbsp;
	       <input type="button" onclick="toView()" class="group_btn" value="新增"/>
		</form>
		<table class="table">
			<tr>
				<th>序号</th>
				<th>用户名</th>
				<th>真实名</th>
				<th>头像</th>
				<th>联系电话</th>
				<th>邮箱</th>
				<th>性别</th>
				<th>地址</th>
				<th>生日</th>
				<th>部门</th>
				<th>创建时间</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${userList.content}" var="rl">
			<tr>
				<td class="center">${rl.id}</td>
				<td>${rl.username }</td>
				<td class="center">${rl.realname }</td>
				<td class="center">${rl.img }</td>
				<td class="center">${rl.mobile }</td>
				<td class="center">${rl.email }</td>
				<td class="center">${rl.sex }</td>
				<td class="center">${rl.address }</td>
				<td class="center">${rl.birthday }</td>
				<td class="center">${rl.deptId }</td>
				<td class="center"><fmt:formatDate value="${rl.createTime}" pattern="yyyy/MM/dd HH:mm:ss"/></td>
				<td class="center">
				 <a href="${path}/sys/role/editView/${rl.id}" title="编辑" class="link_icon" 	target="_blank">&#118;</a> 
				 <a href="#" title="删除" class="link_icon">&#100;</a></td>
			</tr>
			</c:forEach>
		</table>
		<tags:pagination page="${userList}" paginationSize="10"/>
	</div>
</div>
</div>