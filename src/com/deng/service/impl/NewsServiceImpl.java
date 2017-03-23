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
		newsDao.save(news);
	}

	@Override
	public void deleteNewsById(Long id) {
		newsDao.deleteById(id);
	}

	@Override
	public void updateNews(News news) {
		String updateTime = DateUtil.getDate();
		news.setUpdateTime(updateTime);
		newsDao.update(news);
	}

	@Override
	public News findNewsById(Long id) {
		return newsDao.queryById(id);
	}

	@Override
	public List<News> findNewsByCatalog(Long catalog_id) {
		return newsDao.queryByCatalogId(catalog_id);
	}

	@Override
	public List<News> findAll() {
		return newsDao.queryAll();
	}

	@Override
	public List<CatalogNewsModel> findAllNews() {
		List<CatalogNewsModel> list = new ArrayList<CatalogNewsModel>();
		List<Catalog> catalogList = catalogDao.queryAll();
		for(Catalog catalog : catalogList){
			CatalogNewsModel model = new CatalogNewsModel();
			model.setCatalog(catalog);
			List<News> newsList = newsDao.queryByCatalogId(catalog.getId());
			model.setNews(newsList);
			list.add(model);
		}
		return list;
	}
	
}
