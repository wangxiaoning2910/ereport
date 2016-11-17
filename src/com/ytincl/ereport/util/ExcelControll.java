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
//			logger.error("机构名称编码转换失败-->"+orgName,e);
//		}
		logger.info("准备下载报表的机构名称："+orgName);
		String fileName = "";
		String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
		if(!"null".equals(excleTypeString)){
			excleType = Integer.parseInt(excleTypeString);
			switch (excleType) {
			//导出     非平台客户风险排查情况汇总表
			case 1:
				List<RkDmKhCheck> dmKhChecks = rkDmKhCheckMapper.selectBySsyjfhId(orgNo);
				fileName = orgName + "_非平台客户风险排查情况汇总表_"+date+"_"+userId+".xls";
				dealProcess(request, response, fileName, dmKhChecks, excleTypeString);
				break;
			//导出     平台客户风险排查信息汇总表	
			case 2:
				List<RkDmPtkhCheck> rkDmPtkhChecks = rkDmPtkhCheckMapper.selectBySsyjfhId(orgNo);
				fileName = orgName + "_平台客户风险排查信息汇总表_"+date+"_"+userId+".xls";
				dealProcess(request, response, fileName, rkDmPtkhChecks, excleTypeString);
				break;
			//导出     非平台业务风险排查情况汇总表
			case 3:
				List<RkDmYwCheck> dmYwChecks = rkDmYwCheckMapper.selectByYwjbhId(orgNo);
				fileName = orgName + "_非平台业务风险排查情况汇总表_"+date+"_"+userId+".xls";
				dealProcess(request, response, fileName, dmYwChecks, excleTypeString);
				break;	
			//导出     平台业务风险排查情况汇总表
			case 4:
				List<RkDmPtywCheck> dmPtywChecks = rkDmPtywCheckMapper.selectByYwjbhId(orgNo);
				fileName = orgName + "_平台业务风险排查情况汇总表_"+date+"_"+userId+".xls";
				dealProcess(request, response, fileName, dmPtywChecks, excleTypeString);
				break;	
			//导出     集团客户风险排查集团汇总表
			case 5:
//				List<RkDmJtItem> dmJtItems = rkDmJtCheckMapper.selectAllByJtglhId(orgNo);
				List<RkDmJtItemS> dmJtItems = rkDmJtCheckMapper.selectAllByJtglhId2(orgNo);
//				logger.info(dmJtItems.toString());
				fileName = orgName + "_集团客户风险排查集团汇总表_"+date+"_"+userId+".xls";
				dealProcess(request, response, fileName, dmJtItems, excleTypeString);
				break;	
			default:
				logger.info("请确定excel报表模板类型---->"+excleTypeString);
				break;
			}
		}
	}
	

	@SuppressWarnings("unused")
	private <T> void  dealProcess(HttpServletRequest request, HttpServletResponse response,
			String fileName,Collection<T> dataset,String excelType){
		try {
			String filePath = ExcelUtil.exportReport(excelType, dataset,fileName);
			logger.info("生成excel的文件路径："+filePath);
			if(DownloadFileUtil.downloadfile(request, response, filePath, fileName)){
				logger.info(filePath+"-->下载成功");
			}
			//下载完删除文件
			File file = new File(filePath);
			logger.info(file.exists());
			if(file.exists()){
				file.delete();
				logger.info("下载成功后，"+filePath+"文件删除成功");
			}
		} catch (IOException e) {
			logger.error("下载excel失败", e);
		}
	}
	
	  DownloadFileUtil.java方法下载示例,downloadfile   filepath：文件全路径+文件名，fileName仅文件名，下载时浏览器生成的中文名（支持中文）
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
				log.info("---下载文件的异常:"+e);
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
			/*logger.info("生成excel的文件路径："+filePath);
			if(DownloadFileUtil.downloadfile(request, response, targetFileName, downFileName)){
				logger.info(filePath+"-->下载成功");
			}
			*/
			//下载完删除文件		
			System.out.println(file.exists());
			if(file.exists()){
				//file.delete();
				System.out.println("下载成功后，"+targetFileName+"文件删除成功");
			}
		} catch (Exception e) {
			System.out.println("下载excel失败:"+e);
		}
	}
	
	
}
