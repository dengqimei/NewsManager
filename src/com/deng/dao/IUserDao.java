package com.deng.dao;

import java.util.List;

import com.deng.bean.User;

public interface IUserDao {

	public void save(User user);
	
	public void deleteById(String id);
	
	public void update(User user);
	
	public User queryByUserName(String username);
	
	public User queryById(String id);
	
	public List<User> queryAll();
	
}
