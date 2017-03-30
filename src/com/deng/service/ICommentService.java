package com.deng.service;

import java.util.List;

import com.deng.bean.Comment;
import com.deng.model.UserNewsCommentModel;

public interface ICommentService {

	public void addComment(Comment comment);

	public void deleteCommentById(Long id);

	public void updateComment(Comment comment);

	public Comment findCommentById(Long id);

	public List<UserNewsCommentModel> findAllComment();

	public List<UserNewsCommentModel> findUserComment(String user_id);
	
	public List<UserNewsCommentModel> findNewsComments(Long news_id);
	
	public int batchDel(String[] delids);
	
}
