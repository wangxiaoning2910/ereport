package com.ytincl.ereport.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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

import com.ytincl.ereport.constant.CommonConstants;
import com.ytincl.ereport.model.ToBeDownLoadList;
import com.ytincl.ereport.model.testdownloaddatalist;
import com.ytincl.ereport.pojo.RowData;
import com.ytincl.ereport.pojo.ToBeDownLoadFile;
import com.ytincl.ereport.pojo.testdownloaddata;
import com.ytincl.ereport.service.ToBeDownLoadListService;
import com.ytincl.ereport.service.UpLoadFileService;
import com.ytincl.ereport.util.ExportExcel;
import com.ytincl.ereport.util.ExportExcel1; 

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
	 * @param ymounth - ����  ; filename - �ļ���
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
		String filePath = request.getSession().getServletContext().getRealPath(CommonConstants.UPLOADFILEPATH);
		//��б��
		String backslant = CommonConstants.BACKSLANT;
		//�ļ�����+��׺
		String filename = fn+CommonConstants.POINT+CommonConstants.OFFICE_EXCEL_2003_POSTFIX;
		//·��+�ļ���+��׺
		String fullPath = filePath + backslant +filename;
		//��ͷ
		String[] headers = {"ceshi1","ceshi2","ceshi3","ceshi4","ceshi5","ceshi6","ceshi7","ceshi8"};
		//��ѯ��ͷ
		
		//��ѯ����
		ArrayList<testdownloaddata> datalist = getdownloadlist.getDataList(querydate);
		
		ExportExcel<testdownloaddata> ex = new ExportExcel<testdownloaddata>();
		try {
			//����Outputstream
			OutputStream out = new FileOutputStream(fullPath);
			//����Excel�ļ�
			ex.exportExcel(headers, datalist, out);
			out.close();
			//��ȡ���ɵ��ļ�
			File file = new File(fullPath);
			HttpHeaders httpheaders = new HttpHeaders();
			String filenamech=new String(filename.getBytes("UTF-8"),"iso-8859-1");//Ϊ�˽������������������  
			httpheaders.setContentDispositionFormData("attachment", filenamech);
			httpheaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			//���ص�ǰ̨
			return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),httpheaders,HttpStatus.CREATED);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/** 
	 * @Description ����Excel�ļ� 
	 * @author wxn 
	 * @date 2016��09��20�� 
	 * @param ymounth - ����  ; filename - �ļ���
	 * @return ResponseEntity<byte[]> 
	 * @throws IOException */
	
	@RequestMapping(value="/view/exportfiles.do")
	public ResponseEntity<byte[]> exportfiles(
			@RequestParam(value="ymounth", required=true) String querydate,
			@RequestParam(value="filename", required=true) String fn,
    		HttpServletRequest request,HttpServletResponse response) throws IOException{
		//1����ѯ���ݿ��е����ݣ�
		//2������Excel�ļ���������д��
		//3�����ļ��͵�ǰ̨����
		String[] filenames = fn.split(",");
		int filenamenum = filenames.length;
		String[] downloadfiles = new String[filenamenum];
		//�ļ���·��
		String filePath = request.getSession().getServletContext().getRealPath(CommonConstants.UPLOADFILEPATH);
		//��б��
		String backslant = CommonConstants.BACKSLANT;
		String filename = "";
		String fullPath = "";
		//��ͷ
		String[] headers = {"ceshi1","ceshi2","ceshi3","ceshi4","ceshi5","ceshi6","ceshi7","ceshi8"};
		//��ѯ��ͷ
		
		//��ѯ����
		ArrayList<testdownloaddata> datalist = getdownloadlist.getDataList(querydate);
		
		for(int i = 0; i < filenamenum; i++){
			//�ļ���+��׺
			filename = filenames[i] + CommonConstants.POINT+CommonConstants.OFFICE_EXCEL_2003_POSTFIX;
			//·��+�ļ���+��׺
			fullPath = filePath + backslant +filename;
			//����Outputstream
			OutputStream out = new FileOutputStream(fullPath);
			//����Excel�ļ�
			ExportExcel<testdownloaddata> ex = new ExportExcel<testdownloaddata>();
			ex.exportExcel(headers, datalist, out);
			downloadfiles[i] = fullPath;
			out.close();
		}
		String zipName = querydate + CommonConstants.ZIP;
		String zipNamePath = filePath + backslant + zipName;
		OutputStream os = new BufferedOutputStream(new FileOutputStream(zipNamePath));
		ZipOutputStream zos = new ZipOutputStream( os );
		byte[] buf = new byte[8192];
        int len;
        for (int i = 0; i < filenamenum; i++) {  
            File file = new File(downloadfiles[i]);
            if ( !file.isFile() ) continue;
            ZipEntry ze = new ZipEntry( file.getName() );
            zos.putNextEntry( ze );
            BufferedInputStream bis = new BufferedInputStream( new FileInputStream( file ) );
            while ((len = bis.read(buf)) > 0){
                zos.write( buf, 0, len );
            }
            zos.closeEntry();
        }
        zos.closeEntry();
        zos.close();
        
		try {
			//��ȡ���ɵ��ļ�
			File file = new File(zipNamePath);
			HttpHeaders httpheaders = new HttpHeaders();
			String zipNamech=new String(zipName.getBytes("UTF-8"),"iso-8859-1");//Ϊ�˽������������������  
			httpheaders.setContentDispositionFormData("attachment", zipNamech);
			httpheaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			//���ص�ǰ̨
			return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),httpheaders,HttpStatus.CREATED);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}