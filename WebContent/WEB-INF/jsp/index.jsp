<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<link rel="stylesheet" type="text/css" href="theme/1/css/common.css">
<link rel="stylesheet" type="text/css" href="theme/1/css/front/style.css">
</head>
<body>
	<div id="main">
		<div class="top">
			<div class="topbg">
				<jsp:include page="header.jsp"></jsp:include>
				<!-- 
				<div class="banner">
					<div class="banimg">
						<img src="theme/1/images/front/banner.png">
					</div>
				</div>
				 -->
			</div>
		</div>
		<div class="content">
			<div class="congw">

				<div class="clear"></div>
				<div class="box">

					<c:forEach items="${catalogNewsList }" var="cnl" begin="0" end="2">
					<div class="box_b">
						<div class="title">
							<span class="txt_title">${cnl.catalog.name }</span> <span class="txt_discipline">作者</span>
							<span class="txt_time"><a href="toList.action?c_id=${cnl.catalog.id }">更多>></a></span>
						</div>
						<div class="cont">
							<ul class="discipUI">
							<c:forEach items="${cnl.news }" var="news" begin="0" end="5">
								<li><a href="toContent.action?id=${news.id }" class="txt_title">${news.title }</a><span
									class="txt_discipline">${news.author }</span><span class="txt_time">${news.publishDate }</span>
								</li>
							</c:forEach>
							</ul>
						</div>
					</div>
					</c:forEach>

				</div>
				
				<div class="box">

					<c:forEach items="${catalogNewsList }" var="cnl" begin="3" end="5">
					<div class="box_f">
						<div class="title">
							<span class="txt_title">${cnl.catalog.name }</span> <span class="txt_discipline">作者</span>
							<span class="txt_time"><a href="toList.action?c_id=${cnl.catalog.id }">更多>></a></span>
						</div>
						<div class="cont">
							<ul class="discipUI">
								<c:forEach items="${cnl.news }" var="news" begin="0" end="5">
								<li><a href="toContent.action?id=${news.id }" class="txt_title">${news.title }</a><span
									class="txt_discipline">${news.author }</span><span class="txt_time">${news.publishDate }</span>
								</li>
							</c:forEach>
							</ul>
						</div>
					</div>
					</c:forEach>

				</div>
				
				<div class="box">

					<c:forEach items="${catalogNewsList }" var="cnl" begin="6" end="8">
					<div class="box_f">
						<div class="title">
							<span class="txt_title">${cnl.catalog.name }</span> <span class="txt_discipline">作者</span>
							<span class="txt_time"><a href="toList.action?c_id=${cnl.catalog.id }">更多>></a></span>
						</div>
						<div class="cont">
							<ul class="discipUI">
								<c:forEach items="${cnl.news }" var="news" begin="0" end="5">
								<li><a href="toContent.action?id=${news.id }" class="txt_title">${news.title }</a><span
									class="txt_discipline">${news.author }</span><span class="txt_time">${news.publishDate }</span>
								</li>
							</c:forEach>
							</ul>
						</div>
					</div>
					</c:forEach>

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