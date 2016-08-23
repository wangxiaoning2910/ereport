package com.ytincl.ereport.util;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ytincl.ereport.constant.CommonConstants;


public class BaseApplicationException extends Exception implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 4286978613584076282L;
	private static final String SENSITIVE_STR = "com.ytincl.ereport";
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseApplicationException.class);
	/** 异常代码 */
	private  String errCode = null;

	private  String errorMsg="";
	
	private  String detailErrorMsg="";
	
	private String errorDetail="";

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getDetailErrorMsg() {
		return detailErrorMsg;
	}

	public void setDetailErrorMsg(String detailErrorMsg) {
		this.detailErrorMsg = detailErrorMsg;
	}

	public String getErrorDetail() {
		return errorDetail;
	}

	public void setErrorDetail(String errorDetail) {
		this.errorDetail = errorDetail;
	}
	/**
	 * 业务异常-纯业务异常，无Throwable类异常时，使用该构造
	 * 
	 * @param errCode
	 *            异常代码
	 * @param  
	 * @param args
	 *            自定义异常信息
	 */
	public BaseApplicationException(String errCode) {
		super(getErrMsg(errCode));
		this.errCode=errCode;
		this.errorMsg=super.getMessage();
		this.detailErrorMsg="";
	}
	public BaseApplicationException(String errCode,boolean getFromConfig,String... args) {
		super(getErrMsg(errCode,args));
		this.errCode=errCode;
		this.errorMsg=super.getMessage();
		this.detailErrorMsg="";
	}
	/***
	 * 系统异常 -有Throwable类异常需要处理时，使用该构造
	 * 
	 * @param errCode
	 *            异常代码
	 * @param e
	 *            异常对象
	 * @param args
	 *            定义异常信息
	 */
	public BaseApplicationException(String errCode, Throwable e, String... args) {
		super(getErrMsg(errCode, args),e);
		this.errCode=errCode;
		this.errorMsg=super.getMessage();
		this.detailErrorMsg=appendStr(render(e));
	}
	/***
	 * 系统异常
	 * 
	 * @param errCode
	 *            异常代码
	 * @param errMsg
	 *            异常信息
	 */
	public BaseApplicationException(String errCode, String errMsg) {
		super(errMsg);
		this.errCode = errCode;
		this.errorMsg=errMsg;
		this.detailErrorMsg="";
	}

	
	public BaseApplicationException(Object errCode, Object errMsg,Object errorDetail) {
		super(errMsg.toString());
		this.errCode = errCode.toString();
		this.errorMsg=errMsg.toString();
		this.detailErrorMsg="";
		this.errorDetail=errorDetail.toString();
	}
	/**
	 * 私有提取异常信息描述方法，从errdict.properties配置文件中读取
	 * 
	 * @param errCode
	 * 			          异常代码
	 * @param args
	 *			        自定义异常信息
	 * @return String
	 */
	private static String getErrMsg(String errCode, String... args) {
		// 暂未对args进行处理，可扩展
		String errMsgStr=DictHelper.getDictByName(CommonConstants.ERR_DICT_NAME, errCode);
		if(errMsgStr.indexOf("{0}")!=-1){
			if(null!=args&&args.length>0)
			for(int i=0;i<args.length;i++){
				String flagStr="{"+i+"}";
				errMsgStr=errMsgStr.replace(flagStr, args[i]);
			}
		}else{
			errMsgStr=errMsgStr+printArgs(args);
		}
		return errMsgStr;
	}
	private static String printArgs(String... args){
		String returnStr="";
		if(null!=args){
			String [] argsStr=args.clone();
			for(String str:argsStr){
				returnStr=returnStr+"<br>["+str+"]";
			}
		}
		return returnStr;
	}
	/**
	  * 异常明细
	  * @param throwable
	  * @return
	  */
	 public static String[] render(Throwable throwable){
	  StringWriter sw = new StringWriter();
	  PrintWriter pw = new PrintWriter(sw);
	  try {
		  throwable.printStackTrace(pw);
	  } catch (RuntimeException ex) {
		  LOGGER.error("异常明细读取运行错误(来源:BusinessException)",ex);
	  }
	  pw.flush();
	  LineNumberReader reader = new LineNumberReader(new StringReader(sw.toString()));
	  List lines = new ArrayList();
	  try {
		  String line = reader.readLine();
		  while (line != null) {
			  lines.add(line);
			  line = reader.readLine();
		  }
	  } catch (IOException ex) {
		  /*if ((ex instanceof InterruptedIOException)) {
			  Thread.currentThread().interrupt();
		  }
		  lines.add(ex.toString());*/
		  LOGGER.error("异常消息读取错误(来源:BusinessException)",ex);
	  }
	  String[] tempRep = new String[lines.size()];
	  lines.toArray(tempRep);
	  return tempRep;
	 }
	 /**
	 * 
	 * @param strArray
	 * @return
	 */
	public static String appendStr(String[] strArray){
		StringBuilder str=new StringBuilder("");
		if(null!=strArray){
			for(int i=0;i<strArray.length;i++){
					String errStr=strArray[i];
					if(errStr.indexOf(SENSITIVE_STR)!=-1){
						errStr="<font color=red style=bold>"+errStr+"</font>";
					}
					str.append(errStr);
					if(i==strArray.length-1){
					}else{
						str.append("<br>");
					}
			}
		}
		return str.toString();
	}
}
