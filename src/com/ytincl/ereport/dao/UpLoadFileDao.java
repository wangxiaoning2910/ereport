package com.ytincl.ereport.dao;

import java.util.List;

import com.ytincl.ereport.pojo.ResolveExcel;
import com.ytincl.ereport.pojo.SavingsDifferenceType;
import com.ytincl.ereport.pojo.SavingsNetAmount;
import com.ytincl.ereport.pojo.SavingsNetAmount2;
import com.ytincl.ereport.pojo.UpLoadFile;
import com.ytincl.ereport.pojo.pbsmr_busi;
import com.ytincl.ereport.pojo.pbsmr_inst;

public interface UpLoadFileDao {
	public List<UpLoadFile> getToBeUpLoaded(String querydate);
	public int insertExcelContent(ResolveExcel re); 
	public int updateStatus(UpLoadFile ulf);
	public int insertSavingsDifferenceType(SavingsDifferenceType sdt);
	public int insertSavingsNetAmount(SavingsNetAmount sna);
	public int insertSavingsNetAmount2(SavingsNetAmount2 sna2);
	public int insertdata(UpLoadFile ulf);
	public int insertNormData(pbsmr_busi pb);
	public int insertNormData(pbsmr_inst pi);
}
