<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="logo_sign">
	<div class="logo"></div>
	<div class="sign">
		<span>欢迎您&nbsp;<a id="username" href="toUserInfo.action?userName=${username }">${username }</a>
		<a href="toLogin.action">${login }</a></span> <!-- <span class="sp_home" style="visibility:hidden">首页</span> --> <span
			class="sp_backstage" <c:if test="${usertype=='1' }"> style="visibility:hidden"</c:if>><a href="Manager/toIndex.action">后台管理</a></span> 
			<span class="sp_signout"<c:if test="${username==''||username==null }"> style="visibility:hidden"</c:if>><a href="logout.action">退出</a></span>
	</div>
</div>
<div class="nav">
	<ul class="navUI">
	<li><a href="toIndex.action">首页</a></li>
	<c:forEach items="${catalogList }" var="catalog" begin="0" end="8">
		<li><a href="toList.action?c_id=${catalog.id }">${catalog.name }</a></li>
	</c:forEach>
	</ul>
</div>