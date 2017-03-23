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
	
	public List<UserModel> findAll();
	
	public void saveLoginInfo(LoginInfo loginInfo);
	
	public void Logout(LoginInfo loginInfo);
	
}
