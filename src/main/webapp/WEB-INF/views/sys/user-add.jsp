<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
 <link rel="stylesheet" type="text/css" href="${path }/css/style.css">
 <div class="rt_content">
      <div class="page_title">
       <h2 class="fl">添加用户</h2>
       <a class="fr top_rt_btn" href="${path}/sys/user/list">返回列表</a>
      </div>
     <section>
      <ul class="ulColumn2">
       <li>
        <span class="item_name" style="width:120px;">用户名：</span>
        <input type="text" class="textbox textbox_295" placeholder="用户名..." name="username"/>
        <!-- <span class="errorTips"></span> -->
       </li>
       <li>
        <span class="item_name" style="width:120px;">密码：</span>
        <input type="password" class="textbox textbox_295" name="password" placeholder="密码..."/>
        <!-- <span class="errorTips"></span> -->
       </li>
        <li>
        <span class="item_name" style="width:120px;">确认密码：</span>
        <input type="password" class="textbox textbox_295" name="passwordRe" placeholder="确认密码..."/>
        <!-- <span class="errorTips"></span> -->
       </li>
       <li>
        <span class="item_name" style="width:120px;">部门：</span>
        <select class="select">
         <option>请选择所属部门</option>
         <option>部门2</option>
         <option>部门3</option>
        </select>
       </li>
       <li>
        <span class="item_name" style="width:120px;">真实姓名：</span>
        <input type="realname" class="textbox textbox_295" name="realname" placeholder="真实姓名..."/>
        <!-- <span class="errorTips"></span> -->
       </li>
         <li>
        <span class="item_name" style="width:120px;">联系电话：</span>
        <input type="text" class="textbox textbox_295" name="mobile" placeholder="联系电话..."/>
        <!-- <span class="errorTips"></span> -->
       </li>
       <li>
         <span class="item_name" style="width:120px;">性别：</span>
        <label class="single_selection"><input type="radio" name="sex"/>男</label>
        <label class="single_selection"><input type="radio" name="sex"/>女</label>
       </li>
         <li>
        <span class="item_name" style="width:120px;">邮箱：</span>
        <input type="text" class="textbox textbox_295" name="email" placeholder="邮箱..."/>
        <!-- <span class="errorTips"></span> -->
       </li>
         <li>
        <span class="item_name" style="width:120px;">生日：</span>
        <input type="text" class="textbox textbox_295" name="birthday" placeholder="生日..."/>
        <!-- <span class="errorTips"></span> -->
       </li>
         <li>
        <span class="item_name" style="width:120px;">地址：</span>
        <input type="text" class="textbox textbox_295" name="address" placeholder="地址..."/>
        <!-- <span class="errorTips"></span> -->
       </li>
       <li>
        <span class="item_name" style="width:120px;">上传头像：</span>
        <label class="uploadImg">
         <input type="file"/>
         <span>上传图片</span>
        </label>
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
	  var name = $("#name").val();
	  var remark=$("#remark").val();
	  var qx=$('#tt').tree('getChecked');
	  var myArray="";
	  for(var i=0;i<qx.length;i++){
		  myArray+=qx[i].id+",";
	  }
	$.ajax({
		    type: "POST",
		    url: '${path}/sys/role/add',
		    data:{name:name,remark:remark,permission:myArray},
		    success: function(data){
		    	if(data.code==0) {		    		
		    		swal({
						title : "新增成功",
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