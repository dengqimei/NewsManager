<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <style>
        th{
            background:#f5f5f5;
            font-size:16px;
            font-family:微软雅黑;
        }
        td{
            font-size:15px;
            font-family:微软雅黑;
            text-align:center;
        }
    </style>
<div>
	<table border="1" style=" border-collapse:collapse; width:100%;">
	<caption style="font-size:24px;font-weight: bold;">所有新闻信息</caption>
		<tr>
			<th>编号</th>
			<th>标题</th>
			<th>作者</th>
			<th>发布时间</th>
			<th>浏览次数</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${newsList }" var="news" varStatus="status">
		<tr <c:if test="${status.index %2 !=0 }">style="background:#f1f1f1"</c:if>
		<c:if test="${status.index %2 ==0 }">style="background:white"</c:if>>
			<td><input type="checkbox" value=${news.id }></td>
			<td width="40%">${news.title }</td>
			<td width="150px">${news.author }</td>
			<td>${news.publishTime }</td>
			<td>${news.clickTimes }</td>
			<td>
			    <a href="javascript:void(0)" val="${news.id }" class="upd">修改</a>
			    <a href="javascript:void(0)" val="${news.id }" class="del">删除</a>
			</td>
		</tr>
		</c:forEach>
	</table>
</div>
<script>
$(function(){
	$(".del").off();
	$(".del").on("click",function(){
		var flag = confirm("确定删除吗？");
		if(flag){
		    var id = $(this).attr("val");
		    $.post("delNews.action",{id:id},function(){
			    $(".baseUI li :contains('新闻管理')").trigger("click");
			    alert("删除成功");
		    });
		}
	});	
});

$(function(){
	$(".upd").off();
	$(".upd").on("click",function(){
		var id=$(this).attr("val");
		$.post("toUpdNews.action",{id:id},function(){
			$(".right").load("toUpdNews.action",{id:id});
		});
	});
});
</script>