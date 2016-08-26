package com.ytincl.ereport.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ytincl.ereport.controller.webappcontroller;
import com.ytincl.ereport.dao.UserInfoDao;
import com.ytincl.ereport.model.Menu;
import com.ytincl.ereport.model.UserInfo;
@Repository("userInfoDao")
public class UserInfoDaoImpl implements UserInfoDao {
	@Resource
	private SqlSession sqlSession;
	private static Logger logger = LoggerFactory.getLogger(UserInfoDaoImpl.class);
	@Override
	public List<UserInfo> getUserByName(String username) {
		// TODO Auto-generated method stub
		List<UserInfo> user = new ArrayList<>();
		user = (List)sqlSession.selectList("com.ytincl.ereport.dao.UserInfoDao.selectUserByName",username);
		return user;
	}
	@Override
	public List<Menu> getMenu() {
		// TODO Auto-generated method stub
		return (List)sqlSession.selectList("com.ytincl.ereport.dao.UserInfoDao.selectMenu");
	}

}
