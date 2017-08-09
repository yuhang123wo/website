<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
  <link rel="stylesheet" type="text/css" href="${path }/css/sweet-alert.css" />
  <link rel="stylesheet" type="text/css" href="${path }/css/style.css">
 <div class="rt_content">
      <div class="page_title">
       <h2 class="fl">请假</h2>
       <a class="fr top_rt_btn" href="${path}/workFlow/leave/index">返回列表</a>
      </div>
      <form action="POST" id="dataForm" >
     <section>
      <ul class="ulColumn2">
       <li>
        <span class="item_name" style="width:120px;">标题：</span>
        <input type="text" class="textbox textbox_295" placeholder="输入标题..." id="title" name="title"/>
        <!-- <span class="errorTips"></span> -->
       </li>
       <li>
        <span class="item_name" style="width:120px;">开始时间：</span>
        <input type="text" class="textbox textbox_295" placeholder="开始时间"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH'})" name="startTime" >
        <!-- <span class="errorTips"></span> -->
       </li>
         <li>
        <span class="item_name" style="width:120px;">结束时间：</span>
        <input type="text" class="textbox textbox_295" placeholder="结束时间" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH'})" name="endTime">
        <!-- <span class="errorTips"></span> -->
       </li>
       <li>
        <span class="item_name" style="width:120px;">请假事由：</span>
        <textarea  class="textbox_295" placeholder="请假事由..." cols="5" rows="5" id="content" name="content"></textarea>
     <!--    <span class="errorTips"></span> -->
       </li>
       <li>
        <span class="item_name" style="width:120px;"></span>
        <input type="button" class="link_btn" value="提交" onclick="addData();"/>
       </li>
      </ul>
     </section>
     </form>
 </div>
<script type="text/javascript">
  function addData(){
	var parray = $("#dataForm").serialize();
	$.ajax({
		    type: "POST",
		    url: '${path}/workFlow/leave/add',
		    data:parray,
		    success: function(data){
		    	if(data.code==0) {		    		
		    		swal({
						title : "新增成功",
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