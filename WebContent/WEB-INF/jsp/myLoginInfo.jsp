<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
th {
	height:40px;
	background: #f5f5f5;
	font-size: 16px;
	font-family: 微软雅黑;
}
tr{
	height:35px;
}
td {
	font-size: 15px;
	font-family: 微软雅黑;
	text-align: center;
}
</style>

<script type="text/javascript">
//设置表格颜色
$(function initTableColor() {
	$("tr:even").css("background", "#f1f1f1");
	$("tr:odd").css("background", "white");
});
	
//翻页
$(function(){
	$(".page").off();
	$(".page").on("click",function(){
		var username = $("#username").attr("val");
		var currPage = $(this).attr("val");
		$.post("showUserInfo.action",{currPage:currPage,catalogName:"我的登录信息",userName:username},function(){
			$(".userinfo").load("showUserInfo.action",{currPage:currPage,catalogName:"我的登录信息",userName:username});
		})
	})
});

</script>

<div class="loginInfo">
	<table border="1" style="border-collapse: collapse; width: 100%;">
		<tr>
			<th>用户名ID</th>
			<th>用户名称</th>
			<th>登录时间</th>
			<th>退出登录时间</th>
		</tr>
		<c:forEach items="${loginInfoList }" var="loginInfo">
			<tr>
				<td>${loginInfo.userId }</td>
				<td>${loginInfo.userName }</td>
				<td>${loginInfo.loginTime }</td>
				<td>${loginInfo.logoutTime }</td>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${currPage>1}">
			<a href="javascript:void(0)" class="page" val="1" style="color:blue;">首页</a>
			<a href="javascript:void(0)" class="page" val="${currPage-1 }" style="color:blue;">上一页</a>
		</c:if>
		<c:if test="${currPage==1}">
			<span class="disabled">首页</span>
			<span class="disabled">上一页</span>
		</c:if>
		 ${currPage}/${pageCount}
		<c:if test="${currPage<pageCount}">
			<a href="javascript:void(0)" class="page" val="${currPage+1 }" style="color:blue;">下一页</a>
			<a href="javascript:void(0)" class="page" val="${pageCount }" style="color:blue;">尾页</a>
		</c:if>
		<c:if test="${currPage==pageCount}">
			<span class="disabled">下一页</span>
			<span class="disabled">尾页</span>
		</c:if>
</div>