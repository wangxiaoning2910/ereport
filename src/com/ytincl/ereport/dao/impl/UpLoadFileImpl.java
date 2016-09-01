package com.ytincl.ereport.dao.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.ytincl.ereport.dao.UpLoadFileDao;
import com.ytincl.ereport.pojo.UpLoadFile;



@Repository("upLoadFileDao")
public class UpLoadFileImpl implements UpLoadFileDao{
	@Resource
	private SqlSession sqlSession;
	@Override
	public List<UpLoadFile> getToBeUpLoaded() {
		// TODO Auto-generated method stub
		List<UpLoadFile> list = new ArrayList<UpLoadFile>();
		list = (List)sqlSession.selectList("com.ytincl.ereport.dao.UpLoadFileDao.selectToBeUpLoaded");
		return list;
	}

}
