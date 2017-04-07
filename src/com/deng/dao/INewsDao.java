package com.deng.dao;

import java.util.List;

import com.deng.bean.News;

public interface INewsDao {

	//保存新闻信息
	public void save(News news);

	//通过新闻ID删除新闻信息
	public void deleteById(Long id);

	//修改新闻信息
	public void update(News news);

	//通过新闻ID查询新闻信息
	public News queryById(Long id);
	
	//查询所有新闻信息
	public List<News> queryAll();

	//通过栏目查询新闻信息
	public List<News> queryCatalogNews(Long Catalog_id);
	
	//通过栏目查询新闻信息并且分页
	public List<News> queryByCatalogId(Long catalog_id,Integer offset,Integer pageSize);
	
	//通过栏目查询已发布的新闻信息并且分页
	public List<News> queryInuseByCatalogId(Long catalog_id,Integer offset,Integer pageSize);
	
	//批量删除新闻信息
	public int batchDel(String[] delids);
	
	//批量取消发布新闻信息
	public int batchCancel(String[] delids);
	
	//批量发布新闻信息
	public int batchPublish(String[] updids);
	
	//查询所有新闻信息的分页数
	public int getPageCount(Integer pageSize);
	
	//查询所在栏目下所有新闻信息的分页数
	public int getCatalogPageCount(Integer pageSize,Long catalog_id);
	
	//查询所在栏目下已发布新闻信息的分页数
	public int getInusePageCount(Integer pageSize,Long catalog_id);
	
	//查询所有新闻信息并且分页
	public List<News> findAllNews(Integer offset,Integer pageSize);

}
