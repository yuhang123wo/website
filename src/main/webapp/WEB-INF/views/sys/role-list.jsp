<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
	<div class="rt_content">
		<div class="page_title">
			<h2 class="fl">产品回收站示例</h2>
			<a class="fr top_rt_btn">返回上一页</a>
		</div>
		<section class="mtb">
			<select class="select">
				<option>下拉菜单</option>
				<option>菜单1</option>
			</select> <input type="text" class="textbox textbox_225"
				placeholder="输入产品关键词或产品货号..." /> <input type="button" value="查询"
				class="group_btn" />
		</section>
		<table class="table">
			<tr>
				<th>ID</th>
				<th>角色名</th>
				<th>说明</th>
				<th>创建时间</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${roleList.content}" var="rl">
			<tr>
				<td class="center">${rl.id}</td>
				<td>${rl.name }</td>
				<td class="center">${rl.remark }</td>
				<td class="center">${rl.createTime }</td>
				<td class="center">
				 <a href="#" title="详情" class="link_icon" 	target="_blank">&#118;</a> 
				 <a href="#" title="删除" class="link_icon">&#100;</a></td>
			</tr>
			</c:forEach>
		</table>
		<tags:pagination page="${roleList}" paginationSize="10"/>
	</div>