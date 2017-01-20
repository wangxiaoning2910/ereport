package com.ytincl.ereport.dao.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.ytincl.ereport.dao.UpLoadFileDao;
import com.ytincl.ereport.pojo.ResolveExcel;
import com.ytincl.ereport.pojo.SavingsDifferenceType;
import com.ytincl.ereport.pojo.SavingsNetAmount;
import com.ytincl.ereport.pojo.SavingsNetAmount2;
import com.ytincl.ereport.pojo.UpLoadFile;
import com.ytincl.ereport.pojo.pbsmr_busi;
import com.ytincl.ereport.pojo.pbsmr_inst;



@Repository("upLoadFileDao")
public class UpLoadFileImpl implements UpLoadFileDao{
	@Resource
	private SqlSession sqlSession;
	@Override
	public List<UpLoadFile> getToBeUpLoaded(String querydate) {
		// TODO Auto-generated method stub
		List<UpLoadFile> list = new ArrayList<UpLoadFile>();
		list = (List)sqlSession.selectList("com.ytincl.ereport.dao.UpLoadFileDao.selectToBeUpLoaded",querydate);
		return list;
	}
	@Override
	public int insertExcelContent(ResolveExcel re) {
		// TODO Auto-generated method stub
		return sqlSession.insert("com.ytincl.ereport.dao.UpLoadFileDao.test",re);
	}
	@Override
	public int updateStatus(UpLoadFile ulf) {
		// TODO Auto-generated method stub
		return sqlSession.update("com.ytincl.ereport.dao.UpLoadFileDao.updateStatus",ulf);
	}
	@Override
	public int insertSavingsDifferenceType(SavingsDifferenceType sdt) {
		// TODO Auto-generated method stub
		return sqlSession.insert("com.ytincl.ereport.dao.UpLoadFileDao.insertSavingsDifferenceType",sdt);
	}
	@Override
	public int insertSavingsNetAmount(SavingsNetAmount sna) {
		// TODO Auto-generated method stub
		return sqlSession.insert("com.ytincl.ereport.dao.UpLoadFileDao.insertSavingsNetAmount",sna);
	}
	@Override
	public int insertSavingsNetAmount2(SavingsNetAmount2 sna2) {
		// TODO Auto-generated method stub
		return sqlSession.insert("com.ytincl.ereport.dao.UpLoadFileDao.insertSavingsNetAmount2",sna2);
	}
	@Override
	public int insertdata(UpLoadFile ulf) {
		// TODO Auto-generated method stub
		return sqlSession.insert("com.ytincl.ereport.dao.UpLoadFileDao.insertdata",ulf);
	}
	@Override
	public int insertNormData(pbsmr_busi pb) {
		// TODO Auto-generated method stub
		return sqlSession.insert("com.ytincl.ereport.dao.UpLoadFileDao.insertNormData",pb);
	}
	@Override
	public int insertNormData(pbsmr_inst pi) {
		// TODO Auto-generated method stub
		return sqlSession.insert("com.ytincl.ereport.dao.UpLoadFileDao.insertNormData_inst",pi);
	}

}
