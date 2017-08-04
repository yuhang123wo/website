<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
 <link rel="stylesheet" type="text/css" href="${path }/css/style.css">
<script type="text/javascript">
   function toView(){
	   window.location.href="${path}/sys/role/addView";
   }
</script>
	<div class="rt_content">
		<div class="page_title">
			<h2 class="fl">角色管理</h2>
		</div>
		<form action="${path}/sys/role/list" id="searchForm" method="POST">
		   <input type="text" class="textbox textbox_225" placeholder="输入角色名称..." name="name" value="${name}"/> 
	       <input type="submit" value="查询" class="group_btn" />&nbsp;&nbsp;&nbsp;&nbsp;
	       <input type="button" onclick="toView()" class="group_btn" value="新增"/>
		</form>
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
				<td class="center"><fmt:formatDate value="${rl.createTime}" pattern="yyyy/MM/dd HH:mm:ss"/></td>
				<td class="center">
				 <a href="${path}/sys/role/editView/${rl.id}" title="编辑" class="link_icon" 	target="_blank">&#118;</a> 
				 <a href="#" title="删除" class="link_icon">&#100;</a></td>
			</tr>
			</c:forEach>
		</table>
		<tags:pagination page="${roleList}" paginationSize="10"/>
	</div>
	
	<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					 modal
				</h4>
			</div>
			<div>
			<br/>
			<div class="form-horizontal">
                 <div class="form-group">
					<label class="col-sm-2 control-label" for="content">询价需求</label>
					<div class="col-sm-10">
						 <label id="content" class="col-sm-10"></label>
					</div>
				</div>
				  <div class="modal-footer">
				   <button type="button" class="btn btn-default" data-dismiss="modal">关闭
				   </button>
			</div>
            </div> 
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
</div>