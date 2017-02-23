package com.ytincl.ereport.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ytincl.ereport.dao.UpLoadFileDao;
import com.ytincl.ereport.pojo.ResolveExcel;
import com.ytincl.ereport.pojo.SavingsDifferenceType;
import com.ytincl.ereport.pojo.SavingsNetAmount;
import com.ytincl.ereport.pojo.SavingsNetAmount2;
import com.ytincl.ereport.pojo.UpLoadFile;
import com.ytincl.ereport.pojo.pbsmr_busi;
import com.ytincl.ereport.pojo.pbsmr_entrustunit;
import com.ytincl.ereport.pojo.pbsmr_inst;
import com.ytincl.ereport.service.UpLoadFileService;


@Service("tobeuploaded")
public class UpLoadFileServiceImpl implements UpLoadFileService{
	@Autowired
	private UpLoadFileDao uploadfiledao;

	@Override
	public int inserExcelContent(ResolveExcel re) {
		// TODO Auto-generated method stub
		return uploadfiledao.insertExcelContent(re);
	}

	@Override
	public int updateStatus(UpLoadFile ulf) {
		// TODO Auto-generated method stub
		return uploadfiledao.updateStatus(ulf);
	}

	@Override
	public List<UpLoadFile> getToBeUpLoadedList(String querydate) {
		// TODO Auto-generated method stub
		return uploadfiledao.getToBeUpLoaded(querydate);
	}

	@Override
	public int insertSavingsDifferenceType(SavingsDifferenceType sdt) {
		// TODO Auto-generated method stub
		return uploadfiledao.insertSavingsDifferenceType(sdt);
	}

	@Override
	public int insertSavingsNetAmount(SavingsNetAmount sna) {
		// TODO Auto-generated method stub
		return uploadfiledao.insertSavingsNetAmount(sna);
	}

	@Override
	public int insertSavingsNetAmount2(SavingsNetAmount2 sna2) {
		// TODO Auto-generated method stub
		return uploadfiledao.insertSavingsNetAmount2(sna2);
	}

	@Override
	public int insertdata(UpLoadFile ulf) {
		// TODO Auto-generated method stub
		return uploadfiledao.insertdata(ulf);
	}

	@Override
	public int insertNormData(pbsmr_busi pb) {
		// TODO Auto-generated method stub
		return uploadfiledao.insertNormData(pb);
	}

	@Override
	public int insertNormData(pbsmr_inst pi) {
		// TODO Auto-generated method stub
		return uploadfiledao.insertNormData(pi);
	}
	
	@Override
	public int insertNormData(pbsmr_entrustunit pe) {
		// TODO Auto-generated method stub
		return uploadfiledao.insertNormData(pe);
	}
}


