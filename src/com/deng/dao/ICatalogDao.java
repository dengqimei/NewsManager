package com.deng.dao;

import java.util.List;

import com.deng.bean.Catalog;

public interface ICatalogDao {

	public void save(Catalog catalog);

	public void deleteById(Long id);

	public void update(Catalog catalog);

	public Catalog queryById(Long id);
	
	public List<Catalog> queryAll();

	public List<Catalog> queryAllCatalog(Integer offset,Integer pageSize);
	
	public List<Catalog> queryAllInuse();
	
	public List<Catalog> queryAllUserInuse();
	
	public int batchDel(String[] delids);
	
	public int batchInUse(String[] updids);
	
	public int batchUnUse(String[] updids);
	
	public int getPageCount(Integer pageSize);

}
