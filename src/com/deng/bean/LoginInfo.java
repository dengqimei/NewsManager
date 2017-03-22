package com.deng.bean;

public class LoginInfo {

	private String sessionId;
	private String userId;
	private String userName;
	private String loginTime;
	private String logoutTime;
	
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
