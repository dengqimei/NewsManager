package com.deng.bean;

import java.util.Date;

public class Comment {

	private Long id;
	private String content;
	private Date publishTime;
	private Long user_id;
	private Long news_id;
	
	public Comment() {}

	public Comment(Long id, String content, Date publishTime, Long user_id, Long news_id) {
		this.id = id;
		this.content = content;
		this.publishTime = publishTime;
		this.user_id = user_id;
		this.news_id = news_id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Long getNews_id() {
		return news_id;
	}

	public void setNews_id(Long news_id) {
		this.news_id = news_id;
	}

}
