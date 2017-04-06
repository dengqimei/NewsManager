package com.deng.bean;

public class Comment {

	private Long id;//评论ID
	private String content;//评论内容
	private String publishTime;//评论发布时间
	private String user_id;//评论人ID
	private Long news_id;//评论的新闻ID
	
	public Comment() {}

	public Comment(Long id, String content, String publishTime, String user_id, Long news_id) {
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

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Long getNews_id() {
		return news_id;
	}

	public void setNews_id(Long news_id) {
		this.news_id = news_id;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", content=" + content + ", publishTime=" + publishTime + ", user_id=" + user_id
				+ ", news_id=" + news_id + "]";
	}

}
