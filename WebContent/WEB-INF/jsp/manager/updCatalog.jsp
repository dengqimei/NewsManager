<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script type="text/javascript">
//修改栏目信息
$(function(){
	var form=$("#updCatalogForm");
	form.off();
	form.on("submit",function(){
		form.ajaxSubmit(function(){
			form[0].reset;
			alert("修改成功！");
			$(".baseUI li :contains('栏目管理')").trigger("click");
		});
		return false;
	});	
});

</script>
<center>
<div>
	<form action="updCatalog.action" method="post" id="updCatalogForm">
	<input type="hidden" name="id" value="${catalog.id }">
	<table style="border-collapse: collapse; width: 100%;">
	    <caption style="font-size:24px;font-weight: bold;">修改栏目</caption>
		<tr height="30px"><td width="100px">栏目名称:</td><td><input type="text" name="name" value="${catalog.name }" /></td></tr> 
		<tr height="30px"><td width="100px">栏目编号:</td><td><input type="text"name="code" value="${catalog.code }"/></td></tr> 
		<tr height="30px"><td width="100px">添加栏目时间:</td><td><input type="text"name="inputTime" readonly="readonly" value="${catalog.inputTime }"/></td></tr> 
		<tr height="30px"><td width="100px">修改栏目时间:</td><td><input type="text"name="updateTime" readonly="readonly" value="${catalog.updateTime }"/></td></tr> 
		<tr height="30px"><td colspan="2"><input type="submit" value="修改" /></td></tr>
	</table>
	</form>
</div>
</center>