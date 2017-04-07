<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
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

<style type="text/css">
body{
	text-align: center;
}
#login{
	margin:0 auto;
}
</style>
<body>
	<div id="login">
		<form action="#" method="post" id="loginForm">
			<table align="center">
				<caption><h1>用户登录</h1></caption>
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
					<td align="center"><input onclick="login()" type="button" value="登录"></td>
					<td><a href="toRegister.action" style="color:blue;">没有账号？注册</a></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>