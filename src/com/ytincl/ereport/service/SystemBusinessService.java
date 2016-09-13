package com.ytincl.ereport.service;

import java.util.List;

import com.ytincl.ereport.pojo.SystemBusiness;

public interface SystemBusinessService {
	public List<SystemBusiness> querySystemBusiness();
	public void insertSystemBusiness(SystemBusiness systemBusiness);
	public int updateSystemBusiness(SystemBusiness systemBusiness);
	public void deleteSystemBusiness(String userName);
}
