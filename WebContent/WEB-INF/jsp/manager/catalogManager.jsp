<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
th {
	height:40px;
	background: #f5f5f5;
	font-size: 16px;
	font-family: 微软雅黑;
}
tr{
	height:35px;
}
td {
	font-size: 15px;
	font-family: 微软雅黑;
	text-align: center;
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
	
	//批量删除栏目
	function batchDel() {
		var delids = new Array();
		$('input[name="id"]:checked').each(function() {
			delids.push($(this).val());
		});
		if (delids.length == 0) {
			alert("请选择一条记录删除!");
		} else {
			var flag = confirm("确定删除已选中的栏目吗？");
			if(flag){
				$.ajax({
					url : "batchDelCatalog.action",
					data : "delids=" + delids,
					success : function(data) {
						alert(data);
						$(".baseUI li :contains('栏目管理')").trigger("click");
					}
				});
			}
		}
	}
	
	//批量启用栏目
	function batchInUse() {
		var updids = new Array();
		$('input[name="id"]:checked').each(function() {
			updids.push($(this).val());
		});
		if (updids.length == 0) {
			alert("请选择一条记录启用!");
		} else {
			var flag = confirm("确定启用已选中的栏目吗？");
			if(flag){
				$.ajax({
					url : "batchInUseCatalog.action",
					data : "updids=" + updids,
					success : function(data) {
						alert(data);
						$(".baseUI li :contains('栏目管理')").trigger("click");
					}
				});
			}
		}
	}
	
	//批量禁用栏目
	function batchUnUse() {
		var updids = new Array();
		$('input[name="id"]:checked').each(function() {
			updids.push($(this).val());
		});
		if (updids.length == 0) {
			alert("请选择一条记录禁用!");
		} else {
			var flag = confirm("确定禁用已选中的栏目吗？");
			if(flag){
				$.ajax({
					url : "batchUnUseCatalog.action",
					data : "updids=" + updids,
					success : function(data) {
						alert(data);
						$(".baseUI li :contains('栏目管理')").trigger("click");
					}
				});
			}
		}
	}
	
	//修改栏目
	$(function(){
		$(".upd").off();
		$(".upd").on("click",function(){
			var id=$(this).attr("val");
			$.post("toUpdCatalog.action",{id:id},function(){
				$(".right").load("toUpdCatalog.action",{id:id});
			});
		});
	});
	
	//翻页
	$(function(){
		$(".page").off();
		$(".page").on("click",function(){
			var currPage = $(this).attr("val");
			$.post("toCatalogManager.action",{currPage:currPage},function(){
				$(".right").load("toCatalogManager.action",{currPage:currPage});
			})
		})
	});
	
</script>
<!-- <p style="font-size: 24px; font-weight: bold;" align="center">所有栏目信息</p> -->
<div style="margin-bottom: 5px;">
	<input type="button" value="删除" onclick="batchDel()" />&nbsp; 
	<input type="button" value="启用" onclick="batchInUse()" />&nbsp; 
	<input type="button" value="禁用" onclick="batchUnUse()" />&nbsp;
</div>
<div class="catalogTable">
	<table border="1" style="border-collapse: collapse; width: 100%;">
		<tr>
			<th width="80px"><p style="font-size: 14px">全选/反选</p>
				<input type="checkbox" id="all" /></th>
			<th>栏目名称</th>
			<th>栏目编号</th>
			<th>是否启用</th>
			<th>修改</th>
		</tr>
	
		<c:forEach items="${catalogList }" var="catalog" varStatus="status">
			<tr
				<c:if test="${status.index %2 !=0 }">style="background:#f1f1f1"</c:if>
				<c:if test="${status.index %2 ==0 }">style="background:white"</c:if>>
				<td><input type="checkbox" name="id" value="${catalog.id }"
					name="id" class="id" /></td>
				<td>${catalog.name }</td>
				<td>${catalog.code }</td>
				<td>
					<c:if test="${catalog.isInuse=='1' }">
						<p style="color: blue">启用</p>
					</c:if> 
					<c:if test="${catalog.isInuse=='0' }">
						<p style="color: red">禁用</p>
					</c:if> 
					<%-- <a href="javascript:void(0)" val="${catalog.id }" class="upd">修改</a>
				    <a href="javascript:void(0)" val="${catalog.id }" class="del">删除</a> --%>
				</td>
				<td><a href="javascript:void(0)" val="${catalog.id }"
					class="upd">修改</a></td>
			</tr>
		</c:forEach>
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


<!-- <script type="text/javascript">
$(function(){
	$(".upd").off();
	$(".upd").on("click",function(){
		var id=$(this).attr("val");
		$.post("toUpdCatalog.action",{id:id},function(){
			$(".right").load("toUpdCatalog.action",{id:id});
		});
	});
	
	$(".del").off();
	$(".del").on("click",function(){
		var flag = confirm("确定删除吗？");
		if(flag){
		    var id = $(this).attr("val");
		    $.post("delCatalog.action",{id:id},function(){
			    $(".baseUI li :contains('栏目管理')").trigger("click");
			    alert("删除成功")
		    });
		}
	});		
});
	
</script> -->