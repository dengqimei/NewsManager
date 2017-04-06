<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script>
//添加栏目
$(function(){
	var form=$("#addCategoryForm");
	form.off();
	form.on("submit",function(){
		form.ajaxSubmit(function(){
			form[0].reset();
			alert("添加成功！");
		});
		return false;
	});
});

</script>
<center>
<div>
	<form action="addCatalog.action" method="post" id="addCategoryForm">
	<table style="border-collapse: collapse; width: 100%;">
	    <caption style="font-size:24px;font-weight: bold;">添加栏目</caption>
		<tr height="30px"><td width="70px">栏目名称:</td><td><input type="text" name="name" /></td></tr> 
		<tr height="30px"><td width="70px">栏目序号:</td><td><input type="text"name="code" /></td></tr> 
		<tr height="30px">
			<td width="70px">栏目类型:</td>
			<td><select name="type">
				<option value="0">系统栏目</option>
				<option value="1">用户栏目</option>
			</select>
			</td>
		</tr> 
		<tr height="30px"><td colspan="2"><input type="submit" value="添加" /></td></tr>
	</table>
	</form>
</div>
</center>