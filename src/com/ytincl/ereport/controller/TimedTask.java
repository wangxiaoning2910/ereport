package com.ytincl.ereport.controller;


import java.sql.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ytincl.ereport.pojo.UpLoadFile;
import com.ytincl.ereport.service.UpLoadFileService;





@Component 
public class TimedTask {
	@Autowired
	private UpLoadFileService tobeuploaded;
	private static Logger logger = LoggerFactory.getLogger(TimedTask.class);
	@Scheduled(cron = "0 0 0 1 * ?")
	public void InsertData() {  
		logger.info("������ʱ���񣬲�����ϴ����б�����");
		//1�жϵ����Ƿ��Ѿ���������
		UpLoadFile upl;
		Date currentDate = new Date(System.currentTimeMillis());
		String currentDatestr = currentDate.toString();
		String querydate = currentDatestr.split("-")[0]+""+currentDatestr.split("-")[1];
		List<UpLoadFile> list = tobeuploaded.getToBeUpLoadedList(querydate);
		String[][] datas = {{"����ִ������","0",querydate},{"�������������--����","0",querydate},{"�������������--����","0",querydate}};
		if(list.isEmpty()){
			//׼����������
			for(int i = 0;i<datas.length;i++){
				upl = new UpLoadFile();
				upl.setName(datas[i][0]);
				upl.setStatus(datas[i][1]);
				upl.setQueryDate(datas[i][2]);
				tobeuploaded.insertdata(upl);
			}
			logger.info("����ִ�гɹ�");
		}else{
			logger.info("��������insert");
		}
	}  

}
