package com.deng.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;


import com.deng.bean.Catalog;
import com.deng.bean.News;
import com.deng.dao.ICatalogDao;
import com.deng.dao.INewsDao;
import com.deng.model.CatalogNewsModel;
import com.deng.service.INewsService;
import com.deng.util.DateUtil;
import com.deng.util.IdUtil;

public class NewsServiceImpl implements INewsService{

	@Resource
	private INewsDao newsDao;
	@Resource
	private ICatalogDao catalogDao;
	
	public void setNewsDao(INewsDao newsDao){
		this.newsDao = newsDao;
	}

	public void setCatalogDao(ICatalogDao catalogDao) {
		this.catalogDao = catalogDao;
	}

	//添加新闻
	@Override
	public void addNews(News news) {
		String publishTime = DateUtil.getDate();
		String updateTime = DateUtil.getDate();
		String id = IdUtil.getId();
		news.setId(Long.parseLong(id));
		news.setPublishTime(publishTime);
		news.setPublishDate(publishTime.split(" ")[0]);
		news.setUpdateTime(updateTime);
		news.setClickTimes(0);
		news.setIsPublish("0");
		newsDao.save(news);
	}

	//删除新闻
	@Override
	public void deleteNewsById(Long id) {
		newsDao.deleteById(id);
	}

	//修改新闻信息
	@Override
	public void updateNews(News news) {
		String updateTime = DateUtil.getDate();
		news.setUpdateTime(updateTime);
		newsDao.update(news);
	}

	//通过id查找新闻
	@Override
	public News findNewsById(Long id) {
		return newsDao.queryById(id);
	}

	//通过栏目查找新闻、并且分页
	@Override
	public List<News> findNewsByCatalog(Long catalog_id,Integer offset,Integer pageSize) {
		return newsDao.queryByCatalogId(catalog_id,offset,pageSize);
	}
	
	
	
	//查找已启用栏目的新闻并且分页
	@Override
	public List<News> findInuseNewsByCatalog(Long catalog_id,Integer offset,Integer pageSize) {
		return newsDao.queryInuseByCatalogId(catalog_id, offset, pageSize);
	}

	//查找所有新闻
	@Override
	public List<News> findAll() {
		return newsDao.queryAll();
	}

	//查找所有新闻
	@Override
	public List<CatalogNewsModel> findAllNews() {
		List<CatalogNewsModel> list = new ArrayList<CatalogNewsModel>();
		List<Catalog> catalogList = catalogDao.queryAllInuse();
		for(Catalog catalog : catalogList){
			CatalogNewsModel model = new CatalogNewsModel();
			model.setCatalog(catalog);
			List<News> newsList = newsDao.queryCatalogNews(catalog.getId());
			model.setNews(newsList);
			list.add(model);
		}
		return list;
	}

	//批量删除新闻
	@Override
	public int batchDel(String[] delids) {
		if(delids.length==0){
			return -1;
		}else{
			return newsDao.batchDel(delids);
		}
	}

	//批量发布新闻
	@Override
	public int batchPublish(String[] updids) {
		if(updids.length==0){
			return -1;
		}else{
			return newsDao.batchPublish(updids);
		}
	}
	
	//批量取消发布新闻
	@Override
	public int batchCancel(String[] updids) {
		if(updids.length==0){
			return -1;
		}else{
			return newsDao.batchCancel(updids);
		}
	}

	//查找分页数
	@Override
	public int getPageCount(Integer pageSize) {
		return newsDao.getPageCount(pageSize);
	}

	//查找所有新闻的分页数
	@Override
	public List<News> findAllNews(Integer offset, Integer pageSize) {
		return newsDao.findAllNews(offset, pageSize);
	}

	//栏目中所有新闻的分页数
	@Override
	public int getCatalogPageCount(Integer pageSize,Long catalog_id) {
		return newsDao.getCatalogPageCount(pageSize, catalog_id);
	}

	//已启用栏目所有新闻的分页数
	@Override
	public int getInusePageCount(Integer pageSize, Long catalog_id) {
		return newsDao.getInusePageCount(pageSize, catalog_id);
	}
	
}
