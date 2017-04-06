package com.deng.service;

import java.util.List;

import com.deng.bean.LoginInfo;
import com.deng.bean.User;
import com.deng.model.UserModel;

public interface IUserService {

	public void register(User user);
	
	public void deleteUserById(String id);
	
	public void updateUser(User user);
	
	public String login(User user);
	
	public User findById(String id);
	
	public User findByName(String name);
	
	public List<UserModel> findAll(Integer offset,Integer pageSize);
	
	public void saveLoginInfo(LoginInfo loginInfo);
	
	public void Logout(LoginInfo loginInfo);
	
	public int batchDel(String[] delids);
	
	public int batchInUse(String[] updids);
	
	public int batchUnUse(String[] updids);
	
	public int getPageCount(Integer pageSize);
	
}
