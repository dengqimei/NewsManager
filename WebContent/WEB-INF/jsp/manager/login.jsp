<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
</head>
<body>
	<form action="Manager/login.action" method="post">
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
				<td colspan="2" align="center"><input type="submit" value="登录"></td>
			</tr>
		</table>
	</form>
</body>
</html>