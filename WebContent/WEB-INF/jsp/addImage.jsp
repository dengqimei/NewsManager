<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加图片</title>
</head>
<body>
<form id="addImageForm" action="toSaveImage.action" method="post" enctype="multipart/form-data"> 
        编号：<input type="text" id="id" name="id"><p>
        名称：<input type="text" id="name" name="name"><p>
        标题：<input type="text" id="title" name="title"><p>
        图片： <input type="file" id="picture" name="pic"><p>
        <input type="submit" value="保存">   
</form>
</body>
</html>