package com.deng.model;

import com.deng.bean.Comment;

public class UserCommentModel {

	private String username;
	private Comment comment;
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
	
}
