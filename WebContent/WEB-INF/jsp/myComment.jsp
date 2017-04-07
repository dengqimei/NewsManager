<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
th {
	height: 40px;
	background: #f5f5f5;
	font-size: 16px;
	font-family: 微软雅黑;
}

tr {
	height: 35px;
}

td {
	font-size: 15px;
	font-family: 微软雅黑;
	text-align: left;
	text-indent: 1em;
}
</style>

<script type="text/javascript">
	//设置表格颜色
	$(function initTableColor() {
		$("tr:even").css("background", "#f1f1f1");
		$("tr:odd").css("background", "white");
	});
	//全选/反选
	$("#all").click(function() {
		if (this.checked) {
			$(".id").attr("checked", true);
		} else {
			$(".id").attr("checked", false);
		}
	});

	//批量删除评论
	function batchDel() {
		var delids = new Array();
		$('input[name="id"]:checked').each(function() {
			delids.push($(this).val());
		});
		if (delids.length == 0) {
			alert("请选择一条记录删除!");
		} else {
			var flag = confirm("确定删除已选中的评论信息吗？");
			if (flag) {
				$.ajax({
					url : "batchDelComment.action",
					data : "delids=" + delids,
					success : function(data) {
						alert(data);
						$(".baseUI li :contains('评论管理')").trigger("click");
					}
				});
			}
		}
	}

	//翻页
	$(function(){
		$(".page").off();
		$(".page").on("click",function(){
			var username = $("#username").attr("val");
			var currPage = $(this).attr("val");
			$.post("showUserInfo.action",{currPage:currPage,catalogName:"我的评论",userName:username},function(){
				$(".userinfo").load("showUserInfo.action",{currPage:currPage,catalogName:"我的评论",userName:username});
			})
		})
	});
	
</script>

<div>
	<!-- <p style="font-size: 24px; font-weight: bold;" align="center">所有用户信息</p> -->
	<div style="margin-bottom: 5px;">
		<input type="button" value="删除" onclick="batchDel()" />&nbsp;
		<!-- <input type="button" value="启用" onclick="batchInUse()" />&nbsp;
		<input type="button" value="禁用" onclick="batchUnUse()" />&nbsp; -->
	</div>
	<div class="commentList">
		<table border="1" style="border-collapse: collapse; width: 100%;">
			<tr>
				<th><p style="font-size: 14px;">全选/反选</p> <input
					type="checkbox" id="all" /></th>
				<th>评论内容</th>
				<th>评论时间</th>
				<th>评论人</th>
				<th>新闻标题</th>
			</tr>
			<c:forEach items="${commentList }" var="list" varStatus="status">
				<tr
					<c:if test="${status.index %2 !=0 }">style="background:#f1f1f1"</c:if>
					<c:if test="${status.index %2 ==0 }">style="background:white"</c:if>>
					<td style="text-align: center; text-indent: 0px;"><input
						type="checkbox" value=${list.comment.id } name="id" class="id">
					</td>
					<td style="width: 35%">${list.comment.content }</td>
					<td>${list.comment.publishTime }</td>
					<td>${list.username }</td>
					<td>${list.newstitle }</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<c:if test="${currPage>1}">
		<a href="javascript:void(0)" class="page" val="1" style="color: blue;">首页</a>
		<a href="javascript:void(0)" class="page" val="${currPage-1 }"
			style="color: blue;">上一页</a>
	</c:if>
	<c:if test="${currPage==1}">
		<span class="disabled">首页</span>
		<span class="disabled">上一页</span>
	</c:if>
	${currPage}/${pageCount}
	<c:if test="${currPage<pageCount}">
		<a href="javascript:void(0)" class="page" val="${currPage+1 }"
			style="color: blue;">下一页</a>
		<a href="javascript:void(0)" class="page" val="${pageCount }"
			style="color: blue;">尾页</a>
	</c:if>
	<c:if test="${currPage==pageCount}">
		<span class="disabled">下一页</span>
		<span class="disabled">尾页</span>
	</c:if>
</div>