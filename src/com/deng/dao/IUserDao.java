package com.deng.dao;

import java.util.List;

import com.deng.bean.User;

public interface IUserDao {

	//保存用户信息
	public void save(User user);
	
	//通过用户ID删除用户信息
	public void deleteById(String id);
	
	//更新用户信息
	public void update(User user);
	
	//通过用户ID查询用户信息
	public User queryById(String id);
	
	//通过用户名称查询用户信息
	public User queryByName(String name);
	
	//查询所有用户信息并且分页
	public List<User> queryAll(Integer offset,Integer pageSize);
	
	//批量删除用户
	public int batchDel(String[] delids);
	
	//批量启用用户
	public int batchInUse(String[] updids);
	
	//批量禁用用户
	public int batchUnUse(String[] updids);
	
	//设为管理员
	public int setAdmin(String[] updids);
	
	//设为普通用户
	public int setCommonUser(String[] updids);
	
	//查询所有用户的分页数
	public int getPageCount(Integer pageSize);
	
}
