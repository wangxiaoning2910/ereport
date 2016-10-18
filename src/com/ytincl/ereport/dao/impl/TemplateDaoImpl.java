package com.ytincl.ereport.dao.impl;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ytincl.ereport.dao.TemplateDao;
import com.ytincl.ereport.pojo.Template;
@Repository("TemplateDao")
public class TemplateDaoImpl implements TemplateDao {

	@Resource
	private SqlSession sqlSession;
	@Override
	public int deleteByPrimaryKey(String temp_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Template record) {
		int n = sqlSession.insert("com.ytincl.ereport.dao.TemplateDao.insert", record);
		return n;
	}

	@Override
	public int insertSelective(Template record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Template selectByPrimaryKey(String temp_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Template record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Template record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Template> queryTemplate() {
		ArrayList<Template> list = new ArrayList<Template>();
		list = (ArrayList)sqlSession.selectList("com.ytincl.ereport.dao.TemplateDao.queryTemplate");
		return list;
	}

	@Override
	public ArrayList<Template> queryTemplateF() {
		ArrayList<Template> list = new ArrayList<Template>();
		list = (ArrayList)sqlSession.selectList("com.ytincl.ereport.dao.TemplateDao.queryTemplateF");
		return list;
	}

	@Override
	public ArrayList<Template> queryTemplateC(String temp_id) {
		ArrayList<Template> list = new ArrayList<Template>();
		list = (ArrayList)sqlSession.selectList("com.ytincl.ereport.dao.TemplateDao.queryTemplateC",temp_id);
		return list;
	}

}
