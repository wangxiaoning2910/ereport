package com.ytincl.ereport.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.ytincl.ereport.model.ToBeUploaded;
import com.ytincl.ereport.pojo.UpLoadFile;
import com.ytincl.ereport.service.UpLoadFileService;
import com.ytincl.ereport.util.FileUtil;

@Controller
public class upLoadFile {
	@Autowired
	private UpLoadFileService tobeuploaded;
	private static Logger logger = LoggerFactory.getLogger(upLoadFile.class);
	//�����ϴ��ļ�

	@RequestMapping(value="/view/uploadFile.do")
	@ResponseBody
	public String uploadFile(@RequestParam("pic")CommonsMultipartFile pic,HttpServletRequest req,
			HttpServletResponse response) throws IOException{
		logger.debug("===========================UpLoadFile=================");
		//�����ļ�����ı���·��
		String filePath = req.getSession().getServletContext().getRealPath("/uploadFiles/");
		logger.debug("filePath================="+filePath);
		String fileName = pic.getOriginalFilename();
		logger.debug("fileName====="+fileName);
		String fileType = fileName.split("[.]")[1];
		logger.debug("fileType====="+fileType);
		//Ϊ�˱����ļ����ظ������ļ���ǰ��UUID
		String uuid = UUID.randomUUID().toString().replace("-","");
		String uuidFileName = uuid + fileName;
		//File f = new File(filePath+"/"+uuid+"."+fileType);
	
		//���ļ����浽������
		FileUtil.upFile(pic.getInputStream(), uuidFileName, filePath);
		return uuidFileName;

	}
	@RequestMapping(value="/view/test.do")
	@ResponseBody
	public ToBeUploaded test(){
		ToBeUploaded tbu = new ToBeUploaded();
		ArrayList<UpLoadFile> list = new ArrayList<UpLoadFile>();
		logger.debug("===========================test.do=================");
		UpLoadFile up = new UpLoadFile();
		up.setName("ceshi1");
		up.setStatus("123321");
		UpLoadFile up1 = new UpLoadFile();
		up1.setName("ceshi2");
		up1.setStatus("123456");
		list.add(up);
		list.add(up1);
		tbu.setList(list);
		return tbu;

	}
	@RequestMapping(value="/view/getToBeUpLoaded.do")
	@ResponseBody
	public ToBeUploaded getToBeUpLoaded(){
		ToBeUploaded tbu = new ToBeUploaded();
		ArrayList<UpLoadFile> list;
		list = (ArrayList<UpLoadFile>) tobeuploaded.getToBeUpLoadedList();
		tbu.setList(list);
		return tbu;
	}
}
