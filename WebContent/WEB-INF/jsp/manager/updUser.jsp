<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
//修改用户信息
$(function() {
    initProvinces();
    var form=$("#updUserForm");
	form.off();
	form.on("submit",function(){
		form.ajaxSubmit(function(){
			form[0].reset;
			alert("修改成功！");
			$(".baseUI li :contains('用户管理')").trigger("click");
		});
		return false;
	});
});

/**
 * 获取省列表
 */
function initProvinces() {
	//$('#province').empty();
	$.ajax({
		type : "POST",
		url : "../showProvince.action",
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
		url : "../showCity.action?id=" + provinceID,
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
		url : "../showCounty.action?id=" + cityID,
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

	<form action="updUser.action" method="post" id="updUserForm">
	
		<table align="center">
			<caption><h1>用户信息</h1></caption>
			<tr>
				<td>用户名ID：</td>
				<td><input type="text" name="id" id="id" value="${user.id }" readonly="readonly"></td>
			</tr>
			<tr>
				<td>用户名称：</td>
				<td><input type="text" name="name" id="name" value="${user.name }"></td>
			</tr>
			<tr>
				<td>年龄：</td>
				<td><input type="text" name="age" id="age" value="${user.age }"></td>
			</tr>
			<tr>
				<td>性别：</td>
				<td>
					<input type="radio" name="sex" id="sex" value="0" <c:if test="${user.sex=='0'}"> checked='checked'</c:if> />男
					<input type="radio" name="sex" id="sex" value="1" <c:if test="${user.sex=='1'}"> checked='checked'</c:if> >女
				</td>
			</tr>
			<tr>
				<td>地址：</td>
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
				<td>注册时间：</td>
				<td><input type="text" name="age" id="age" value="${user.inputTime }" readonly="readonly"></td>
			</tr>
			<tr>
				<td>最后登录时间：</td>
				<td><input type="text" name="lastLogin" id="lastLogin" value="${user.lastLoginTime }" readonly="readonly"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="修改"></td>
			</tr>
		</table>
		
	</form>
	
</body>
</html>