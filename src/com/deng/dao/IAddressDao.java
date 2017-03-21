package com.deng.dao;

import java.util.List;

import com.deng.bean.Address;

public interface IAddressDao {
	
	public List<Address> queryProvince();
	
	public List<Address> queryCity(String provinceId);
	
	public List<Address> queryCounty(String cityId);
	
	public Address queryById();

}
