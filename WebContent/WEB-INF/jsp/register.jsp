<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
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
	
</script>

<body>
	
	<form action="register.action" method="post">
	
		<table align="center">
			<caption><h1>用户注册</h1></caption>
			<tr>
				<td>用户名ID：</td>
				<td><input type="text" name="id" id="id"></td>
			</tr>
			<tr>
				<td>用户名称：</td>
				<td><input type="text" name="name" id="name"></td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><input type="password" name="password" id="password"></td>
			</tr>
			<tr>
				<td>年龄：</td>
				<td><input type="text" name="age" id="age"></td>
			</tr>
			<tr>
				<td>性别：</td>
				<td>
					<input type="radio" name="sex" id="sex" value="0">男
					<input type="radio" name="sex" id="sex" value="1">女
				</td>
			</tr>
			<tr>
				<td>地址：</td>
				<td>
					省份<select id="province" name="province">
						<option value="000000">---请选择---</option>
						<c:forEach items="${provinceList }" var="province">
							<option value="${province.id }">${province.name }</option>
						</c:forEach>
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
				<td colspan="2"><input type="submit" value="注册"></td>
			</tr>
		</table>
		
	</form>
	
</body>
</html>