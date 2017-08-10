<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
 <link rel="stylesheet" type="text/css" href="${path }/css/style.css">
 <link rel="stylesheet" type="text/css" href="${path }/css/sweet-alert.css" />
<script type="text/javascript">
   function sh(taskId){
		$.ajax({
		    type: "POST",
		    url: '${path}/workFlow/complete',
		    data:{taskId:taskId},
		    success: function(data){
		    	if(data.code==0) {		    		
		    		swal({
						title : "审核成功",
						type : "success"// error
				     });
		    	}else{
		    		swal({
						title : data.msg,
						type : "error"// error
				     });
		    	}
		    	
		    },
	});
   }
</script>
<div class="rt_content">
		<div class="page_title">
			<h2 class="fl">待办事项</h2>
		</div>
		<form action="${path}/workFlow/waiting/index" id="searchForm" method="POST">
		   <input type="text" class="textbox textbox_225" placeholder="输入..." name="name" value="${name}"/> 
	       <input type="submit" value="查询" class="group_btn" />&nbsp;&nbsp;&nbsp;&nbsp;
		</form>
		<table class="table">
			<tr>
				<th>ID</th>
				<th>标题</th>
				<th>开始时间</th>
				<th>结束时间</th>
				<th>时间</th>
				<th>进度</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${list.content}" var="rl">
			<tr>
				<td class="center">${rl.id}</td>
				<td>${rl.title}</td>
				<td class="center">${rl.startTime }</td>
				<td class="center">${rl.endTime}</td>
				<td>${rl.dayCount }</td>
				<td>${rl.processName}</td>
				<td class="center">
				 <a href="javascript:void(0)" title="审核" class="link_icon" onclick="sh(${rl.taskId})">&#101;</a> 
			</tr>
			</c:forEach>
		</table>
		<tags:pagination page="${list}" paginationSize="10"/>
	</div>
