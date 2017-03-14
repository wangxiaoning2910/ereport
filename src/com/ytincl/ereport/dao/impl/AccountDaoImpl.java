package com.ytincl.ereport.dao.impl;


import java.util.ArrayList;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ytincl.ereport.dao.AccountDao;
import com.ytincl.ereport.pojo.Account;
@Repository("AccountDao")
public class AccountDaoImpl implements AccountDao {

	@Resource
	private SqlSession sqlSession;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Account record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(Account record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Account selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Account record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Account record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Account querySingleAccount(String accountName) {
		Account account = new Account();
		account = sqlSession.selectOne("com.ytincl.ereport.dao.AccountDao.querySingleAccount");
		return account;
	}

	@Override
	public Account getAccountByName(String accountName) {
		Account account = null;
		account =  sqlSession.selectOne("com.ytincl.ereport.dao.AccountDao.getAccountByName",accountName);
		return account;
	}

	@Override
	public ArrayList<Account> queryAllAccount() {
		ArrayList<Account> alist = new ArrayList<>();
		alist = (ArrayList)sqlSession.selectList("com.ytincl.ereport.dao.AccountDao.queryAllAccount");
		return alist;
	}

	@Override
	public void deleteByName(String accountName) {
		sqlSession.delete("com.ytincl.ereport.dao.AccountDao.deleteByName",accountName);
		
	}

	@Override
	public void accountAdd(Account account) {
		sqlSession.insert("com.ytincl.ereport.dao.AccountDao.accountAdd", account);
		
	}

	@Override
	public int updateByName(Account account) {
		return sqlSession.update("com.ytincl.ereport.dao.AccountDao.updateByName", account);
		
	}

}
