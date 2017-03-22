package com.deng.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.deng.bean.User;
import com.deng.dao.ICodeDao;
import com.deng.dao.IUserDao;
import com.deng.model.UserModel;
import com.deng.service.IUserService;
import com.deng.util.DateUtil;

public class UserServiceImpl implements IUserService{
	
	@Resource
	private IUserDao userDao;
	@Resource ICodeDao codeDao;
	
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public void setCodeDao(ICodeDao codeDao) {
		this.codeDao = codeDao;
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
		userDao.update(user);
	}

	@Override
	public String login(User user) {
		String password = userDao.queryById(user.getId()).getPassword();
		String type = userDao.queryById(user.getId()).getType();
		if(password.equals(user.getPassword())&&"1".equals(type)){
			return "success";
		}
		return "failed";
	}

	@Override
	public User findById(String id) {
		return userDao.queryById(id);
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

	
}
