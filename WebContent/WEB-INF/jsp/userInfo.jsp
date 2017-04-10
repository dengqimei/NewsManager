<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息</title>
</head>
<link rel="stylesheet" type="text/css" href="theme/1/css/main.css" />
<link rel="stylesheet" type="text/css" href="theme/1/css/common.css">
<link rel="stylesheet" type="text/css" href="theme/1/css/front/style.css">
</head>
<script type="text/javascript" src="theme/1/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="theme/1/js/main.js"></script>
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

$(function(){
	$(".userList").off();
	$(".userList").on("click",function(){
		var username = $("#username").attr("val");
		var catalogname = $(this).attr("val");
		$.post("showUserInfo.action",{userName:username,catalogName:catalogname},function(){
			$(".userinfo").load("showUserInfo.action",{userName:username,catalogName:catalogname});
		})
	})
});

</script>
<style type="text/css">

.userinfo{
	height:400px;
	margin-left:30px;
	margin-top:20px;
}

</style>
<body>
	<div id="main">
		<div class="top">
			<div class="logo_sign">
				<div class="logo"></div>
				<div class="sign">
					<span>欢迎您&nbsp;<a id="username" val="${username }" href="toUserInfo.action?userName=${username }">${username }</a>
					<a href="toLogin.action">${login }</a></span> <!-- <span class="sp_home" style="visibility:hidden">首页</span> --> <span
						class="sp_backstage" <c:if test="${usertype=='1' }"> style="visibility:hidden"</c:if>><a href="Manager/toIndex.action">后台管理</a></span> 
						<span class="sp_signout"<c:if test="${username==''||username==null }"> style="visibility:hidden"</c:if>><a href="logout.action">退出</a></span>
				</div>
			</div>
			<div class="nav">
				<ul class="navUI">
				<li><a href="toIndex.action">首页</a></li>
				<c:forEach items="${catalogList }" var="catalog" begin="0" end="8">
					<li><a class="userList" href="javascript:void(0)" val="${catalog.name }">${catalog.name }</a></li>
				</c:forEach>
				</ul>
			</div>
		</div>
		
		<div class="content">
			<div class="congw">
				<div class="userinfo">
					<jsp:include page="myInfo.jsp"></jsp:include>
				</div>
				
				<div class="link">
					<div class="linelink">
						<span>友情链接：</span> <a href="http://news.sina.com.cn/">新浪新闻</a> <a
							href="http://mini.eastday.com/">头条新闻</a> <a
							href="http://news.ifeng.com/">凤凰新闻</a> <a
							href="http://www.huanqiu.com/">环球新闻</a> <a
							href="http://www.xinhuanet.com/">新华网</a>
					</div>
				</div>
			</div>
		</div>
		<div class="footer">
			<div class="foot">
				<p>
					新闻信息发布系统  技术支持：<a href="javascript:void(0)">软件科技有限公司</a>
				</p>
			</div>
		</div>
	</div>
</body>
</html>

