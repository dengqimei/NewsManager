<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新闻</title>
<link rel="stylesheet" type="text/css" href="theme/1/css/common.css">
<link rel="stylesheet" type="text/css" href="theme/1/css/front/style.css">
</head>
<script type="text/javascript" src="theme/1/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="theme/1/js/main.js"></script>
<script type="text/javascript">

$(function getPage(){
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

<body>
	<div id="main">
		<div class="top">
			<div class="topbg">
				<jsp:include page="header.jsp"></jsp:include>
			</div>
		</div>
		<div class="content">
			<div class="congw">
				<div class="conlist">
					<div class="search_result">
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
							
							<div class="page">
								<c:if test="${currPage>1}">
									<a class="getpage" val="1">首页</a>
									<a class="getpage" val="${currPage-1}" >上一页</a>
								</c:if>
								<c:if test="${currPage==1}">
									<span class="disabled">首页</span>
									<span class="disabled">上一页</span>
								</c:if>
								 ${currPage}/${pageCount}
								<c:if test="${currPage<pageCount}">
									<a class="getpage" val="${currPage+1}" >下一页</a>
									<a class="getpage" val="${pageCount}" >尾页</a>
								</c:if>
								<c:if test="${currPage==pageCount}">
									<span class="disabled">下一页</span>
									<span class="disabled">尾页</span>
								</c:if>
							</div>
						
						</div>
						
						
					</div>

				</div>
				<div class="link">
					<div class="linelink">
						<span>友情链接：</span>
						<a href="http://news.sina.com.cn/">新浪新闻</a>
						<a href="http://mini.eastday.com/">头条新闻</a>
						<a href="http://news.ifeng.com/">凤凰新闻</a>
						<a href="http://www.huanqiu.com/">环球新闻</a>
						<a href="http://www.xinhuanet.com/">新华网</a>
					</div>
				</div>
			</div>
		</div>
		<div class="footer">
			<div class="foot">
				<p>
					信息发布系统 技术支持：<a href="javascript:void(0)">软件科技有限公司</a>
					电话：021-xxxxxxx
				</p>
			</div>
		</div>
	</div>
</body>
</html>