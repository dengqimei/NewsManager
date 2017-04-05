package com.deng.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.deng.bean.LoginInfo;
import com.deng.bean.User;
import com.deng.dao.ICodeDao;
import com.deng.dao.ILoginInfoDao;
import com.deng.dao.IUserDao;
import com.deng.model.UserModel;
import com.deng.service.IUserService;
import com.deng.util.DateUtil;

public class UserServiceImpl implements IUserService{
	
	@Resource
	private IUserDao userDao;
	@Resource 
	private ICodeDao codeDao;
	@Resource 
	private ILoginInfoDao loginInfoDao ;
	
	
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public void setCodeDao(ICodeDao codeDao) {
		this.codeDao = codeDao;
	}

	public void setLoginInfoDao(ILoginInfoDao loginInfoDao) {
		this.loginInfoDao = loginInfoDao;
	}

	//用户注册
	@Override
	public void register(User user) {
		String inputTime = DateUtil.getDate();
		String updateTime = DateUtil.getDate();
		user.setType("1");
		user.setInputTime(inputTime);
		user.setUpdateTime(updateTime);
		user.setIsInuse("1");
		userDao.save(user);
	}
	
	//删除用户
	@Override
	public void deleteUserById(String id) {
		userDao.deleteById(id);
	}

	//修改用户信息
	@Override
	public void updateUser(User user) {
		String updateTime = DateUtil.getDate();
		user.setUpdateTime(updateTime);
		userDao.update(user);
	}
	
	//用户登录
	@Override
	public String login(User user) {
		User user1 = userDao.queryById(user.getId());
		if(user1!=null&&"1".equals(user1.getIsInuse())){  //用户是否存在、如果存在，是否启用
			String password = user1.getPassword();
			String type = user1.getType();
			if(password.equals(user.getPassword())&&"0".equals(type)){
				return "manager";
			}else if(password.equals(user.getPassword())&&"1".equals(type)){
				return "success";
			}else{
				return "failed";
			}
		}
		return "";
	}

	//通过ID查找用户
	@Override
	public User findById(String id) {
		User user = userDao.queryById(id);
		if(user!=null){
			String lastLoginTime = loginInfoDao.queryLastLoginTime(user.getId());
			user.setLastLoginTime(lastLoginTime);
		}
		return user;
	}
	
	//查询所有用户信息
	@Override
	public List<UserModel> findAll() {
		List<UserModel> userModel = new ArrayList<UserModel>();
		List<User> userList = userDao.queryAll();
		for(User user : userList){
			UserModel model = new UserModel();
			model.setUser(user);
			String address = codeDao.queryAddressById(user.getAddress()).getName();
			String type = codeDao.queryUserType(user.getType()).getName();
			String sex = codeDao.queryUserSex(user.getSex()).getName();
			model.setAddress(address);
			model.setType(type);
			model.setSex(sex);
			userModel.add(model);
		}
		return userModel;
	}
	
	//保存用户登录信息
	@Override
	public void saveLoginInfo(LoginInfo loginInfo) {
		String loginTime = DateUtil.getDate();
		loginInfo.setLoginTime(loginTime);
		loginInfoDao.save(loginInfo);
	}
	
	//用户退出登录
	@Override
	public void Logout(LoginInfo loginInfo) {
		String logoutTime = DateUtil.getDate();
		loginInfo.setLogoutTime(logoutTime);
		loginInfoDao.setLogoutTime(loginInfo);
	}
	
	//通过用户名查找用户
	@Override
	public User findByName(String name) {
		User user = userDao.queryByName(name);
		System.out.println("======================");
		System.out.println(user);
		String lastLoginTime = loginInfoDao.queryLastLoginTime(user.getId());
		user.setLastLoginTime(lastLoginTime);
		return user;
	}

	//批量删除用户
	@Override
	public int batchDel(String[] delids) {
		if(delids.length==0){
			return -1;
		}else{
			return userDao.batchDel(delids);
		}
	}

	//批量启用用户
	@Override
	public int batchInUse(String[] updids) {
		if(updids.length==0){
			return -1;
		}else{
			return userDao.batchInUse(updids);
		}
	}
	
	//批量禁用用户
	@Override
	public int batchUnUse(String[] updids) {
		if(updids.length==0){
			return -1;
		}else{
			return userDao.batchUnUse(updids);
		}
	}

	
}
