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
import com.deng.util.MD5;

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
	
	//检查用户ID或
	@Override
	public String checkUserIsExist(String userid,String username){
		User user1 = userDao.queryById(userid);
		User user2 = userDao.queryByName(username);
		if(user1!=null){
			return "userid exist";
		}else if (user2!=null){
			return "username exist";
		}else{
			return "not exist";
		}
	}
	
	//删除用户
	@Override
	public void deleteUserById(String id) {
		userDao.deleteById(id);
	}

	//修改用户信息
	@Override
	public void updateUser(User user) {
		String password  = userDao.queryById(user.getId()).getPassword();
		user.setPassword(password);
		String updateTime = DateUtil.getDate();
		user.setUpdateTime(updateTime);
		userDao.update(user);
	}
	
	//用户登录
	@Override
	public String login(String userid,String pwd,LoginInfo loginInfo) {
		String password = MD5.getInstance().getMD5ofStr(pwd);
		User user = userDao.queryById(userid);
		if(user!=null){
			if(password.equals(user.getPassword())&&"1".equals(user.getIsInuse())){
				String loginTime = DateUtil.getDate();
				loginInfo.setUserName(user.getName());
				loginInfo.setLoginTime(loginTime);
				loginInfoDao.save(loginInfo);
				return "success";
			}else if("0".equals(user.getIsInuse())){
				return "用户已经被禁用，请联系系统管理员！";
			}else{
				return "密码错误！！！";
			}
		}else{
			return "用户名ID不存在！！！";
		}
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
	public List<UserModel> findAll(Integer offset,Integer pageSize) {
		List<UserModel> userModel = new ArrayList<UserModel>();
		List<User> userList = userDao.queryAll(offset, pageSize);
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
	
	//设为管理员
	@Override
	public int setAdmin(String[] updids){
		if(updids.length==0){
			return -1;
		}else{
			return userDao.setAdmin(updids);
		}
	}
		
	//设为普通用户
	@Override
	public int setCommonUser(String[] updids){
		if(updids.length==0){
			return -1;
		}else{
			return userDao.setCommonUser(updids);
		}
	}

	//查找所有用户分页数
	@Override
	public int getPageCount(Integer pageSize) {
		return userDao.getPageCount(pageSize);
	}

	//修改密码
	@Override
	public String updPassword(String userName, String oldPWD, String newPWD) {
		String oldPassword = MD5.getInstance().getMD5ofStr(oldPWD);
		String newPassword = MD5.getInstance().getMD5ofStr(newPWD);
		User user = userDao.queryByName(userName);
		if(user!=null){
			String password = user.getPassword();
			if(oldPassword.equals(password)){
				user.setPassword(newPassword);
				userDao.update(user);
				return "修改成功，下次登录请输入新密码！！！";
			}else{
				return "原始密码不正确！";
			}
		}else{
			return "用户不存在！";
		}
	}

	//查询用户登录信息
	@Override
	public List<LoginInfo> findLoginInfo(String userId, Integer offset, Integer pageSize) {
		return loginInfoDao.queryByUserId(userId, offset, pageSize);
	}

	//用户的登录信息分页数
	@Override
	public int getLoginInfoPageCount(Integer pageSize, String userId) {
		return loginInfoDao.getLoginInfoPageCount(pageSize, userId);
	}

	
}
