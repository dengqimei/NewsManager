<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<style type="text/css">
	.td-title{
		text-align:right;
	}
</style>

<script type="text/javascript">

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

<div class="myInfo">
	<form action="updUser.action" method="post" id="updUserForm">
	
		<table align="center" style="height:300px;margin-left:30px;font-size:16px;">
			<!-- <caption align="center"><h2>用户个人信息</h2></caption> -->
			<tr>
				<td class="td-title">用户名ID：</td>
				<td><input type="text" name="id" id="id" value="${user.id }" readonly="readonly"></td>
			</tr>
			<tr>
				<td class="td-title">用户名称：</td>
				<td><input type="text" name="name" id="name" value="${user.name }"></td>
			</tr>
			<tr>
				<td class="td-title">年龄：</td>
				<td><input type="text" name="age" id="age" value="${user.age }"></td>
			</tr>
			<tr>
				<td class="td-title">性别：</td>
				<td>
					<input type="radio" name="sex" id="sex" value="0" <c:if test="${user.sex=='0'}"> checked="checked"</c:if> />男
					<input type="radio" name="sex" id="sex" value="1" <c:if test="${user.sex=='1'}"> checked="checked"</c:if> />女
				</td>
			</tr>
			<tr>
				<td class="td-title">地址：</td>
				<td>
					省份<select id="province" name="province">
						<option value="${province.id }">${province.name }</option>
					</select>
					市<select id="city" name="city">
						<option value="${city.id }">${city.name }</option>
					</select>
					县/市<select id="county" name="address">
						<option  value="${user.address }">${address.name }</option>
					</select>
				</td>
			</tr>
			<tr>
				<td class="td-title">注册时间：</td>
				<td><input type="text" name="age" id="age" value="${user.inputTime }" readonly="readonly"></td>
			</tr>
			<tr>
				<td class="td-title">最后登录时间：</td>
				<td><input type="text" name="lastLogin" id="lastLogin" value="${user.lastLoginTime }" readonly="readonly"></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align:center;"><input type="submit" value="修改"></td>
			</tr>
		</table>
		
	</form>
</div>