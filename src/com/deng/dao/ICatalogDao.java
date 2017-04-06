package com.deng.dao;

import java.util.List;

import com.deng.bean.Catalog;

public interface ICatalogDao {

	//保存栏目
	public void save(Catalog catalog);
	
	//通过栏目ID删除栏目
	public void deleteById(Long id);

	//修改栏目信息
	public void update(Catalog catalog);

	//通过栏目ID查询栏目信息
	public Catalog queryById(Long id);
	
	//查询所有栏目
	public List<Catalog> queryAll();

	//查询所有栏目并且分页
	public List<Catalog> queryAllCatalog(Integer offset,Integer pageSize);
	
	//查询所有已经启用的栏目
	public List<Catalog> queryAllInuse();
	
	//查询所有禁用的栏目
	public List<Catalog> queryAllUserInuse();
	
	//批量删除栏目
	public int batchDel(String[] delids);
	
	//批量启用栏目
	public int batchInUse(String[] updids);
	
	//批量禁用栏目
	public int batchUnUse(String[] updids);
	
	//获得栏目分页数
	public int getPageCount(Integer pageSize);

}
