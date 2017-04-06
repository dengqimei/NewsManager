package com.deng.service;

import java.util.List;

import com.deng.bean.Code;

public interface ICodeService {

	//查找所有省份
	public List<Code> findAllProvince();
	
	//查找省份下的所有城市
	public List<Code> findCityByProvince(String provinceId);
	
	//查找城市下的所有县市
	public List<Code> findCountyByCity(String cityId);
	
	//通过ID查找用户地址
	public Code findAddressById(String id);
	
	//通过ID查找用户类型
	public Code findUserType(String id);
	
	//通过ID查找用户性别
	public Code findUserSex(String id);
	
	//通过省份ID查找省份
	public Code findProvinceById(String provinceId);
	
	//通过城市ID查找城市
	public Code findCityById(String cityId);
	
	//通过县市ID查找县市
	public Code findCountyById(String countyId);
	
}
