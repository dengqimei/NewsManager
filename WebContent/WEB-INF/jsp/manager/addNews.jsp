<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	
	function check(){
		var title = $("#title").val();
		var author = $("#author").val();
		var catalog_id = $("#catalog_id").val();
		var content = $("#content").val();
		var uploadimage = $("#uploadimage").val();
		if(title==""||title==null){
			alert("新闻标题不能为空！！！");
			return false;
		}else if(author==""||author==null){
			$("#author").val("未知");
			return false;
		}else if(catalog_id==""||catalog_id==null){
			alert("请选择新闻所属栏目！！！");
			return false;
		}else if(content==""||content==null){
			alert("新闻内容不能为空！！！");
			return false;
		}else if(uploadimage==""||uploadimage==null){
			alert("上传图片不能为空！！！");
			return false;
		}else{
			return true;
		}
	}

	$(function(){
		var form = $("#addNewsForm");
		form.off();
		form.on("submit",function(){
			if(check()){
				form.ajaxSubmit(function(){
					form[0].reset();
					alert("添加成功！");
				});
			}
			return false;
		});
	});
</script>
<style>
    table tr{
        height:30px;
    }
</style>
<form action="addNews.action" method="post" id="addNewsForm" enctype="multipart/form-data">
	<table style="border-collapse: collapse; width: 100%;">
		<caption style="font-size:24px;font-weight: bold;">发布新闻</caption>
		<tr>
			<td width="70px">文章标题：</td>
			<td><input type="text" id="title" name="title"></td>
		</tr>
		<tr>
			<td>文章作者：</td>
			<td><input type="text" id="author" name="author"></td>
		</tr>
		<tr>
			<td>所属栏目：</td>
			<td><select name="catalog_id" id="catalog_id">
					<option value="">---请选中---</option>
					<c:forEach items="${catalogList }" var="catalog">
						<option value="${catalog.id }">${catalog.name }</option>
					</c:forEach>
			</select></td>
		</tr>
		<tr>
			<td>文章内容：</td>
			<td><textarea rows="5" cols="60" name="content" id="content"></textarea></td>
		</tr>
		<tr>
			<td>图片：</td>
			<td><input type="file" id=uploadimage name="uploadimage" accept="image/*"></td>
		</tr>
		<tr>
			<td><input type="submit" value="添加"></td>
		</tr>
	</table>
</form>