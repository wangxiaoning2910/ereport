package com.ytincl.ereport.dao;

import java.util.ArrayList;

import com.ytincl.ereport.pojo.SystemBusiness;

public interface SystemBusinessDao {
	public ArrayList<SystemBusiness> querySystemBusiness();
	public void insertSystemBusiness(SystemBusiness systemBusiness);
	public int updateSystemBusiness(SystemBusiness systemBusiness);
	public void deleteSystemBusiness(String userName);
}
