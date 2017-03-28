<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新闻详情</title>
<link rel="stylesheet" type="text/css" href="theme/1/css/main.css" />
<link rel="stylesheet" type="text/css" href="theme/1/css/common.css">
<link rel="stylesheet" type="text/css" href="theme/1/css/front/style.css">
</head>
<script type="text/javascript" src="theme/1/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="theme/1/js/main.js"></script>
<!-- <script type="text/javascript" src="theme/1/js/sinaFaceAndEffec.js"></script> -->
<script type="text/javascript">

	
	function out() {
		var content = $('.text').val();
		var username = $("#username").html();
		var publishTime = new Date();
		if(username==""){
			alert("请登录后再评论！！！");
		}else{
			$('#info-show ul').prepend(reply(username,content,publishTime));
			saveComment(username);
		}
	
	}
	
	var html;
	function reply(username,content,publishTime){
		html  = '<li>';
		html += '<div class="head-face">';
		html += '<img src="theme/1/images/1.jpg" / >';
		html += '</div>';
		html += '<div class="reply-cont">';
		html += '<p class="username">'+username +'</p>';
		html += '<p class="comment-body">'+content+'</p>';
		html += '<p class="comment-footer">'+publishTime+'</p>';
		html += '</div>';
		html += '</li>';
		return html;
	}
	
	function saveComment(username){
		var content = $('.text').val();
		var newsId = $("#newsId").val();
		$.ajax({
			type : "POST",
			url : "saveComment.action",
			data : {newsId:newsId,content:content,username:username},
			success : function(){
				$('.text').val() =="请输入内容......";
				location.reload(this);
			}
		});
	}
	
	$(function showComment(){
		var newsId = $("#newsId").val();
		$.ajax({
			type : "POST",
			url : "showComment.action?newsId="+newsId,
			Accept:"application/json",
			contentType : "application/json",
			dataType : "json",
			success : function(data) {
				$.each(data, function(i, model) {
					var username = model.username;
					var content = model.comment.content;
					var publishTime = model.comment.publishTime;
					$('#info-show ul').prepend(reply(username,content,publishTime));
				});
			}
		});
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
				<div class="conarticle">
					<div class="position">
						<span class="poshome">您现在的位置：</span> <a href="toIndex.action">首页</a>
						>> <a href="toList.action?c_id=${news.catalog_id }">${catalog.name }</a>
					</div>
					<input type="hidden" id="newsId" value="${news.id }">
					<div class="title">${news.title }</div>
					<div class="abstract">
						<span>更新时间：${news.publishTime }</span> <span>发布人：${news.author }</span>
						<span>评论${news.clickTimes }次</span>
					</div>
					<div class="details">
						<p>${news.content }</p>
					</div>
				</div>
				<div id="comment" style="width: 1050px; height: auto;margin-left:10px;margin-top:40px">
					<div class="cont-box">
						<textarea class="text" placeholder="请输入内容..."></textarea>
					</div>
					<div class="tools-box">
						<!-- <div class="operator-box-btn"><span class="face-icon"  >☺</span><span class="img-icon">▧</span></div> -->
						<div class="submit-btn"><input type="button" onClick="out()"value="提交评论" /></div>
					</div>
				</div>
				<div id="info-show">
					<ul></ul>
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
					信息发布系统 技术支持：<a href="javascript:void(0)">软件科技有限公司</a>
					电话：021-xxxxxxx
				</p>
			</div>
		</div>
	</div>
</body>
</html>
