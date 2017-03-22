package com.deng.dao;

import java.util.List;

import com.deng.bean.Code;

public interface ICodeDao {
	
	public List<Code> queryProvince();
	
	public List<Code> queryCity(String provinceId);
	
	public List<Code> queryCounty(String cityId);
	
	public Code queryAddressById(String id);
	
	public Code queryUserType(String id);
	
	public Code queryUserSex(String id);

}
