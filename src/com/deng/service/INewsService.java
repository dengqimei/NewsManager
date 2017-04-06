package com.deng.service;

import java.util.List;

import com.deng.bean.News;
import com.deng.model.CatalogNewsModel;

public interface INewsService {
	
	public void addNews(News news);
	
	public void deleteNewsById(Long id);
	
	public void updateNews(News news);
	
	public News findNewsById(Long id);
	
	public List<News> findAll();
	
	public List<News> findNewsByCatalog(Long catalog_id,Integer offset,Integer pageSize);
	
	public List<CatalogNewsModel> findAllNews();
	
	public int batchDel(String[] delids);
	
	public int batchPublish(String[] updids);
	
	public int batchCancel(String[] updids);
	
	public int getPageCount(Integer pageSize);
	
	public int getCatalogPageCount(Integer pageSize,Long catalog_id);
	
	public List<News> findAllNews(Integer offset,Integer pageSize);
	
}
