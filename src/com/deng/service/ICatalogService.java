package com.deng.service;

import java.util.List;

import com.deng.bean.Catalog;

public interface ICatalogService {

	public void addCatalog(Catalog catalog);
	
	public void deleteCatalogById(Long id);
	
	public void updateCatalog(Catalog catalog);
	
	public Catalog findCatalogById(Long id);
	
	public List<Catalog> findAll();
	
}
