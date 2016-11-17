package com.ytincl.ereport.service;

import java.util.List;
import com.ytincl.ereport.pojo.ResolveExcel;
import com.ytincl.ereport.pojo.SavingsDifferenceType;
import com.ytincl.ereport.pojo.SavingsNetAmount;
import com.ytincl.ereport.pojo.SavingsNetAmount2;
import com.ytincl.ereport.pojo.UpLoadFile;

public interface UpLoadFileService {
	public List<UpLoadFile> getToBeUpLoadedList(String querydate);
	public int inserExcelContent(ResolveExcel re);
	public int updateStatus(UpLoadFile ulf);
	public int insertSavingsDifferenceType(SavingsDifferenceType sdt);
	public int insertSavingsNetAmount(SavingsNetAmount sna);
	public int insertSavingsNetAmount2(SavingsNetAmount2 sna2);
	public int insertdata(UpLoadFile ulf);
}
