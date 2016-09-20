package com.ytincl.ereport.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Driver;
import com.ytincl.ereport.dao.FindFileDao;
import com.ytincl.ereport.pojo.FindFile;

@Repository("findFileDao")
public class FindFileDaoImpl  implements  FindFileDao{
	@Resource
	private SqlSession sqlSession;
	@Override
	public List[] getFindFileByName(FindFile obj) {
		// TODO Auto-generated method stub
		List<FindFile> data[] = new ArrayList[2];
		data[0] = sqlSession.selectList("com.ytincl.ereport.dao.FindFileDao.selectFindFileByName", obj); //全口径数据
		data[1] = sqlSession.selectList("com.ytincl.ereport.dao.FindFileDao.selectFindFileZYByName", obj);  //银行自营数据
		return data;
	}

}
