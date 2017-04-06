<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
$(function(){
	initTableColor();
});
	
	//设置表格颜色
	function initTableColor() {
	$("tr:even").css("background", "#f1f1f1");
	$("tr:odd").css("background", "white");
}
	
	//翻页
	$(function(){
		var c_id = $(".querybyCID:selected").val();
		$(".page").off();
		$(".page").on("click",function(){
			var currPage = $(this).attr("val");
			$.post("showNewsByCatalog.action",{catalog_id:c_id,currPage:currPage},function(){
				$(".newsTable").load("showNewsByCatalog.action",{catalog_id:c_id,currPage:currPage});
			})
		})
	});
	
	//修改新闻信息
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
<div class="news">
	<table border="1" style=" border-collapse:collapse; width:100%;" id="newsTable">
		<tr>
			<th><p style="font-size:14px">全选/反选</p><input type="checkbox" id="all"/></th>
			<th>标题</th>
			<th>作者</th>
			<th>发布时间</th>
			<th>评论次数</th>
			<th>是否发布</th>
			<th>操作</th>
		</tr>
		<tbody id="news">
		<c:forEach items="${newsList }" var="news" varStatus="status">
		<tr <%-- <c:if test="${status.index %2 !=0 }">style="background:#f1f1f1"</c:if>
		<c:if test="${status.index %2 ==0 }">style="background:white"</c:if> --%>>
			<td style="text-align: center; text-indent: 0px;"><input type="checkbox" value=${news.id } name="id" class="id"></td>
			<td width="40%">&nbsp;&nbsp;${news.title }</td>
			<td width="100px" style="text-align: center; text-indent: 0px;">${news.author }</td>
			<td>${news.publishTime }</td>
			<td style="text-align: center; text-indent: 0px;">${news.clickTimes }</td>
			<td>
				<%-- <a href="javascript:void(0)" val="${news.id }" class="upd">修改</a>
			   <a href="javascript:void(0)" val="${news.id }" class="del">删除</a> --%>
			    <c:if test="${news.isPublish=='1' }"><p style="color:blue">已发布</p></c:if>
			    <c:if test="${news.isPublish=='0' }"><p style="color:red">未发布</p></c:if>
			</td>
			<td><a href="javascript:void(0)" val="${news.id }" class="upd">修改</a></td>
		</tr>
		</c:forEach>
		</tbody>
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