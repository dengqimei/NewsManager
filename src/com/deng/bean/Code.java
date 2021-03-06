package com.deng.bean;

public class Code {

	private String id;//code ID
	private String name;//名称
	
	public Code() {}

	public Code(String id, String name) {
		this.id = id;
		this.name = name;
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

	@Override
	public String toString() {
		return "Address [id=" + id + ", name=" + name + "]";
	}

}