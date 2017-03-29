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
			var flag = confirm("确定删除已选中的用户吗？");
			if(flag){
				$.ajax({
					url : "batchDelUser.action",
					data : "delids=" + delids,
					success : function(data) {
						alert(data);
						$(".baseUI li :contains('用户管理')").trigger("click");
					}
				});
			}
		}
	}
	
	function batchInUse() {
		var updids = new Array();
		$('input[name="id"]:checked').each(function() {
			updids.push($(this).val());
		});
		if (updids.length == 0) {
			alert("请选择一条记录启用!");
		} else {
			var flag = confirm("确定启用已选中的用户吗？");
			if(flag){
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
			if(flag){
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
	}
	
</script>
   
<div>
	<p style="font-size:24px;font-weight: bold;" align="center">所有用户信息</p>
	<div style="margin-bottom:5px;">
		<input type="button" value="删除" onclick="batchDel()"/>&nbsp;
		<input type="button" value="启用" onclick="batchInUse()"/>&nbsp;
		<input type="button" value="禁用" onclick="batchUnUse()"/>&nbsp;
	</div>
	<table border="1" style=" border-collapse:collapse; width:100%;">
		<tr>
			<th><p style="font-size:14px">全选/反选</p><input type="checkbox" id="all"/></th>
			<th>用户ID</th>
			<th>用户姓名</th>
			<th>用户类型</th>
			<th>地址</th>
			<th>注册时间</th>
			<th>是否启用</th>
		</tr>
		<c:forEach items="${userModelList }" var="userModel" varStatus="status">
		<tr <c:if test="${status.index %2 !=0 }">style="background:#f1f1f1"</c:if>
		<c:if test="${status.index %2 ==0 }">style="background:white"</c:if>>
			<td><input type="checkbox" value=${userModel.user.id } name="id" class="id"></td>
			<td>${userModel.user.id }</td>
			<td>${userModel.user.name }</td>
			<td>${userModel.type }</td>
			<td>${userModel.address }</td>
			<td>${userModel.user.inputTime }</td>
			<td>
				<c:if test="${userModel.user.isInuse=='1' }"><p style="color:blue">启用</p></c:if>
			    <c:if test="${userModel.user.isInuse=='0' }"><p style="color:red">禁用</p></c:if>
			    <%-- <a href="javascript:void(0)" val="${userModel.user.id }" class="upd">修改</a>
			    <a href="javascript:void(0)" val="${userModel.user.id }" class="del">删除</a> --%>
			</td>
		</tr>
		</c:forEach>
	</table>
</div>
<!-- <script type="text/javascript">
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

</script> -->