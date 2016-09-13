package com.ytincl.ereport.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ytincl.ereport.dao.SystemBusinessDao;
import com.ytincl.ereport.pojo.SystemBusiness;
import com.ytincl.ereport.service.SystemBusinessService;

@Service("systemBusinessService")
public class SystemBusinessServiceImpl implements SystemBusinessService {
	@Autowired
	private SystemBusinessDao systemBusinessDao;
	//===================================²éÑ¯
	@Override
	public List<SystemBusiness> querySystemBusiness() {
		// TODO Auto-generated method stub
		return systemBusinessDao.querySystemBusiness();
	}
	//===================================ÐÂÔö
	@Override
	public void insertSystemBusiness(SystemBusiness systemBusiness) {
		// TODO Auto-generated method stub
		systemBusinessDao.insertSystemBusiness(systemBusiness);
	}
	@Override
	public int updateSystemBusiness(SystemBusiness systemBusiness) {
		return systemBusinessDao.updateSystemBusiness(systemBusiness);
	}
	@Override
	public void deleteSystemBusiness(String userName) {
		systemBusinessDao.deleteSystemBusiness(userName);
		
	}
}
