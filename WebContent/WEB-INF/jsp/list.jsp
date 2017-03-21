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
					<c:forEach items="${newsList }" var="news">
						<div class="art_box">
							<div class="art_img">
								<a href="toContent.action?id=${news.id }"><img src="Manager/showImage.action?id=${news.id }"></a>
							</div>
							<div class="art_txt">
								<div class="title">
									<a href="toContent.action?id=${news.id }">${news.title }</a><span class="icon_pen"></span>
								</div>
								<div class="tag_txt">
									<span>栏目名称：${catalog.name }</span>
								</div>
								<div class="tag_txt">
									<span>撰写人：${news.author }</span><span class="pl30">上传时间：${news.publishTime }</span>
								</div>
							</div>
						</div>
					</c:forEach>
						<div class="page">分页</div>
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