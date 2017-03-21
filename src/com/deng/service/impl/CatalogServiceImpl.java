package com.deng.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.deng.bean.Catalog;
import com.deng.dao.ICatalogDao;
import com.deng.service.ICatalogService;
import com.deng.util.DateUtil;
import com.deng.util.IdUtil;

public class CatalogServiceImpl implements ICatalogService{

	@Resource
	private ICatalogDao catalogDao;
	public void setCatalogDao(ICatalogDao catalogDao) {
		this.catalogDao = catalogDao;
	}

	@Override
	public void addCatalog(Catalog catalog) {
		String inputTime = DateUtil.getDate();
		String updateTime = DateUtil.getDate();
		String id = IdUtil.getId();
		catalog.setId(Long .parseLong(id));
		catalog.setInputTime(inputTime);
		catalog.setUpdateTime(updateTime);
		catalogDao.save(catalog);
	}

	@Override
	public void deleteCatalogById(Long id) {
		catalogDao.deleteById(id);
	}

	@Override
	public void updateCatalog(Catalog catalog) {
		String updateTime = DateUtil.getDate();
		catalog.setUpdateTime(updateTime);
		catalogDao.update(catalog);
	}

	@Override
	public Catalog findCatalogById(Long id) {
		return catalogDao.queryById(id);
	}

	@Override
	public List<Catalog> findAll() {
		return catalogDao.queryAll();
	}
	
}
