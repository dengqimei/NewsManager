<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="theme/1/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="theme/1/js/main.js"></script>
<script type="text/javascript">
//通过栏目查询新闻
$(function(){
	var c_id = $(".catalogid").attr("val");
	$(".getpage").off();
	$(".getpage").on("click",function(){
		var currPage = $(this).attr("val");
		$.post("listNews.action",{c_id:c_id,currPage:currPage},function(){
			$(".search_result").load("listNews.action",{c_id:c_id,currPage:currPage});
		})
	})
});

</script>

<div class="position">
	<span class="poshome">您现在的位置：</span> <a href="toIndex.action">首页</a>
	>> <a class="catalogid" href="toList.action?c_id=${catalog.id }" val="${catalog.id }">${catalog.name }</a>
</div>

<div class="newsList">
	<c:forEach items="${newsList }" var="news">
		<div class="art_box">
			<div class="art_img">
				<a href="toContent.action?id=${news.id }"><img src="theme/1/images/front/doc.png"></a>
			</div>
			<div class="art_txt">
				<div class="title">
					<a href="toContent.action?id=${news.id }">${news.title }</a><span class="icon_pen"></span>
				</div>
				<div class="tag_txt">
					<span>栏目名称：${catalog.name }</span>
				</div>
				<div class="tag_txt">
					<span>作者：${news.author }</span><span class="pl30">发布时间：${news.publishTime }</span>
				</div>
			</div>
		</div>
	</c:forEach>
</div>
<div class="page">
	<c:if test="${currPage>1}">
		<a class="getpage" href="javascript:void(0)" val="1" style="color:blue;">首页</a>
		<a class="getpage" href="javascript:void(0)" val="${currPage-1}" style="color:blue;">上一页</a>
	</c:if>
	<c:if test="${currPage==1}">
		<span class="disabled">首页</span>
		<span class="disabled">上一页</span>
	</c:if>
	 ${currPage}/${pageCount}
	<c:if test="${currPage<pageCount}">
		<a class="getpage" href="javascript:void(0)" val="${currPage+1}" style="color:blue;">下一页</a>
		<a class="getpage" href="javascript:void(0)" val="${pageCount}" style="color:blue;">尾页</a>
	</c:if>
	<c:if test="${currPage==pageCount}">
		<span class="disabled">下一页</span>
		<span class="disabled">尾页</span>
	</c:if>
</div>