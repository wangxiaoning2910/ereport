package com.ytincl.ereport.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;







public class ExcelControll {
	
   /*
	@RequestMapping("/expExcle")
	public void downloadBatchInst(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		int excleType = 0;
		String excleTypeString = request.getParameter("excleType");
		String orgNo = request.getParameter("orgNo");
		UserInfo userinfo=(UserInfo)session.getAttribute(UserConst.UserInfo);
		String userId = userinfo.getUserId();
		String orgName = request.getParameter("orgName");
//		String orgName="";
//		try {
//			orgName = new String(request.getParameter("orgName").getBytes("ISO-8859-1"),"utf-8");
//		} catch (UnsupportedEncodingException e) {
//			logger.error("�������Ʊ���ת��ʧ��-->"+orgName,e);
//		}
		logger.info("׼�����ر���Ļ������ƣ�"+orgName);
		String fileName = "";
		String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
		if(!"null".equals(excleTypeString)){
			excleType = Integer.parseInt(excleTypeString);
			switch (excleType) {
			//����     ��ƽ̨�ͻ������Ų�������ܱ�
			case 1:
				List<RkDmKhCheck> dmKhChecks = rkDmKhCheckMapper.selectBySsyjfhId(orgNo);
				fileName = orgName + "_��ƽ̨�ͻ������Ų�������ܱ�_"+date+"_"+userId+".xls";
				dealProcess(request, response, fileName, dmKhChecks, excleTypeString);
				break;
			//����     ƽ̨�ͻ������Ų���Ϣ���ܱ�	
			case 2:
				List<RkDmPtkhCheck> rkDmPtkhChecks = rkDmPtkhCheckMapper.selectBySsyjfhId(orgNo);
				fileName = orgName + "_ƽ̨�ͻ������Ų���Ϣ���ܱ�_"+date+"_"+userId+".xls";
				dealProcess(request, response, fileName, rkDmPtkhChecks, excleTypeString);
				break;
			//����     ��ƽ̨ҵ������Ų�������ܱ�
			case 3:
				List<RkDmYwCheck> dmYwChecks = rkDmYwCheckMapper.selectByYwjbhId(orgNo);
				fileName = orgName + "_��ƽ̨ҵ������Ų�������ܱ�_"+date+"_"+userId+".xls";
				dealProcess(request, response, fileName, dmYwChecks, excleTypeString);
				break;	
			//����     ƽ̨ҵ������Ų�������ܱ�
			case 4:
				List<RkDmPtywCheck> dmPtywChecks = rkDmPtywCheckMapper.selectByYwjbhId(orgNo);
				fileName = orgName + "_ƽ̨ҵ������Ų�������ܱ�_"+date+"_"+userId+".xls";
				dealProcess(request, response, fileName, dmPtywChecks, excleTypeString);
				break;	
			//����     ���ſͻ������Ų鼯�Ż��ܱ�
			case 5:
//				List<RkDmJtItem> dmJtItems = rkDmJtCheckMapper.selectAllByJtglhId(orgNo);
				List<RkDmJtItemS> dmJtItems = rkDmJtCheckMapper.selectAllByJtglhId2(orgNo);
//				logger.info(dmJtItems.toString());
				fileName = orgName + "_���ſͻ������Ų鼯�Ż��ܱ�_"+date+"_"+userId+".xls";
				dealProcess(request, response, fileName, dmJtItems, excleTypeString);
				break;	
			default:
				logger.info("��ȷ��excel����ģ������---->"+excleTypeString);
				break;
			}
		}
	}
	

	@SuppressWarnings("unused")
	private <T> void  dealProcess(HttpServletRequest request, HttpServletResponse response,
			String fileName,Collection<T> dataset,String excelType){
		try {
			String filePath = ExcelUtil.exportReport(excelType, dataset,fileName);
			logger.info("����excel���ļ�·����"+filePath);
			if(DownloadFileUtil.downloadfile(request, response, filePath, fileName)){
				logger.info(filePath+"-->���سɹ�");
			}
			//������ɾ���ļ�
			File file = new File(filePath);
			logger.info(file.exists());
			if(file.exists()){
				file.delete();
				logger.info("���سɹ���"+filePath+"�ļ�ɾ���ɹ�");
			}
		} catch (IOException e) {
			logger.error("����excelʧ��", e);
		}
	}
	
	  DownloadFileUtil.java��������ʾ��,downloadfile   filepath���ļ�ȫ·��+�ļ�����fileName���ļ���������ʱ��������ɵ���������֧�����ģ�
	 public static boolean downloadfile(HttpServletRequest request,HttpServletResponse response, String filepath,String fileName) throws IOException{
		
	    BufferedInputStream bis = null;  
        BufferedOutputStream bos = null;  
	    try {
	    	 File file =new File(filepath);
		     FileInputStream stream=new FileInputStream(file);
			log.info("---stream:"+stream.toString());

	         long fileLength = file.length();
	         	response.reset();  
				response.setContentType("application/x-download;charset=UTF-8");
				response.setContentLength((int) fileLength);
				 boolean isFirefox = request.getHeader("USER-AGENT").toLowerCase().indexOf("firefox")>0;
				 if(isFirefox)
					 response.setHeader("Content-Disposition", "attachment;filename*=\"utf8''" + URLEncoder.encode(fileName,"UTF-8") + "\"");
				 else
					 response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(fileName,"UTF-8"));

				response.setHeader("Pragma", "public");
				response.setHeader("Cache-Control", "max-age=0");
		        bis = new BufferedInputStream(stream);  
		        bos = new BufferedOutputStream(response.getOutputStream());  
		        byte[] buff = new byte[4096*2];  
		        int bytesRead;  
		        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {  
		            bos.write(buff, 0, bytesRead);  
		        }  
		        bos.flush();
			} catch (IOException e) {
				e.printStackTrace();
				log.info("---�����ļ����쳣:"+e);
				return false;
			}finally{
				 if(bis!=null){
					 bis.close();  
				 }
				 if(bos!=null){
				     bos.close(); 
				 }
			}
	    	return true;
	 	}
	
	
	
	
	
	
	
	
	*/
	

	
	
	public void downloadBatchInst(String fileName,String targetPath,String targetFileName,List<String[]> dataList,String[]fprop,String[] fpropValue){
		dealProcess( fileName,targetPath, targetFileName, dataList,fprop,fpropValue);
		
	}
	private <T> void  dealProcess(String fileName,String targetPath,String targetFileName,List<String[]> dataList,String[]fprop,String[] fpropValue){
		try {	
			File file = new File(targetFileName);
			if(!file.exists()){
				file.mkdirs();
			}			
			targetFileName=ExcelUtil2.exportReport(fileName, targetPath, targetFileName,dataList,fprop,fpropValue);
			/*logger.info("����excel���ļ�·����"+filePath);
			if(DownloadFileUtil.downloadfile(request, response, targetFileName, downFileName)){
				logger.info(filePath+"-->���سɹ�");
			}
			*/
			//������ɾ���ļ�		
			System.out.println(file.exists());
			if(file.exists()){
				//file.delete();
				System.out.println("���سɹ���"+targetFileName+"�ļ�ɾ���ɹ�");
			}
		} catch (Exception e) {
			System.out.println("����excelʧ��:"+e);
		}
	}
	
	
}
