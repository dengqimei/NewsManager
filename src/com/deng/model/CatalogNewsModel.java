package com.deng.model;

import java.util.List;

import com.deng.bean.Catalog;
import com.deng.bean.News;

public class CatalogNewsModel {

	private Catalog catalog;//栏目
	private List<News> news;//栏目下的所有新闻
	
	public Catalog getCatalog() {
		return catalog;
	}
	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}
	public List<News> getNews() {
		return news;
	}
	public void setNews(List<News> news) {
		this.news = news;
	}
	
}
