<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
 <link rel="stylesheet" type="text/css" href="http://www.jeasyui.net/Public/js/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="http://www.jeasyui.net/Public/js/easyui/themes/icon.css">
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.6.1.min.js"></script>
	<script type="text/javascript" src="http://www.jeasyui.net/Public/js/easyui/jquery.easyui.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${path }/css/sweet-alert.css" />
 <link rel="stylesheet" type="text/css" href="${path }/css/style.css">
 <div class="rt_content">
      <div class="page_title">
       <h2 class="fl">编辑角色</h2>
       <a class="fr top_rt_btn" href="${path}/sys/role/list">返回列表</a>
      </div>
     <section>
      <ul class="ulColumn2">
       <li>
        <span class="item_name" style="width:120px;">角色名：</span>
        <input type="hidden" value="${role.id }" id="roleId">
        <input type="text" class="textbox textbox_295" placeholder="角色名..." id="name" disabled="disabled" value="${role.name}"/>
        <!-- <span class="errorTips"></span> -->
       </li>
       <li>
        <span class="item_name" style="width:120px;">创建者：</span>
        <input type="text" class="textbox textbox_295" value="<shiro:principal property='username'/>" disabled="disabled"/>
        <!-- <span class="errorTips"></span> -->
       </li>
       <li>
        <span class="item_name" style="width:120px;">角色描述：</span>
        <textarea  class="textbox_295" placeholder="角色描述..." cols="5" rows="5" id="remark" >${role.remark}</textarea>
     <!--    <span class="errorTips"></span> -->
       </li>
       <li>
        <span class="item_name" style="width:120px;">权限选择：</span>
         <label class="single_selection textbox_295"><ul id="tt" class="easyui-tree" url="${pageContext.request.contextPath}/sys/role/allMenu?roleId=${role.id}"
			checkbox="true"/></ul></label>
       </li>
       <li>
        <span class="item_name" style="width:120px;"></span>
        <input type="button" class="link_btn" value="提交" onclick="addData();"/>
       </li>
      </ul>
     </section>
 </div>
<script type="text/javascript">
  function addData(){
	  var qx=$('#tt').tree('getChecked');
	  var myArray="";
	  for(var i=0;i<qx.length;i++){
		  myArray+=qx[i].id+",";
	  }
	$.ajax({
		    type: "POST",
		    url: '${path}/sys/role/edit',
		    data:{id:$("#roleId").val(),name:$("#name").val(),permission:myArray},
		    success: function(data){
		    	if(data.code==0) {		    		
		    		swal({
						title : "修改成功",
						type : "success"// error
				     });
		    	   //location.reload();
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