<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台首页</title>
<link rel="stylesheet" type="text/css" href="../theme/1/css/common.css">
<link rel="stylesheet" type="text/css" href="../theme/1/css/style.css">
<link rel="stylesheet" type="text/css" href="../theme/1/css/table.css">
<link rel="stylesheet" type="text/css" href="../theme/1/css/icon.css">
<link rel="stylesheet" type="text/css" href="../theme/1/jquery-easyui-themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../theme/1/css/easyui-tabs.css">
<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../js/jquery.form.js"></script>
<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../theme/1/js/base.js"></script>
</head>

<script type="text/javascript">
    $(function(){
    	$(".baseUI li").on("click",function(){
    		var url=$(this).attr("url");
    		$(".right").load(url);
    	});
    	$(".baseUI li").filter(":first").trigger("click");
    });
</script>
<body>
	<div class="main">
		<div id="top">
			<div class="top">
				<div class="t_logo"></div>
				<div class="t_sign">
					<span>你好，${sessionScope.username }</span><a href="logout.action" class="t_exit" style="visibility:hidden">退出</a>
				</div>
				<div style="position: absolute;top:10px;right: 20px">
					<a href="../toIndex.action" target="_blank">跳转到前台首页</a>
				</div>
			</div>
		</div>
		<div id="cont">
			<div class="centent">
				<div class="left">
					<ul class="baseUI">
						<li url="toAddCatalog.action"><a href="javascript:void(0)"> <em class="base_basicset"></em><span>添加栏目</span>
						</a></li>
						<li url="toCatalogManager.action"><a href="javascript:void(0)"> <em class="base_userset"></em><span>栏目管理</span>
						</a></li>
						<li url="toAddNews.action"><a href="javascript:void(0)"> <em class="base_teachergroup"></em><span>新闻发布</span>
						</a></li>
						<li url="toNewsManager.action"><a href="javascript:void(0)"> <em class="base_roll"></em><span>新闻管理</span>
						</a></li>
						<li url="toUserManager.action"><a href="javascript:void(0)"> <em class="base_auth"></em><span>用户管理</span>
						</a></li>
						<li url="toCommentManager.action"><a href="javascript:void(0)"> <em class="base_sys"></em><span>评论管理</span>
						</a></li>
					</ul>
				</div>
				<div class="right" style="padding:30px">
				
				</div>
				<div class="clear"></div>
			</div>
		</div>
		<div id="foot">
			<a href="javascript:void(0)">信息科技有限公司</a>
		</div>
	</div>
</body>
</html>