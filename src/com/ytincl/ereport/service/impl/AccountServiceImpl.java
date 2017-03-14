package com.ytincl.ereport.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ytincl.ereport.dao.AccountDao;
import com.ytincl.ereport.pojo.Account;
import com.ytincl.ereport.service.AccountService;
@Service("AccountService")
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountDao accountDao;

	@Override
	public Account getAccountByName(String accountName) {
		// TODO Auto-generated method stub
		return accountDao.getAccountByName(accountName);
	}

	@Override
	public ArrayList<Account> queryAllAccount() {
		// TODO Auto-generated method stub
		return accountDao.queryAllAccount();
	}

	@Override
	public void accountDelete(String accountName) {
		accountDao.deleteByName(accountName);
		
	}

	@Override
	public void accountAdd(Account account) {
		accountDao.accountAdd(account);
		
	}

	@Override
	public int updateByName(Account account) {
		int i = accountDao.updateByName(account);
		return i;
	}

	

}
