package com.deng.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.deng.bean.Comment;
import com.deng.dao.ICommentDao;
import com.deng.service.ICommentService;

public class CommentServiceImpl implements ICommentService{
	
	@Resource
	private ICommentDao commentDao;
	public void setCommentDao(ICommentDao commentDao){
		this.commentDao = commentDao;
	}
	
	@Override
	public void addComment(Comment comment) {
		commentDao.save(comment);
	}

	@Override
	public void deleteCommentById(Long id) {
		commentDao.deleteById(id);
	}

	@Override
	public void updateComment(Comment comment) {
		commentDao.update(comment);
	}

	@Override
	public Comment findCommentById(Long id) {
		return commentDao.queryById(id);
	}

	@Override
	public List<Comment> findCommentByUser(Long user_id) {
		return commentDao.queryByUserId(user_id);
	}

	@Override
	public List<Comment> findCommentByNews(Long news_id) {
		return commentDao.queryByNewsId(news_id);
	}

}
