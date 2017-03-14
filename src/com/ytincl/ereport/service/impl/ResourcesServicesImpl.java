package com.ytincl.ereport.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ytincl.ereport.dao.ResourcesDao;
import com.ytincl.ereport.pojo.Resources;
import com.ytincl.ereport.pojo.Role;
import com.ytincl.ereport.service.ResourcesService;
@Service("ResourcesService")
public class ResourcesServicesImpl implements ResourcesService{

	@Autowired
	private ResourcesDao resourcesDao;
	@Override
	public List<Resources> getAllResources() {
		
		return resourcesDao.getAllResources();
	}
	@Override
	public List<Resources> findAccountResourcess(String accountId) {
		// TODO Auto-generated method stub
		return resourcesDao.findAccountResourcess(accountId);
	}
	@Override
	public ArrayList<Resources> queryResourcesList() {
		// TODO Auto-generated method stub
		return (ArrayList<Resources>) resourcesDao.getAllResources();
	}
	@Override
	public Resources getResourcesByMid(String mid) {
		// TODO Auto-generated method stub
		return resourcesDao.getResourcesByMid(mid);
	}

}
