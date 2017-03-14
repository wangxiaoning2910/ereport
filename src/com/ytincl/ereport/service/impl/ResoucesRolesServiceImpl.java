package com.ytincl.ereport.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ytincl.ereport.dao.ResourcesRoleDao;
import com.ytincl.ereport.pojo.ResourcesRole;
import com.ytincl.ereport.service.ResourcesRolesService;
@Service("ResourcesRolesService")
public class ResoucesRolesServiceImpl implements ResourcesRolesService {

	@Autowired
	private ResourcesRoleDao resourcesRoleDao;
	@Override
	public int  insert(ResourcesRole resourcesRole) {
		// TODO Auto-generated method stub
		return resourcesRoleDao.insert(resourcesRole);
	}

}
