<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">
th{
    background:#f5f5f5;
    font-size:16px;
    font-family:微软雅黑;
}
td{
    font-size:15px;
    font-family:微软雅黑;
    text-align:left;
    text-indent: 1em;
}
</style>
<script type="text/javascript">

 	$(function(){
		initCatalogs();
		initTableColor();
		queryAll();
 	});
 	
 	//设置表格颜色
 	function initTableColor() {
		$("tr:even").css("background", "#f1f1f1");
		$("tr:odd").css("background", "white");
	}
 	
 	//初始化栏目下拉框
 	function initCatalogs(){
 		$.ajax({
 			type : "POST",
 			url : "showCatalog.action",
 			Accept:"application/json",
 			contentType : "application/json",
 			dataType : "json",
 			success : function(data) {
 				$.each(data, function(i, catalog) {
 					$("<option value='" + catalog.id + "'>"
 						+ catalog.name + "</option><br>").click(function() {
 								showNews(catalog.id);
 					}).appendTo($('#catalog')); 
 				});
 			}
 		});
 	}
 	
 	//根据栏目显示新闻信息
	function showNews(catalog_id) {
		$("#news").empty();
		$.ajax({
			type : "POST",
			url : "showNewsByCatalog.action?catalog_id=" + catalog_id,
			Accept : "application/json",
			contentType : "application/json",
			dataType : "json",
			success : function(data) {
				var isPublish = "";
				$.each(data, function(i, news) {
					isPublish = news.isPublish;
					$("#news").append(
						"<tr><td style='text-align: center; text-indent: 0px;'>"+
						"<input type='checkbox' value="+news.id+" name='id' class='id'>"+
						"</td><td style='text-align: left;'>&nbsp;&nbsp;"+ news.title + 
						"</td><td width='100px' style='text-align: center; text-indent: 0px;'>" 
						+ news.author+ "</td><td>"+news.publishTime+ "</td><td style="+
						"'text-align: center; text-indent: 0px;'>" + news.clickTimes+ "</td>"+
						"<td><p id='ispublish'>"+
						(isPublish==0?'<p style="color:red">未发布</p>':'<p style="color:blue">已发布</p>')+
						"</p></td><td><a href='javascript:void(0)' val="+news.id+" class='upd'>修改</a></td></tr>"
					);
				});
				initTableColor();
			}
		});
	}
	
	//查询所有新闻信息
	function queryAll() {
		$(".query").off();
		$(".query").on("click", function() {
			$(".right").load("toNewsManager.action");
		});
	}

	//批量删除
	function batchDel() {
		var delids = new Array();
		$('input[name="id"]:checked').each(function() {
			delids.push($(this).val());
		});
		if (delids.length == 0) {
			alert("请选择一条记录删除!");
		} else {
			var flag = confirm("确定删除吗？");
			if(flag){
				$.ajax({
					url : "batchDelNews.action",
					data : "delids=" + delids,
					success : function(data) {
						alert(data);
						$(".baseUI li :contains('新闻管理')").trigger("click");
					}
				});
			}
		}
	}

	//批量发布
	function batchPublish() {
		var updids = new Array();
		$('input[name="id"]:checked').each(function() {
			updids.push($(this).val());
		});
		if (updids.length == 0) {
			alert("请选择一条记录发布!");
		} else {
			var flag = confirm("确定发布已选中的新闻吗？");
			if(flag){
				$.ajax({
					url : "batchPublishNews.action",
					data : "updids=" + updids,
					success : function(data) {
						alert(data);
						$(".baseUI li :contains('新闻管理')").trigger("click");
					}
				});
			}
		}
	}
	
	//批量取消发布
	function batchCancel() {
		var updids = new Array();
		$('input[name="id"]:checked').each(function() {
			updids.push($(this).val());
		});
		if (updids.length == 0) {
			alert("请选择一条记录取消发布!");
		} else {
			var flag = confirm("确定取消发布已选中的新闻吗？");
			if(flag){
				$.ajax({
					url : "batchCancelNews.action",
					data : "updids=" + updids,
					success : function(data) {
						alert(data);
						$(".baseUI li :contains('新闻管理')").trigger("click");
					}
				});
			}
		}
	}

	//全选/反选
	$("#all").click(function() {
		if (this.checked) {
			$(".id").attr("checked", true);
		} else {
			$(".id").attr("checked", false);
		}
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
    
<div>
	<div style="margin-bottom:5px;">
		<input type="button" value="删除" onclick="batchDel()"/>&nbsp;
		<input type="button" value="发布" onclick="batchPublish()"/>&nbsp;
		<input type="button" value="取消发布" onclick="batchCancel()"/>&nbsp;
		<input class="query" type="button" value="查询所有新闻信息">&nbsp;
		<select id="catalog"><option class="query">---请选择栏目---</option></select>
	</div>
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
</div>
<!-- <script type="text/javascript">
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
</script> -->