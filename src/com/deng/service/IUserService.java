package com.deng.service;

import java.util.List;

import com.deng.bean.LoginInfo;
import com.deng.bean.User;
import com.deng.model.UserModel;

public interface IUserService {

	//用户注册
	public void register(User user);
	
	//通过ID删除用户
	public void deleteUserById(String id);
	
	//修改用户信息
	public void updateUser(User user);
	
	//用户登录
	public String login(User user);
	
	//通过用户ID查找用户
	public User findById(String id);
	
	//通过用户名称查找用户
	public User findByName(String name);
	
	//查找所有用户并且分页
	public List<UserModel> findAll(Integer offset,Integer pageSize);
	
	//保存用户登录信息
	public void saveLoginInfo(LoginInfo loginInfo);
	
	//保存用户退出登录时间
	public void Logout(LoginInfo loginInfo);
	
	//批量删除永固
	public int batchDel(String[] delids);
	
	//批量启用用户
	public int batchInUse(String[] updids);
	
	//批量禁用用户
	public int batchUnUse(String[] updids);
	
	//查找分页数
	public int getPageCount(Integer pageSize);
	
}
