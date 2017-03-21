package com.deng.dao;

import java.util.List;

import com.deng.bean.News;

public interface INewsDao {

	public void save(News news);

	public void deleteById(Long id);

	public void update(News news);

	public News queryById(Long id);
	
	public List<News> queryAll();

	public List<News> queryByCatalogId(Long catalog_id);

}
