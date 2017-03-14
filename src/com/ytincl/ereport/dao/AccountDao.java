package com.ytincl.ereport.dao;

import java.util.ArrayList;

import com.ytincl.ereport.pojo.Account;

public interface AccountDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);
    
    public Account querySingleAccount(String accountName);
    
    public Account getAccountByName(String accountName);
    
    public ArrayList<Account> queryAllAccount();
    
    public void deleteByName(String accountName);

	void accountAdd(Account account);

	int updateByName(Account account);
}