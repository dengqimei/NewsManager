package com.deng.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.deng.bean.Code;
import com.deng.dao.ICodeDao;
import com.deng.service.ICodeService;

public class CodeServiceImpl implements ICodeService{

	@Resource
	private ICodeDao codeDao;
	
	@Override
	public List<Code> findAllProvince() {
		return codeDao.queryAllProvince();
	}

	@Override
	public List<Code> findCityByProvince(String provinceId) {
		return codeDao.queryCityByProvince(provinceId);
	}

	@Override
	public List<Code> findCountyByCity(String cityId) {
		return codeDao.queryCountyByCity(cityId);
	}

	@Override
	public Code findAddressById(String id) {
		return codeDao.queryAddressById(id);
	}

	@Override
	public Code findUserType(String id) {
		return codeDao.queryUserType(id);
	}

	@Override
	public Code findUserSex(String id) {
		return codeDao.queryUserSex(id);
	}

	@Override
	public Code findProvinceById(String provinceId) {
		return codeDao.queryProvince(provinceId);
	}

	@Override
	public Code findCityById(String cityId) {
		return codeDao.queryCity(cityId);
	}

	@Override
	public Code findCountyById(String countyId) {
		return codeDao.queryCounty(countyId);
	}


}
