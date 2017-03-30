<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">
th {
	background: #f5f5f5;
	font-size: 16px;
	font-family: 微软雅黑;
}

td {
	font-size: 15px;
	font-family: 微软雅黑;
	text-align: left;
	text-indent: 1em;
}
</style>

<script type="text/javascript">
	//全选/反选
	$("#all").click(function() {
		if (this.checked) {
			$(".id").attr("checked", true);
		} else {
			$(".id").attr("checked", false);
		}
	});

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

	/* function batchInUse() {
		var updids = new Array();
		$('input[name="id"]:checked').each(function() {
			updids.push($(this).val());
		});
		if (updids.length == 0) {
			alert("请选择一条记录启用!");
		} else {
			var flag = confirm("确定启用已选中的用户吗？");
			if (flag) {
				$.ajax({
					url : "batchInUseUser.action",
					data : "updids=" + updids,
					success : function(data) {
						alert(data);
						$(".baseUI li :contains('用户管理')").trigger("click");
					}
				});
			}
		}
	}

	function batchUnUse() {
		var updids = new Array();
		$('input[name="id"]:checked').each(function() {
			updids.push($(this).val());
		});
		if (updids.length == 0) {
			alert("请选择一条记录禁用!");
		} else {
			var flag = confirm("确定禁用已选中的用户吗？");
			if (flag) {
				$.ajax({
					url : "batchUnUseUser.action",
					data : "updids=" + updids,
					success : function(data) {
						alert(data);
						$(".baseUI li :contains('用户管理')").trigger("click");
					}
				});
			}
		}
	} */
	
</script>

<div>
	<p style="font-size: 24px; font-weight: bold;" align="center">所有用户信息</p>
	<div style="margin-bottom: 5px;">
		<input type="button" value="删除" onclick="batchDel()" />&nbsp; 
		<!-- <input type="button" value="启用" onclick="batchInUse()" />&nbsp;
		<input type="button" value="禁用" onclick="batchUnUse()" />&nbsp; -->
	</div>
	<table border="1" style="border-collapse: collapse; width: 100%;">
		<tr>
			<th><p style="font-size: 14px;">全选/反选</p>
				<input type="checkbox" id="all" /></th>
			<th>评论内容</th>
			<th>评论时间</th>
			<th>评论人</th>
			<th>新闻标题</th>
		</tr>
		<c:forEach items="${commentList }" var="list"
			varStatus="status">
			<tr
				<c:if test="${status.index %2 !=0 }">style="background:#f1f1f1"</c:if>
				<c:if test="${status.index %2 ==0 }">style="background:white"</c:if>>
				<td style="text-align: center; text-indent: 0px;"><input
					type="checkbox" value=${list.comment.id } name="id" class="id">
				</td>
				<td>${list.comment.content }</td>
				<td>${list.comment.publishTime }</td>
				<td>${list.username }</td>
				<td>${list.newstitle }</td>
			</tr>
		</c:forEach>
	</table>
</div>
