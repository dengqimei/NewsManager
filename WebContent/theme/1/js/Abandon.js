/**
 * 打开登录窗体
 */
function loginInfo(){
	var mask = document.getElementById("mask");
	mask.style.display = "block";
	var login = document.getElementById("login");
	login.style.display = "block";
}

/**
 * 关闭登录窗体
 */
function EscInfo(){
	var mask = document.getElementById("mask");
	mask.style.display = "none";
	var login = document.getElementById("login");
	login.style.display = "none";
}

function dianji(){
	var loginName = document.getElementById("loginName");
	loginName.value = "";
	loginName.border = "0px"
	}

/**
 * 登陆点击事件
 */


/**
 * 用户验证不能为空
 * @return
 */
function confirmation(){
	var loginName = document.getElementById("loginName");
	var loginPwd = document.getElementById("loginPwd");
	var login = document.getElementById("login_1_1");
	if((!(loginPwd.value == null || loginPwd.value == "")) && (!(loginName.value == "" || loginName.value == null || loginName.value =="请输入账号名或邮箱"))){
		return true;
	}else{
		login.innerHTML="账号或密码不能为空";
		return false;
	}
}







