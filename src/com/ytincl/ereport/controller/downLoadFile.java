package com.ytincl.ereport.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;  
import org.springframework.http.MediaType;  
import org.springframework.http.ResponseEntity;  
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ytincl.ereport.model.ToBeDownLoadList;
import com.ytincl.ereport.model.testdownloaddatalist;
import com.ytincl.ereport.pojo.ToBeDownLoadFile;
import com.ytincl.ereport.pojo.testdownloaddata;
import com.ytincl.ereport.service.ToBeDownLoadListService;
import com.ytincl.ereport.service.UpLoadFileService;
import com.ytincl.ereport.util.ExportExcel; 

@Controller
public class downLoadFile {
	@Autowired
	private ToBeDownLoadListService getdownloadlist;
	private static Logger logger = LoggerFactory.getLogger(downLoadFile.class);
	
	
	
	@RequestMapping(value="/view/getToBeUpDownLoad.do")
	@ResponseBody
    public ToBeDownLoadList queryList(
    		@RequestParam(value="ymounth", required=true) String querydate,
    		HttpServletRequest request,HttpServletResponse response){  
		ArrayList<ToBeDownLoadFile> list;
		ToBeDownLoadList tbdll = new ToBeDownLoadList();
		list = getdownloadlist.getList(querydate);
		tbdll.setFilelist(list);
		return tbdll;
    } 
	/** 
	 * @Description ����Excel�ļ� 
	 * @author wxn 
	 * @date 2016��09��20�� 
	 * @param ymounth 
	 * @return ResponseEntity<byte[]> 
	 * @throws IOException */
	
	@RequestMapping(value="/view/exportfile.do")
	public ResponseEntity<byte[]> querydatalist(
			@RequestParam(value="ymounth", required=true) String querydate,
			@RequestParam(value="filename", required=true) String fn,
    		HttpServletRequest request,HttpServletResponse response) throws IOException{
		//1����ѯ���ݿ��е����ݣ�
		//2������Excel�ļ���������д��
		//3�����ļ��͵�ǰ̨����
		
		//�ļ���·��
		String filePath = request.getSession().getServletContext().getRealPath("/uploadFiles/");
		String str = "\\";
		String filename = fn+".xls";
		ArrayList<testdownloaddata> list;
		testdownloaddatalist testlist = new testdownloaddatalist();
		list = getdownloadlist.getDataList(querydate);
		testlist.setList(list);
		
		
		ExportExcel<testdownloaddata> ex = new ExportExcel<testdownloaddata>();
		String[] headers = { "����", "����1", "����2", "����3", "����4","����5","����6","����7" };
		try {
			OutputStream out = new FileOutputStream(filePath+str+filename);
			ex.exportExcel(headers, list, out);
			out.close();
			String path = filePath + str + filename;
			File file = new File(path);
			HttpHeaders httpheaders = new HttpHeaders();
			String filenamech=new String(filename.getBytes("UTF-8"),"iso-8859-1");//Ϊ�˽������������������  
			httpheaders.setContentDispositionFormData("attachment", filenamech);
			httpheaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),httpheaders,HttpStatus.CREATED);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}