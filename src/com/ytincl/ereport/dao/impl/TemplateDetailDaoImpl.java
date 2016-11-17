package com.ytincl.ereport.dao.impl;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ytincl.ereport.dao.TemplateDetailDao;
import com.ytincl.ereport.pojo.TemplateDetail;
import com.ytincl.ereport.pojo.TemplateDetailKey;
@Repository("TemplateDetailDao")
public class TemplateDetailDaoImpl implements TemplateDetailDao{

	@Resource
	private SqlSession sqlSession;
	@Override
	public int deleteByPrimaryKey(TemplateDetailKey key) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(TemplateDetail record) {
		int n = sqlSession.insert("com.ytincl.ereport.dao.TemplateDetailDao.insert", record);
		return n;
	}

	@Override
	public int insertSelective(TemplateDetail record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<TemplateDetail> queryTemplateDetail(TemplateDetailKey key) {
		TemplateDetail templateDetail = new TemplateDetail();
		ArrayList<TemplateDetail> list = new ArrayList<TemplateDetail>();
		list = sqlSession.selectOne("com.ytincl.ereport.dao.TemplateDetailDao.queryTemplateDetail", key);
		return list;
	}


	@Override
	public int updateByPrimaryKey(TemplateDetail record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addTemplateFormula(TemplateDetail record) {
		sqlSession.update("com.ytincl.ereport.dao.TemplateDetailDao.addTemplateFormula", record);
		return 0;
	}

}
