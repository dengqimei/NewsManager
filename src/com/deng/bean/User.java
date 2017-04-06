package com.deng.bean;

public class User {

	private String id;//用户名ID
	private String name;//用户名称
	private String password;//用户密码
	private int age;//用户年龄
	private String sex;//用户性别
	private String address;//用户地址
	private String type;//用户类型
	private String inputTime;//用户注册时间
	private String updateTime;//用户修改时间
	private String lastLoginTime;//用户最后登录时间
	private String isInuse;//用户是否启用
	
	public User() {}

	public User(String id, String name, String password, int age, String sex, String address, String type, String inputTime,
			String updateTime) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.age = age;
		this.sex = sex;
		this.address = address;
		this.type = type;
		this.inputTime = inputTime;
		this.updateTime = updateTime;
	}

	public User(String id, String name, String password, int age, String sex, String address, String inputTime, String updateTime) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.age = age;
		this.sex = sex;
		this.address = address;
		this.inputTime = inputTime;
		this.updateTime = updateTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getInputTime() {
		return inputTime;
	}

	public void setInputTime(String inputTime) {
		this.inputTime = inputTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getIsInuse() {
		return isInuse;
	}

	public void setIsInuse(String isInuse) {
		this.isInuse = isInuse;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", age=" + age + ", sex=" + sex
				+ ", address=" + address + ", type=" + type + ", inputTime=" + inputTime + ", updateTime=" + updateTime
				+ "]";
	}
	
}
