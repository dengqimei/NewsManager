<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <style>
        th{
            background: #f5f5f5;
            font-size:16px;
            font-family:微软雅黑;
        }
        td{
            font-size:15px;
            font-family:微软雅黑;
            text-align:center;
        }
    </style>
	<table border="1" style=" border-collapse:collapse; width:100%;">
	<caption style="font-size:24px;font-weight: bold;">所有栏目信息</caption>
		<tr>
			<th>编号</th>
			<th>栏目名称</th>
			<th>栏目编号</th>
			<th>操作</th>
		</tr>
		
		<c:forEach items="${catalogList }" var="catalog" varStatus="status">
		<tr <c:if test="${status.index %2 !=0 }">style="background:#f1f1f1"</c:if>
		<c:if test="${status.index %2 ==0 }">style="background:white"</c:if>>
			<td><input type="checkbox" name="id" value="${catalog.id }"/></td>
			<td>${catalog.name }</td>
			<td>${catalog.code }</td>
			<td>
			   <a href="javascript:void(0)" val="${catalog.id }" class="upd">修改</a>
			   <a href="javascript:void(0)" val="${catalog.id }" class="del">删除</a>
			</td>
		</tr>
		</c:forEach>
	</table>
<script>
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
	
</script>