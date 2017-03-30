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
	
	//检查用户名是否存在
	function checkUserId(){
		var userid = $("#id").val();
		if(userid==null||userid==""){
			alert("请输入用户名ID!");
		}else{
			$.ajax({
				url:"checkUserId.action",
				data:"userid="+userid,
				success:function(data){
					if(data=="failed"){
						
					}
				}
			});
		}
	}
	
	//检查密码是否正确！
	function checkLogin(){
		var userid = $("#id").val();
		var password = $("#password").val();
		if(password==null||password==""){
			alert("请输入密码！");
		}else{
			$.ajax({
				url:"checkLogin.action",
				data:{userid:userid,password:password},
				success:function(data){
					if(data=="failed"){
						alert("密码错误，请重新输入密码！");
						$("#password").val("");
					}
				}
			});
		}
	}
	
</script>
<body>
	<form action="login.action" method="post" id="loginForm">
		<table align="center">
			<caption><h1>用户登录</h1></caption>
			<tr>
				<td>用户名ID：</td>
				<td><input type="text" name="id" id="id" onblur="checkUserId()"></td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><input type="password" name="password" id="password" onblur="checkLogin()"></td>
			</tr>
			<!-- <tr>
				<td>权限：</td>
				<td>
					<input type="radio" value="0" name="type" id="type">管理员
					<input type="radio" value="1" name="type" id="type">普通用户
				</td>
			</tr> -->
			<tr>
				<td align="center"><input id="submit" type="submit" value="登录"></td>
				<td><a href="toRegister.action">没有账号？注册</a></td>
			</tr>
		</table>
	</form>
</body>
</html>