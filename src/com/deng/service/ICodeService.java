package com.deng.service;

import java.util.List;

import com.deng.bean.Code;

public interface ICodeService {

	public List<Code> findAllProvince();
	
	public List<Code> findCityByProvince(String provinceId);
	
	public List<Code> findCountyByCity(String cityId);
	
	public Code findAddressById(String id);
	
	public Code findUserType(String id);
	
	public Code findUserSex(String id);
	
	public Code findProvinceById(String provinceId);
	
	public Code findCityById(String cityId);
	
	public Code findCountyById(String countyId);
	
}
