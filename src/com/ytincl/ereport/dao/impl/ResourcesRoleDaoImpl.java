package com.ytincl.ereport.dao.impl;


import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ytincl.ereport.dao.ResourcesRoleDao;
import com.ytincl.ereport.pojo.ResourcesRole;
@Repository("ResourcesRoleDao")
public class ResourcesRoleDaoImpl implements ResourcesRoleDao {

	@Resource
	private SqlSession sqlSession;
	@Override
	public int deleteByPrimaryKey(ResourcesRole key) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(ResourcesRole record) {
		// TODO Auto-generated method stub
		return sqlSession.insert("com.ytincl.ereport.dao.ResourcesRoleDao.insert", record);
	}

	@Override
	public int insertSelective(ResourcesRole record) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
