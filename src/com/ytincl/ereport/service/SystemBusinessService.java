package com.ytincl.ereport.service;

import java.util.ArrayList;

import com.ytincl.ereport.pojo.SystemBusiness;

public interface SystemBusinessService {
	public ArrayList<SystemBusiness> querySystemBusiness();
	public void insertSystemBusiness(SystemBusiness systemBusiness);
	public int updateSystemBusiness(SystemBusiness systemBusiness);
	public void deleteSystemBusiness(String userName);
}
