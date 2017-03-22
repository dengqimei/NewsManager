package com.deng.service;

import java.util.List;

import com.deng.bean.Code;

public interface ICodeService {

	public List<Code> findProvince();
	
	public List<Code> findCity(String provinceId);
	
	public List<Code> findCounty(String cityId);
	
	public Code findAddressById(String id);
	
	public Code findUserType(String id);
	
	public Code findUserSex(String id);
	
}
