package com.deng.bean;

public class User {

	private String id;
	private String name;
	private String password;
	private int age;
	private String sex;
	private String address;
	private String type;
	private String inputTime;
	private String updateTime;
	private String lastLoginTime;
	
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

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", age=" + age + ", sex=" + sex
				+ ", address=" + address + ", type=" + type + ", inputTime=" + inputTime + ", updateTime=" + updateTime
				+ "]";
	}
	
}
