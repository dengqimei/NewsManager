package com.deng.bean;

import java.util.Arrays;

public class Image {
	
	private int id;
	private String name;
	private byte[] picture;
	private String title;
	
	public Image() {}

	public Image(int id, String name, String title) {
		this.id = id;
		this.name = name;
		this.title = title;
	}

	public Image(int id, String name, byte[] picture, String title) {
		this.id = id;
		this.name = name;
		this.picture = picture;
		this.title = title;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getPicture() {
		return picture;
	}
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "Image [id=" + id + ", picture=" + Arrays.toString(picture) + ", title=" + title + "]";
	}
	
}
