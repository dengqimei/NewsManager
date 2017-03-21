package com.deng.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.deng.bean.Address;
import com.deng.dao.IAddressDao;
import com.deng.service.IAddressService;

public class AddressServiceImpl implements IAddressService{

	@Resource
	private IAddressDao addressDao;
	
	@Override
	public List<Address> findProvince() {
		return addressDao.queryProvince();
	}

	@Override
	public List<Address> findCity(String provinceId) {
		return addressDao.queryCity(provinceId);
	}

	@Override
	public List<Address> findCounty(String cityId) {
		return addressDao.queryCounty(cityId);
	}

	@Override
	public Address findAddressById() {
		return addressDao.queryById();
	}

}
