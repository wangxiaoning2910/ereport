package com.ytincl.ereport.dao.impl;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ytincl.ereport.dao.AccountRolesDao;
import com.ytincl.ereport.pojo.AccountRoles;
@Repository("AccountRolesDao")
public class AccountRolesDaoImpl implements AccountRolesDao {

	@Resource
	private SqlSession sqlSession;
	@Override
	public int deleteByPrimaryKey(AccountRoles key) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(AccountRoles record) {
		int i = sqlSession.insert("com.ytincl.ereport.dao.AccountRolesDao", record);
		return i;
	}

	@Override
	public int insertSelective(AccountRoles record) {
		
		return 0;
	}

}
