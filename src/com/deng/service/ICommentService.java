package com.deng.service;

import java.util.List;

import com.deng.bean.Comment;
import com.deng.model.UserNewsCommentModel;

public interface ICommentService {

	//添加评论
	public void addComment(Comment comment);

	//删除评论
	public void deleteCommentById(Long id);

	//修改评论
	public void updateComment(Comment comment);

	//通过评论ID查找评论
	public Comment findCommentById(Long id);

	//查找所有评论并且分页
	public List<UserNewsCommentModel> findAllComment(Integer offset,Integer pageSize);
	
	//查找用户的所有评论
	public List<UserNewsCommentModel> findUserComment(String user_id,Integer offset,Integer pageSize);
	
	//查找新闻的所有评论
	public List<UserNewsCommentModel> findNewsComments(Long news_id);
	
	//批量删除评论
	public int batchDel(String[] delids);
	
	//查找分页数
	public int getPageCount(Integer pageSize);
	
	//查询用户评论信息的分页数
	public int getPageCountByUser(Integer pageSize,String userid);
		
	//查询新闻评论信息的分页数
	public int getPageCountByC(Integer pageSize,Long c_id);
	
	//查看用户是否已经评论该新闻
	public boolean isComment(String userName,Long newsId);
	
}
