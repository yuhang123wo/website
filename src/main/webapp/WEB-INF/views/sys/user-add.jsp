<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
  <link href="${path }/css/style.css" rel="stylesheet" type="text/css" />
 <link rel="stylesheet" type="text/css" href="${path }/css/default.css">
 <link rel="stylesheet" type="text/css" href="${path }/css/sweet-alert.css" />
<style>
.error{
	color:red;
}
</style>
 <div class="rt_content">
      <div class="page_title">
       <h2 class="fl">添加用户</h2>
       <a class="fr top_rt_btn" href="${path}/sys/user/list">返回列表</a>
      </div>
     <section>
     <form action="aa" method="POST" id="signupForm">
      <ul class="ulColumn2">
       <li>
        <span class="item_name" style="width:120px;">用户名：</span>
        <input type="text" class="textbox textbox_295" placeholder="用户名..." name="username" id="username"/>
       </li>
       <li>
        <span class="item_name" style="width:120px;">密码：</span>
        <input type="password" class="textbox textbox_295" name="password" placeholder="密码..." id="password"/>
        <!-- <span class="errorTips"></span> -->
       </li>
        <li>
        <span class="item_name" style="width:120px;">确认密码：</span>
        <input type="password" class="textbox textbox_295" name="passwordRe" placeholder="确认密码..." id="passwordRe"/>
        <!-- <span class="errorTips"></span> -->
       </li>
       <li>
        <span class="item_name" style="width:120px;">部门：</span>
        <select class="select" id="dep" name="dep">
         <option value="">请选择所属部门</option>
         <option value="1">部门2</option>
         <option value="2">部门3</option>
        </select>
       </li>
       <li>
        <span class="item_name" style="width:120px;">真实姓名：</span>
        <input type="realname" class="textbox textbox_295" name="realname" placeholder="真实姓名..." id="realname"/>
        <!-- <span class="errorTips"></span> -->
       </li>
         <li>
        <span class="item_name" style="width:120px;">联系电话：</span>
        <input type="text" class="textbox textbox_295" name="mobile" placeholder="联系电话..." id="mobile"/>
        <!-- <span class="errorTips"></span> -->
       </li>
       <li>
        <span class="item_name" style="width:120px;">性别：</span>
        <label class="single_selection"><input type="radio" name="sex" checked="checked" value="1"/>男</label>
        <label class="single_selection"><input type="radio" name="sex" value="0"/>女</label>
       </li>
         <li>
        <span class="item_name" style="width:120px;">邮箱：</span>
        <input type="text" class="textbox textbox_295" name="email" placeholder="邮箱..." id="email"/>
        <!-- <span class="errorTips"></span> -->
       </li>
         <li>
        <span class="item_name" style="width:120px;">生日：</span>
        <input type="text" class="textbox textbox_295" name="birthday" placeholder="生日..." id="birthday"/>
        <!-- <span class="errorTips"></span> -->
       </li>
        <li>
        <span class="item_name" style="width:120px;">地址：</span>
        <input type="text" class="textbox textbox_295" name="address" placeholder="地址..." id="address"/>
        <!-- <span class="errorTips"></span> -->
       </li>
        <span class="item_name" style="width:120px;"></span>
        <input type="submit" class="link_btn" value="提交" onclick="addData();"/>
       </li>
      </ul>
     </section>
     </form>
 </div>
<script type="text/javascript">
  function addData(){
	  var parray = $("#signupForm").serialize();
	$.ajax({
		    type: "POST",
		    url: '${path}/sys/user/add',
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
  jQuery.validator.addMethod("isPhone", function(value,element) { 
	  var length = value.length; 
	  var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/; 
	  var tel = /^\d{3,4}-?\d{7,9}$/; 
	  return this.optional(element) || (tel.test(value) || mobile.test(value)); 

	}, "请正确填写您的联系电话");
 $(function(){
	  $("#signupForm").validate({
		    rules: {
		    	username:{
		    	required:true,
		        minlength:3,
		        maxlength:20
		        },
		        password:{
		        	required:true,	
		        	minlength:6
		        },
		        passwordRe:{
		        	required:true,	
		        	equalTo:"#password"
		        },
		        dep:{
		        	required:true
		        },
		        email:{
		        	required:true,
		        	email:true
		        	
		        },
		        mobile:{
		        	required:true,
		        	isPhone:true
		        	
		        }
		     },
		    messages: {
		    	username:{
		    		required:"请输入用户名",
			    	minlength:"用户名不能小于3个字符",
			    	maxlength:"用户名不能大于20个字符",
		    	},
		        password:{
		        	required:"不能为空",	
		        	minlength:"密码长度至少6位"
		        },
		        passwordRe:{
			        	required:"不能为空",	
			        	equalTo:"两次密码输入不一至"
			        },
			    dep:{
			    	required:'请选择部门'
			    },
			    email:{
			    	required:'邮箱必填',
			    	email:'填写正确的邮箱格式'
			    },
			    mobile:{
			    	required:"不能为空",
			    	isPhone:'填写正确电话'
			    }
		    },
		    submitHandler : function(form) {
				return false;
			},
			errorPlacement : function(error, element) {
				$(element).closest("form").find( "input[name='" + element.attr( "name" ) + "']" ).parent().append(error);
			},
			errorElement : "span"
		});
 })
 
 $(document).on('ready', function() {
	   $("#file-3").fileinput({
	        showUpload: false,
	        showCaption: false,
	        maxFileCount:1,
	        browseClass: "btn btn-primary btn-lg",
	        fileType: "any",
	        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
	        overwriteInitial: false
	    });

});
</script>