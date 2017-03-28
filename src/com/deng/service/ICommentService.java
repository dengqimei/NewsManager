package com.deng.service;

import java.util.List;

import com.deng.bean.Comment;
import com.deng.model.UserCommentModel;

public interface ICommentService {

	public void addComment(Comment comment);

	public void deleteCommentById(Long id);

	public void updateComment(Comment comment);

	public Comment findCommentById(Long id);

	public List<Comment> findCommentByUser(Long user_id);

	public List<Comment> findCommentByNews(Long news_id);
	
	public List<UserCommentModel> findUserComments(Long news_id);

}
