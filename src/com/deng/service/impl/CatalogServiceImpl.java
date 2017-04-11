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

	//添加栏目
	@Override
	public void addCatalog(Catalog catalog) {
		String inputTime = DateUtil.getDate();
		String updateTime = DateUtil.getDate();
		String id = IdUtil.getId();
		catalog.setId(Long .parseLong(id));
		catalog.setInputTime(inputTime);
		catalog.setUpdateTime(updateTime);
		catalog.setIsInuse("0");
		catalogDao.save(catalog);
	}

	//删除栏目
	@Override
	public void deleteCatalogById(Long id) {
		catalogDao.deleteById(id);
	}

	//修改栏目
	@Override
	public void updateCatalog(Catalog catalog) {
		String updateTime = DateUtil.getDate();
		catalog.setUpdateTime(updateTime);
		catalogDao.update(catalog);
	}

	//查找栏目
	@Override
	public Catalog findCatalogById(Long id) {
		return catalogDao.queryById(id);
	}

	//查找所有栏目、并且分页
	@Override
	public List<Catalog> findAllCatalog(Integer offset,Integer pageSize) {
		return catalogDao.queryAllCatalog(offset,pageSize);
	}
	
	@Override
	public List<Catalog> findAllInuse() {
		return catalogDao.queryAllInuse();
	}

	//批量删除栏目
	@Override
	public int batchDel(String[] delids) {
		if(delids.length==0){
			return -1;
		}else{
			return catalogDao.batchDel(delids);
		}
	}

	//批量启用栏目
	@Override
	public int batchInUse(String[] updids) {
		if(updids.length==0){
			return -1;
		}else{
			return catalogDao.batchInUse(updids);
		}
	}

	//批量禁用栏目
	@Override
	public int batchUnUse(String[] updids) {
		if(updids.length==0){
			return -1;
		}else{
			return catalogDao.batchUnUse(updids);
		}
	}

	@Override
	public List<Catalog> findAllUserInuse() {
		return catalogDao.queryAllUserInuse();
	}
	
	@Override
	public int getPageCount(Integer pageSize) {
		return catalogDao.getPageCount(pageSize);
	}

	@Override
	public List<Catalog> findAll() {
		return catalogDao.queryAll();
	}
	
}
