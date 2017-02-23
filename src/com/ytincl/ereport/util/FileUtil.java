package com.ytincl.ereport.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class FileUtil {
	
	/**
	 * �����ļ��ϴ�
	 * @param is
	 * @param fileName
	 * @param filePath
	 */
	public static void upFile(InputStream is,String fileName,String filePath){

		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		BufferedInputStream bis = null;
		File file = new File(filePath);
		if(!file.exists()){
			file.mkdirs();
		}
		File f = new File(filePath+"/"+fileName);

		try {
			bis = new BufferedInputStream(is);
			fos = new FileOutputStream(f);
			bos = new BufferedOutputStream(fos);
			byte[] bt = new byte[4096];
			int len;

			while((len = bis.read(bt))>0){
				bos.write(bt, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(null != bos){
					bos.close();
					bos = null;
				}
				if(null != fos){
					fos.close();
					fos= null;
				}

				if(null != is){
					is.close();
					is=null;
				}
				if (null != bis) {
					bis.close();
					bis = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public FileOutputStream copyFile(String oldPath, String newPath) { 
		FileOutputStream fs = null;
		try { 
			int bytesum = 0; 
			int byteread = 0; 
			File oldfile = new File(oldPath); 
			if (oldfile.exists()) { //�ļ�����ʱ 
				InputStream inStream = new FileInputStream(oldPath); //����ԭ�ļ� 
				fs = new FileOutputStream(newPath); //���ļ�
				byte[] buffer = new byte[1444]; 
				int length; 
				while ( (byteread = inStream.read(buffer)) != -1) { 
					bytesum += byteread; //�ֽ��� �ļ���С 
					fs.write(buffer, 0, byteread); 
				} 
				inStream.close(); 
			}else{
			}
		}catch (Exception e) { 
			e.printStackTrace(); 
		}
		return fs;
	}
}
