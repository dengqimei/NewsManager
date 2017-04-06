package com.deng.dao;

import java.util.List;

import com.deng.bean.Code;

public interface ICodeDao {
	
	//查询所有省份
	public List<Code> queryAllProvince();
	
	//查询省份下的所有城市
	public List<Code> queryCityByProvince(String provinceId);
	
	//查询城市下的所有县市
	public List<Code> queryCountyByCity(String cityId);
	
	//通过省份ID查询省份
	public Code queryProvince(String provinceId);
	
	//通过城市ID查询城市
	public Code queryCity(String cityId);
	
	//通过县市ID查询县市
	public Code queryCounty(String countyId);
	
	//通过ID查询地址
	public Code queryAddressById(String id);
	
	//查询用户类型
	public Code queryUserType(String id);
	
	//查询用户性别
	public Code queryUserSex(String id);

}
