package com.deng.dao;

import java.util.List;

import com.deng.bean.LoginInfo;

public interface ILoginInfoDao {

	public void save(LoginInfo loginInfo);
	
	public void setLogoutTime(LoginInfo loginInfo);
	
	public List<LoginInfo> queryAll();
	
	public List<LoginInfo> queryByUserId(String userId);
	
	public String queryLastLoginTime (String userId);
	
}
