package com.deng.dao;

import java.util.List;

import com.deng.bean.User;

public interface IUserDao {

	public void save(User user);
	
	public void deleteById(String id);
	
	public void update(User user);
	
	public User queryById(String id);
	
	public User queryByName(String name);
	
	public List<User> queryAll();
	
	public int batchDel(String[] delids);
	
	public int batchInUse(String[] updids);
	
	public int batchUnUse(String[] updids);
	
}
