<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新闻详情</title>
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
           <div class="conarticle">
                    <div class="position">
                    	<span class="poshome">您现在的位置：</span>
                        <a href="toIndex.action">首页</a> >> <a href="toList.action?c_id=${news.catalog_id }">${catalog.name }</a>
                    </div>
                    <div class="title">${news.title }</div>
                    <div class="abstract">
                    	<span>更新时间：${news.publishTime }</span>
                        <span>发布人：${news.author }</span>
                        <span>点击${news.clickTimes }次</span>
                    </div>
                    <div class="details">
                    	<p>${news.content }</p>
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
        	<p>信息发布系统 技术支持：<a href="javascript:void(0)">软件科技有限公司</a>      电话：021-xxxxxxx</p>
        </div>
    </div>
</div>
</body>
</html>
