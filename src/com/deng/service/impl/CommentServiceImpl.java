package com.deng.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.deng.bean.Comment;
import com.deng.dao.ICommentDao;
import com.deng.dao.IUserDao;
import com.deng.model.UserCommentModel;
import com.deng.service.ICommentService;
import com.deng.util.DateUtil;
import com.deng.util.IdUtil;

public class CommentServiceImpl implements ICommentService{
	
	@Resource
	private ICommentDao commentDao;
	@Resource
	private IUserDao userDao;
	
	public void setCommentDao(ICommentDao commentDao){
		this.commentDao = commentDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void addComment(Comment comment) {
		String id = IdUtil.getId();
		String publishTime = DateUtil.getDate();
		comment.setId(Long.parseLong(id));
		comment.setPublishTime(publishTime);
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
	
	public List<UserCommentModel> findUserComments(Long news_id){
		List<UserCommentModel> list = new ArrayList<UserCommentModel>();
		List<Comment> commentList = commentDao.queryByNewsId(news_id);
		for(Comment comment : commentList){
			UserCommentModel model = new UserCommentModel();
			model.setComment(comment);
			String username = userDao.queryById(comment.getUser_id()).getName();
			model.setUsername(username);
			list.add(model);
		}
		return list;
	}

}
