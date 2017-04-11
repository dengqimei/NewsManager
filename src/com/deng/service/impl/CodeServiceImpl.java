package com.deng.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.deng.bean.Code;
import com.deng.dao.ICodeDao;
import com.deng.service.ICodeService;

public class CodeServiceImpl implements ICodeService{

	@Resource
	private ICodeDao codeDao;

	public void setCodeDao(ICodeDao codeDao) {
		this.codeDao = codeDao;
	}

	//查询所有省份
	@Override
	public List<Code> findAllProvince() {
		return codeDao.queryAllProvince();
	}

	//查询省份下所有城市
	@Override
	public List<Code> findCityByProvince(String provinceId) {
		return codeDao.queryCityByProvince(provinceId);
	}

	//查询所有城市下的所有县市
	@Override
	public List<Code> findCountyByCity(String cityId) {
		return codeDao.queryCountyByCity(cityId);
	}

	//查询用户地址
	@Override
	public Code findAddressById(String id) {
		return codeDao.queryAddressById(id);
	}

	//查询用户类型
	@Override
	public Code findUserType(String id) {
		return codeDao.queryUserType(id);
	}

	//查询用户性别
	@Override
	public Code findUserSex(String id) {
		return codeDao.queryUserSex(id);
	}

	//查询指定的省份名
	@Override
	public Code findProvinceById(String provinceId) {
		return codeDao.queryProvince(provinceId);
	}

	//查询指定的城市名
	@Override
	public Code findCityById(String cityId) {
		return codeDao.queryCity(cityId);
	}

	//查询指定的县市名
	@Override
	public Code findCountyById(String countyId) {
		return codeDao.queryCounty(countyId);
	}


}
