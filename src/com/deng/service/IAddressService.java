package com.deng.service;

import java.util.List;

import com.deng.bean.Address;

public interface IAddressService {

	public List<Address> findProvince();
	
	public List<Address> findCity(String provinceId);
	
	public List<Address> findCounty(String cityId);
	
	public Address findAddressById();
	
}
