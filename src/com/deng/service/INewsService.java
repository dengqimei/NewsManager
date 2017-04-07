package com.deng.service;

import java.util.List;

import com.deng.bean.News;
import com.deng.model.CatalogNewsModel;

public interface INewsService {
	
	//添加新闻信息
	public void addNews(News news);
	
	//通过ID删除新闻
	public void deleteNewsById(Long id);
	
	//修改新闻信息
	public void updateNews(News news);
	
	//通过ID查找新闻信息
	public News findNewsById(Long id);
	
	//查找所有新闻信息
	public List<News> findAll();
	
	//通过栏目查找新闻信息并且分页
	public List<News> findNewsByCatalog(Long catalog_id,Integer offset,Integer pageSize);
	
	public List<News> findInuseNewsByCatalog(Long catalog_id,Integer offset,Integer pageSize);
	
	//查找所有新闻信息
	public List<CatalogNewsModel> findAllNews();
	
	//批量删除新闻
	public int batchDel(String[] delids);
	
	//批量发布新闻
	public int batchPublish(String[] updids);
	
	//批量取消发布新闻
	public int batchCancel(String[] updids);
	
	//查找分页数
	public int getPageCount(Integer pageSize);
	
	//查找栏目下新闻分页数
	public int getCatalogPageCount(Integer pageSize,Long catalog_id);
	
	//查找所有新闻并且分页
	public List<News> findAllNews(Integer offset,Integer pageSize);
	
	//查询所在栏目下已发布新闻信息的分页数
	public int getInusePageCount(Integer pageSize,Long catalog_id);
	
}
