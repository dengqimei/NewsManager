package com.deng.dao;

import java.util.List;

import com.deng.bean.Code;

public interface ICodeDao {
	
	public List<Code> queryAllProvince();
	
	public List<Code> queryCityByProvince(String provinceId);
	
	public List<Code> queryCountyByCity(String cityId);
	
	public Code queryProvince(String provinceId);
	
	public Code queryCity(String cityId);
	
	public Code queryCounty(String countyId);
	
	public Code queryAddressById(String id);
	
	public Code queryUserType(String id);
	
	public Code queryUserSex(String id);

}
