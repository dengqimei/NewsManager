package com.deng.bean;

public class LoginInfo {

	private String sessionId;//登录时的Session ID
	private String userId;//登录的用户名
	private String userName;//登录的用户名称
	private String loginTime;//登录时间
	private String logoutTime;//退出登录时间
	
	public LoginInfo() {}

	public LoginInfo(String sessionId, String userId, String userName, String loginTime, String logoutTime) {
		this.sessionId = sessionId;
		this.userId = userId;
		this.userName = userName;
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	}

	@Override
	public String toString() {
		return "LoginInfo [sessionId=" + sessionId + ", userId=" + userId + ", userName=" + userName + ", loginTime="
				+ loginTime + ", logoutTime=" + logoutTime + "]";
	}
	
}
