package com.deng.dao;

import java.util.List;

import com.deng.bean.Comment;

public interface ICommentDao {

	public void save(Comment comment);

	public void deleteById(Long id);

	public void update(Comment comment);

	public Comment queryById(Long id);

	public List<Comment> queryByUserId(Long user_id);

	public List<Comment> queryByNewsId(Long news_id);

}
