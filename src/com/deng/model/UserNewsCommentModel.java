package com.deng.model;

import com.deng.bean.Comment;

public class UserNewsCommentModel {

	private String username;//用户名称
	private Comment comment;//用户的评论信息
	private String newstitle;//新闻的标题
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	public String getNewstitle() {
		return newstitle;
	}
	public void setNewstitle(String newstitle) {
		this.newstitle = newstitle;
	}
	
}
