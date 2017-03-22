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
			<th>用户ID</th>
			<th>用户姓名</th>
			<th>用户类型</th>
			<th>地址</th>
			<th>注册时间</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${userModelList }" var="userModel" varStatus="status">
		<tr <c:if test="${status.index %2 !=0 }">style="background:#f1f1f1"</c:if>
		<c:if test="${status.index %2 ==0 }">style="background:white"</c:if>>
			<td>${userModel.user.id }</td>
			<td>${userModel.user.name }</td>
			<td>${userModel.type }</td>
			<td>${userModel.address }</td>
			<td>${userModel.user.inputTime }</td>
			<td>
			    <a href="javascript:void(0)" val="${userModel.user.id }" class="upd">修改</a>
			    <a href="javascript:void(0)" val="${userModel.user.id }" class="del">删除</a>
			</td>
		</tr>
		</c:forEach>
	</table>
</div>
<script type="text/javascript">
$(function(){
	$(".del").off();
	$(".del").on("click",function(){
		var flag = confirm("确定删除吗？");
		if(flag){
		    var id = $(this).attr("val");
		    $.post("delUser.action",{id:id},function(){
			    $(".baseUI li :contains('用户管理')").trigger("click");
			    alert("删除成功");
		    });
		}
	});	
});

$(function(){
	$(".upd").off();
	$(".upd").on("click",function(){
		var id=$(this).attr("val");
		$.post("toUpdUser.action",{id:id},function(){
			$(".right").load("toUpdUser.action",{id:id});
		});
	});
});

</script>