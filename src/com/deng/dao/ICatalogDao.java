package com.deng.dao;

import java.util.List;

import com.deng.bean.Catalog;

public interface ICatalogDao {

	public void save(Catalog catalog);

	public void deleteById(Long id);

	public void update(Catalog catalog);

	public Catalog queryById(Long id);

	public List<Catalog> queryAll();

}
