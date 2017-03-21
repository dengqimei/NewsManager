package com.deng.service.impl;

import javax.annotation.Resource;

import com.deng.bean.User;
import com.deng.dao.IUserDao;
import com.deng.service.IUserService;
import com.deng.util.DateUtil;

public class UserServiceImpl implements IUserService{
	
	@Resource
	//@Autowired
	private IUserDao userDao;
	
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
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

	
}
