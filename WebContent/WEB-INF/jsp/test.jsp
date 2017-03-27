<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<script type="text/javascript" src="theme/1/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="theme/1/js/Abandon.js"></script>
<script type="text/javascript" src="theme/1/js/test.js"></script>
<script type="text/javascript" src="theme/1/js/common.js"></script>
<script type="text/javascript">
	
	function getOs() { 
	   var OsObject = ""; 
	   if(navigator.userAgent.indexOf("MSIE")>0) { 
	        return "MSIE";
	   } 
	   if(isFirefox=navigator.userAgent.indexOf("Firefox")>0){ 
	        return "Firefox"; 
	   } 
	   if(isSafari=navigator.userAgent.indexOf("Safari")>0) { 
	        return "Safari"; 
	   }  
	} 
	if(getOs() == "MSIE"){
		document.write("<link rel='stylesheet' type='text/css' href='theme/1/css/IEstyles.css'>");
	}
	if(getOs() == "Firefox"){
		document.write("<link rel='stylesheet' type='text/css' href='theme/1/css/FFstyles.css'>");
	}
	if(getOs() == "Safari"){
		document.write("<link rel='stylesheet' type='text/css' href='theme/1/css/GGstyles.css'>");
	}

	$(function(){
		$("#commit").click(function(){
			if($("#loginName").val() == null || $("#loginName").val() == "请输入账号名或邮箱" || $("#loginName").val() == ""){
				$("#login_1_1").html("账号不能为空！");
			}else if($("#loginPwd").val() == null || $("#loginPwd").val() == ""){
				$("#login_1_1").html("密码不能为空！");
			}else{
				$.ajax({dataType: "text",type: "post",url: "login.action",data: "name="+$("#loginName").val()+"&"+"pwd="+$("#loginPwd").val(),success: function(msg){
				    if(msg.trim() == "true"){
						location.reload();
					}else{
						$("#login_1_1").html("用户名或密码错误");
					}
				   }
				});
			}
		});
	});
</script>
</head>
<body>
<a href="#" title="登陆" id="login1"
				onclick="loginInfo()">登录</a>
				
<!-- 半透明背景 -->
<div class="mask" id="mask" style="display:none;"></div>
<!-- 登陆 -->
<div class="login" id="login" style="display: none;">
	<div class="login_1">
		<a title="关闭" onclick="EscInfo()">
			<img src="theme/1/images/front/logo-2-2.jpg" />
		</a>
	</div>
	<div class="login_2"></div>
	<div class="login_3">
		<div class="login_3_0">
			<p id="login_1_1"></p>
		</div>
		<div class="login_3_1">
			<label>帐号：</label>
			<input type="text" placeholder="中英文，6-18位" maxlength="15" id="loginName" name="loginName" onclick="clickLogin()" onblur="mouserLogin()" />
		</div>
		<br />
		<div class="login_3_1">
			<label>密码：</label>
			<input type="password" maxlength="25" placeholder="英文或数字，8-25位" id="loginPwd" name="loginPwd"/>
		</div>
		<div class="login_3_2">
			<div class="login_3_2_1">
				<input type="submit" value="" id = "commit" />
			</div>
			<div class="login_3_2_2">
				<a href="#" title="注册用户">没有账号？注册</a>
				<a href="#" title="忘记密码">忘记密码？</a>
			</div>
		</div>
	</div>
</div>
</body>
</html>