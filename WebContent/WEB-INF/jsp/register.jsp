<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
<style type="text/css">
*{
	padding:0;
	margin:0;
}
	html{
		height:100%;
	}
	body{
		/* height:100%; */
		background:url('theme/1/images/register.jpg') no-repeat;
		background-size:100% 100%;
		position:reletive;
	}
	.logo{
		width: 800px;
	    height: 132px;
	    background: url(theme/1/images/logo.png) no-repeat;
	    position: absolute;
	    top: 0;
	    left: 0;
	    margin-top: -30px;
	    margin-left:30px;
	}
	.form-con{
		width: 700px;
	    height: 420px;
	    display: block;
	    background: rgba(255, 242, 242, 0.4);
	    border-radius:10px;
	    margin:  125px auto 0 auto;
	}
	.registerForm{
		padding-left:90px;
	}
	td{
		padding:5px 10px;
		text-align:right;
	}
	 .text-left{
	 	text-align:left;
	 }
	 input{
	 	
		margin-top:5px;
		padding:5px 10px;
	}
	input[type="text"],input[type="password"]{
		width:180px;
	}
	.table-title{
		color:white;
		font-family: cursive;
	    margin-left: -74px;
	    font-size: 40px;
	}
</style>
</head>
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript">

	/* function selectCity(){
		var provinceId = $("#province").val();
		var xmlhttp=new XMLHttpRequest();
		xmlhttp.open("POST", "showCity.action?id="+provinceId, true);
		xmlhttp.send();
	} */
	
	$(function() {
        initProvinces();
    });
    
    /**
     * 获取省列表
     */
	function initProvinces() {
		//$('#province').empty();
		$.ajax({
			type : "POST",
			url : "showProvince.action",
			Accept:"application/json",
			contentType : "application/json",
			dataType : "json",
			success : function(data) {
				$.each(data, function(i, province) {
					$("<option value='" + province.id + "'>"
						+ province.name + "</option><br>").click(function() {
								initCities(province.id);
					}).appendTo($('#province')); 
				});
			}
		});
	}

	/**
	 * 获取市列表
	 */
	function initCities(provinceID) {
		$('#city').empty();
		$.ajax({
			type : "POST",
			url : "showCity.action?id=" + provinceID,
			Accept:"application/json",
			contentType : "application/json",
			dataType : "json",
			success : function(data) {
				$.each(data,function(i, city) {
					$("<option value='" + city.id + "' >"+ city.name +
							"</option><br>").click(function() {
								initCounties(city.id);
						}).appendTo($('#city'));
				});
			}
		});
	}
	
	/**
	 * 获取区县列表
	 */
	function initCounties(cityID) {
		$('#county').empty();
		$.ajax({
			type : "POST",
			url : "showCounty.action?id=" + cityID,
			Accept:"application/json",
			contentType : "application/json",
			dataType : "json",
			success : function(data) {
				$.each(data, function(i, county) {
					$("<option value='" + county.id + "' >"
						+ county.name + "</option><br>").appendTo(
							$('#county'));
				});
			}
		});
	}
	
	function check(){
		$("#msg").empty();
		var id = $("#id").val();
		var name = $("#name").val();
		var password = $("#password").val();
		var age = $("#age").val();
		var sex = $("#sex:checked").attr("checked");
		var address = $("#county").val();
		if(id==""){
			alert("请输入用户ID！！！");
			return false;
		}else if(name==""){
			alert("请输入用户名称！！！")
			return false;
		}else if(password==""){
			alert("请输入密码！！！");
			return false;
		}else if (checkStrLength(password) < 6) {
			alert("对不起，密码长度不能少于6位！");
			$("#password").val("");
			return false;
		}else if (checkStrLength(password) > 14) {
			alert("对不起，密码长度不能超过14位！");
			$("#password").val("");
			return false;
		}else if(age==""){
			alert("请输入年龄！！！");
			return false;
		}else if(!isNumber(age)){
			alert("对不起，年龄应该为数字！");
			$("#age").val("");
			return false;
		}else if (checkStrLength(age)>=3){
			alert("请输入正确的年龄！");
			$("#age").val("");
			return false;
		}else if(sex!="checked"){
			alert("请选择性别！！！");
			return false;
		}else if(address=="000000"){
			alert("请选择地址！！！");
			return false;
		}else{
			return checkUser();
		}
	}
	function checkUser(){
		var id = $("#id").val();
		var name = $("#name").val();
		$.ajax({
			url:"checkRegister.action",
			data:{userid:id,username:name},
			success:function(data){
				if(data=="userid exist"){
					alert("用户ID已经存在，请重新输入！！！");
					$("#id").val("");
					$("#msg").attr("value","false");
				}else if(data=="username exist"){
					alert("用户名称已经存在，请重新输入！！！");
					$("#name").val("");
					$("#msg").attr("value","false");
				}else{
					$("#msg").attr("value","true");
				}
			}
		});
		var flag = $("#msg").val();
		if(flag=="true"){
			return true;
		}else{
			return false;
		}
	}
	
	//计算字符串的长度
	function checkStrLength(value) {
		var StrTrueLength = value.replace(/[^\x00-\xff]/g, "~~").length;
		return StrTrueLength;
	}
	
	/*判断是否为数字*/
	function isNumber(str) {
		var Letters = "1234567890";
		for (var i = 0; i < str.length; i = i + 1) {
			var CheckChar = str.charAt(i);
			if (Letters.indexOf(CheckChar) == -1) {
				return false;
			}
		}
		return true;
	}
	
</script>

<body>
	<div class="logo"></div>
	<div class="form-con">
	<form class="registerForm" action="register.action" method="post" onsubmit="return check()">
	
		<table align="center">
			<caption><h1 class="table-title">用户注册</h1></caption>
			<tr>
				<td>用户名ID：</td>
				<td class="text-left"><input type="text" name="id" id="id"><input type="hidden" id="msg"></td>
			</tr>
			<tr>
				<td>用户名称：</td>
				<td class="text-left"><input type="text" name="name" id="name"></td>
			</tr>
			<tr>
				<td>密码：</td>
				<td class="text-left"><input type="password" name="password" id="password"></td>
			</tr>
			<tr>
				<td>年龄：</td>
				<td class="text-left"><input type="text" name="age" id="age"></td>
			</tr>
			<tr>
				<td>性别：</td>
				<td class="text-left">
					<input type="radio" name="sex" id="sex" value="0">男
					<input type="radio" name="sex" id="sex" value="1">女
				</td>
			</tr>
			<tr>
				<td>地址：</td>
				<td class="text-left">
					省份<select id="province" name="province">
						<option value="000000">---请选择---</option>
						<%-- <c:forEach items="${provinceList }" var="province">
							<option value="${province.id }">${province.name }</option>
						</c:forEach> --%>
					</select>
					市<select id="city" name="city">
						<option value="000000">---请选择---</option>
					</select>
					县/市<select id="county" name="address">
						<option value="000000">---请选择---</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align:center;"><input style="width:95px;height:27px;background: url('theme/1/images/register_btn.jpg')" value="" type="submit">
				已有账号，请<a href="toLogin.action" class="register-btn"  style="color:blue;">登录</a></td>
			</tr>
		</table>
		
	</form>
	</div>
</body>
</html>