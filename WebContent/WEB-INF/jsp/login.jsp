<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
<style type="text/css">
*{
	padding:0;
	margin:0;
}
	html{
		height:100%;
	}
	body{
		height:100%;
		background:url('theme/1/images/login.png') no-repeat;
		background-size:100% 100%;
	}
	#login{
		width: 700px;
	    margin: auto;
	    text-align: center;
	    padding-left: 386px;
	    padding-top: 280px;
	}
	input{
		margin-top:5px;
		padding:5px 10px;
	}
	td{
		text-align:right;
	}
	.register-btn{
		margin-right:-10px;
		margin-left:10px;
	}
</style>
</head>
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript">
	
	function check(){
		var userid = $("#id").val();
		var password = $("#password").val();
		if(userid==""||userid==null){
			alert("请输入用户名！！！");
			return false;
		}else if(password==""||password==null){
			alert("请输入密码！！！");
			return false;
		}else{
			return true;
		}
	}
	
	function login(){
		var userid = $("#id").val();
		var password = $("#password").val();
		if(check()){
			$(".loginForm").attr("onsubmit","return true");
			$.ajax({
				url:"login.action",
				data:{uid:userid,pwd:password},
				success:function(data){
					if("index"==data){
						$.post("toIndex.action",{userid:userid},function(){
							$("body").load("toIndex.action",{userid:userid});
						})
					}else{
						alert(data);
						$("#id").val("");
						$("#password").val("");
					}
				}
				
			});
		}
	}
	
</script>

<body>
	<div id="login">
		<form action="#" method="post" id="loginForm" onsubmit="return false">
			<table align="center">
				<tr>
					<td>用户名ID：</td>
					<td><input type="text" name="id" id="id"></td>
				</tr>
				<tr>
					<td>密码：</td>
					<td><input type="password" name="password" id="password"></td>
				</tr>
				<!-- <tr>
					<td>权限：</td>
					<td>
						<input type="radio" value="0" name="type" id="type">管理员
						<input type="radio" value="1" name="type" id="type">普通用户
					</td>
				</tr> -->
				<tr>
					<td align="center" colspan="2"><input onclick="login()" type="submit" value="登录"><a href="toRegister.action" class="register-btn"  style="color:blue;">没有账号？注册</a></td>
					
				</tr>
			</table>
		</form>
	</div>
</body>
</html>