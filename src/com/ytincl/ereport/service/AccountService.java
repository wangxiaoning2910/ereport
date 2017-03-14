package com.ytincl.ereport.service;

import java.util.ArrayList;

import com.ytincl.ereport.pojo.Account;

public interface AccountService {

	public Account getAccountByName(String accountName);
	
	public ArrayList<Account> queryAllAccount();
	
	public void accountDelete(String accountName);

	public void accountAdd(Account account);

	public int updateByName(Account account);
}
