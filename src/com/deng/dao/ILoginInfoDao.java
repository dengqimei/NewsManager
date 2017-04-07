package com.deng.dao;

import java.util.List;

import com.deng.bean.LoginInfo;

public interface ILoginInfoDao {

	//保存登录信息
	public void save(LoginInfo loginInfo);
	
	//保存退出登录时间
	public void setLogoutTime(LoginInfo loginInfo);
	
	//查询所有登录信息
	public List<LoginInfo> queryAll();
	
	//查询用户的所有登录信息
	public List<LoginInfo> queryByUserId(String userId, Integer offset, Integer pageSize);
	
	//查询用户最后登录时间
	public String queryLastLoginTime (String userId);
	
	//查找登录信息的分页数
	public int getLoginInfoPageCount(Integer pageSize,String userId);
	
}
