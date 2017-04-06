package com.deng.service;

import java.util.List;

import com.deng.bean.Catalog;

public interface ICatalogService {

	//添加栏目信息
	public void addCatalog(Catalog catalog);
	
	//删除栏目信息
	public void deleteCatalogById(Long id);
	
	//修改栏目信息
	public void updateCatalog(Catalog catalog);
	
	//通过栏目ID查找栏目
	public Catalog findCatalogById(Long id);
	
	//查找所有栏目
	public List<Catalog> findAll();
	
	//查找所有栏目并且分页
	public List<Catalog> findAllCatalog(Integer offset,Integer pageSize);
	
	//查找所有已启用的栏目
	public List<Catalog> findAllInuse();
	
	//查找所有禁用的栏目
	public List<Catalog> findAllUserInuse();
	
	//批量删除栏目
	public int batchDel(String[] delids);
	
	//批量启用栏目
	public int batchInUse(String[] updids);
	
	//批量禁用栏目
	public int batchUnUse(String[] updids);
	
	//查找分页数
	public int getPageCount(Integer pageSize);
	
}
