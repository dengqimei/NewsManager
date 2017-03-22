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
	public List<Code> findProvince() {
		return codeDao.queryProvince();
	}

	@Override
	public List<Code> findCity(String provinceId) {
		return codeDao.queryCity(provinceId);
	}

	@Override
	public List<Code> findCounty(String cityId) {
		return codeDao.queryCounty(cityId);
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


}
