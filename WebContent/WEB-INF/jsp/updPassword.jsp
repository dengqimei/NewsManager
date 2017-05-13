<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<style type="text/css">
	.td-title{
		text-align:right;
	}
</style>

<script type="text/javascript">

function check(){
	var oldPWd=$("#oldPWD").val();
	var newPWd=$("#newPWD").val();
	var newPWD1 = $("#newPWD1").val();
	if(oldPWd==""){
		alert("请输入原始密码！！！");
		return false;
	}else if(newPWd==""){
		alert("请输入新密码！！！");
		return false;
	}else if(newPWD1==""){
		alert("请确认密码！！！");
		return false;
	}else if(oldPWd==newPWd){
		alert("新密码和原始密码一致！！！");
		$("#newPWD").val("");
		$("#newPWD1").val("");
		return false;
	}else if(newPWd!=newPWD1){
		alert("两次输入的密码不一致！！！");
		$("#newPWD1").val("");
		return false;
	}else{
		return true;
	}
}

function updPWD(){
	var oldPWD = $("#oldPWD").val();
	var newPWD = $("#newPWD").val();
	var username = $("#username").attr("val");
	if(check()){
		$.ajax({
			url:"updPassword.action",
			data:{userName:username,oldPWD:oldPWD,newPWD:newPWD},
			success:function(data){
				alert(data);
				$("#oldPWD").val("");
				$("#newPWD").val("");
				$("#newPWD1").val("");
			}
		});
	}
}

</script>
<div class="updPassword">

	<div class="pwdTable">
		<form action="#" method="post">
			<table height="150px" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
					<td class="td-title">原始密码：</td>
					<td><input type="password" name="oldPWD" id="oldPWD"></td>
				</tr>
				<tr>
					<td class="td-title">新密码：</td>
					<td><input type="password" name="newPWD" id="newPWD"></td>
				</tr>
				<tr>
					<td class="td-title">确认密码：</td>
					<td><input type="password" name="newPWD1" id="newPWD1"></td>
				</tr>
				<tr>
					<td align="center" colspan="2"><input type="button" value="提交" onclick="updPWD()"></td>
				</tr>
			</table>
		</form>
	</div>
	
</div>