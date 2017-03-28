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

	@Override
	public void register(User user) {
		String inputTime = DateUtil.getDate();
		String updateTime = DateUtil.getDate();
		user.setType("1");
		user.setInputTime(inputTime);
		user.setUpdateTime(updateTime);
		userDao.save(user);
	}

	@Override
	public void deleteUserById(String id) {
		userDao.deleteById(id);
	}

	@Override
	public void updateUser(User user) {
		String updateTime = DateUtil.getDate();
		user.setUpdateTime(updateTime);
		userDao.update(user);
	}

	@Override
	public String login(User user) {
		User user1 = userDao.queryById(user.getId());
		if(user1!=null){
			String password = user1.getPassword();
			String type = user1.getType();
			if(password.equals(user.getPassword())&&"0".equals(type)){
				System.out.println("111111");
				return "manager";
			}else{
				return "success";
			}
		}
		return "failed";
	}

	@Override
	public User findById(String id) {
		User user = userDao.queryById(id);
		String lastLoginTime = loginInfoDao.queryLastLoginTime(user.getId());
		user.setLastLoginTime(lastLoginTime);
		return user;
	}

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

	@Override
	public void saveLoginInfo(LoginInfo loginInfo) {
		String loginTime = DateUtil.getDate();
		loginInfo.setLoginTime(loginTime);
		loginInfoDao.save(loginInfo);
	}

	@Override
	public void Logout(LoginInfo loginInfo) {
		String logoutTime = DateUtil.getDate();
		loginInfo.setLogoutTime(logoutTime);
		loginInfoDao.setLogoutTime(loginInfo);
	}

	@Override
	public User findByName(String name) {
		User user = userDao.queryByName(name);
		String lastLoginTime = loginInfoDao.queryLastLoginTime(user.getId());
		user.setLastLoginTime(lastLoginTime);
		return user;
	}

	
}
