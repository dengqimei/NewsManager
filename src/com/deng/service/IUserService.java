package com.deng.service;

import com.deng.bean.User;

public interface IUserService {

	public void register(User user);
	
	public void deleteUserById(String id);
	
	public void updateUser(User user);
	
	public String login(User user);
	
	public User findById(String id);
	
	
}
