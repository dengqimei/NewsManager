package com.deng.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.deng.bean.Comment;
import com.deng.bean.News;
import com.deng.dao.ICommentDao;
import com.deng.dao.INewsDao;
import com.deng.dao.IUserDao;
import com.deng.model.UserNewsCommentModel;
import com.deng.service.ICommentService;
import com.deng.util.DateUtil;
import com.deng.util.IdUtil;

public class CommentServiceImpl implements ICommentService{
	
	@Resource
	private ICommentDao commentDao;
	@Resource
	private IUserDao userDao;
	@Resource
	private INewsDao newsDao;
	
	public void setCommentDao(ICommentDao commentDao){
		this.commentDao = commentDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public void setNewsDao(INewsDao newsDao) {
		this.newsDao = newsDao;
	}

	@Override
	public void addComment(Comment comment) {
		String id = IdUtil.getId();
		String publishTime = DateUtil.getDate();
		comment.setId(Long.parseLong(id));
		comment.setPublishTime(publishTime);
		Long newsId = comment.getNews_id();
		News news = newsDao.queryById(newsId);
		int count = news.getClickTimes();
		count = count+1;
		news.setClickTimes(count);
		newsDao.update(news);
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
	public List<UserNewsCommentModel> findAllComment(Integer offset,Integer pageSize) {
		List<UserNewsCommentModel> list = new ArrayList<UserNewsCommentModel>();
		List<Comment> commentList = commentDao.queryAll(offset,pageSize);
		for(Comment comment : commentList){
			UserNewsCommentModel model = new UserNewsCommentModel();
			model.setComment(comment);
			String username = userDao.queryById(comment.getUser_id()).getName();
			String newstitle = newsDao.queryById(comment.getNews_id()).getTitle();
			model.setUsername(username);
			model.setNewstitle(newstitle);
			list.add(model);
		}
		return list;
	}

	@Override
	public List<UserNewsCommentModel> findUserComment(String user_id,Integer offset,Integer pageSize) {
		List<UserNewsCommentModel> list = new ArrayList<UserNewsCommentModel>();
		List<Comment> commentList = commentDao.queryByUserId(user_id,offset,pageSize);
		for(Comment comment : commentList){
			UserNewsCommentModel model = new UserNewsCommentModel();
			model.setComment(comment);
			String username = userDao.queryById(user_id).getName();
			String newstitle = newsDao.queryById(comment.getNews_id()).getTitle();
			model.setUsername(username);
			model.setNewstitle(newstitle);
			list.add(model);
		}
		return list;
	}
	
	public List<UserNewsCommentModel> findNewsComments(Long news_id){
		List<UserNewsCommentModel> list = new ArrayList<UserNewsCommentModel>();
		List<Comment> commentList = commentDao.queryByNewsId(news_id);
		for(Comment comment : commentList){
			UserNewsCommentModel model = new UserNewsCommentModel();
			model.setComment(comment);
			String username = userDao.queryById(comment.getUser_id()).getName();
			String newstitle = newsDao.queryById(news_id).getTitle();
			model.setUsername(username);
			model.setNewstitle(newstitle);
			list.add(model);
		}
		return list;
	}

	@Override
	public int batchDel(String[] delids) {
		if(delids.length==0){
			return -1;
		}else{
			return commentDao.batchDel(delids);
		}
	}

	@Override
	public int getPageCount(Integer pageSize){
		return commentDao.getPageCount(pageSize);
	}

	@Override
	public int getPageCountByUser(Integer pageSize, String userid) {
		return commentDao.getPageCountByUser(pageSize, userid);
	}

	@Override
	public int getPageCountByC(Integer pageSize, Long c_id) {
		return commentDao.getPageCountByC(pageSize, c_id);
	}
	
}
