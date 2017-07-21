<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
	<link rel="stylesheet" type="text/css" href="http://www.jeasyui.net/Public/js/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="http://www.jeasyui.net/Public/js/easyui/themes/icon.css">
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.6.1.min.js"></script>
	<script type="text/javascript" src="http://www.jeasyui.net/Public/js/easyui/jquery.easyui.min.js"></script>
</head>
<div>
	<ul id="tt" class="easyui-tree"	url="${pageContext.request.contextPath}/test/testm"
			checkbox="true">
	</ul>
	<input type="button" onclick="m()" value="点击"/>
</div>
<script>
 function m(){
	 console.log($('#tt').tree('getChecked'));
	 
 }
</script>