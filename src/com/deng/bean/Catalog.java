package com.deng.bean;

public class Catalog {

	private Long id;//栏目编号
	private String name;//栏目名称
	private String code;//栏目序号
	private String inputTime;//栏目添加时间
	private String updateTime;//栏目更新时间
	private String isInuse;
	
	public Catalog() {}

	public Catalog(Long id, String name, String code, String inputTime, String updateTime) {
		this.id = id;
		this.name = name;
		this.code = code;
		this.inputTime = inputTime;
		this.updateTime = updateTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getIsInuse() {
		return isInuse;
	}

	public void setIsInuse(String isInuse) {
		this.isInuse = isInuse;
	}
	
}
