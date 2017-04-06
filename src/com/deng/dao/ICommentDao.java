package com.deng.dao;

import java.util.List;

import com.deng.bean.Comment;

public interface ICommentDao {

	//保存用户评论信息
	public void save(Comment comment);

	//通过ID删除用户评论信息
	public void deleteById(Long id);

	//修改评论信息
	public void update(Comment comment);

	//通过ID查询评论信息
	public Comment queryById(Long id);
	
	//查询所有评论信息并且分页
	public List<Comment> queryAll(Integer offset,Integer pageSize);

	//查询用户的评论信息
	public List<Comment> queryByUserId(String user_id);

	//查询新闻的评论信息
	public List<Comment> queryByNewsId(Long news_id);
	
	//批量删除评论信息
	public int batchDel(String[] delids);

	//查询评论信息的分页数
	public int getPageCount(Integer pageSize);
	
}
