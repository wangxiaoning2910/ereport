package com.ytincl.ereport.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ytincl.ereport.dao.AccountDao;
import com.ytincl.ereport.dao.AccountRolesDao;
import com.ytincl.ereport.pojo.AccountRoles;
import com.ytincl.ereport.service.AccountRolesService;
@Service("AccountRolesService")
public class AccountRolesServiceImpl implements AccountRolesService {

	@Autowired
	private AccountRolesDao accountRolesDao;
	@Override
	public int insert(AccountRoles accRole) {
		// TODO Auto-generated method stub
		return accountRolesDao.insert(accRole);
	}

}
