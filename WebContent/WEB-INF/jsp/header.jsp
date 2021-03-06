<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
$(function(){
	$(".login").off();
	$(".login").on("click",function(){
		$.post("toLogin.action",function(){
			$("head").html("");
			$("body").load("toLogin.action");
		})
	})
});
$(function(){
	$(".logout").off();
	$(".logout").on("click",function(){
		$.post("logout.action",function(){
			$("head").html("");
			$("body").load("logout.action");
		})
	})
});
</script>


<div class="logo_sign">
	<div class="logo"></div>
	<div class="sign">
		<span>欢迎您&nbsp;<a id="username" val="${username }" href="toUserInfo.action?userName=${username }">${username }</a>
		<a class="login" href="javascript:void(0)">${login }</a></span> <!-- <span class="sp_home" style="visibility:hidden">首页</span> --> <span
			class="sp_backstage" <c:if test="${usertype=='1'||usertype==null }"> style="visibility:hidden"</c:if>><a href="Manager/toIndex.action">后台管理</a></span> 
			<span class="sp_signout"<c:if test="${username==''||username==null }"> style="visibility:hidden"</c:if>><a class="logout" href="javascript:void(0)">退出</a></span>
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
