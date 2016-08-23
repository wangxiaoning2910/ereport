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
	 * ���к�
	 */
	private static final long serialVersionUID = 4286978613584076282L;
	private static final String SENSITIVE_STR = "com.ytincl.ereport";
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseApplicationException.class);
	/** �쳣���� */
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
	 * ҵ���쳣-��ҵ���쳣����Throwable���쳣ʱ��ʹ�øù���
	 * 
	 * @param errCode
	 *            �쳣����
	 * @param  
	 * @param args
	 *            �Զ����쳣��Ϣ
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
	 * ϵͳ�쳣 -��Throwable���쳣��Ҫ����ʱ��ʹ�øù���
	 * 
	 * @param errCode
	 *            �쳣����
	 * @param e
	 *            �쳣����
	 * @param args
	 *            �����쳣��Ϣ
	 */
	public BaseApplicationException(String errCode, Throwable e, String... args) {
		super(getErrMsg(errCode, args),e);
		this.errCode=errCode;
		this.errorMsg=super.getMessage();
		this.detailErrorMsg=appendStr(render(e));
	}
	/***
	 * ϵͳ�쳣
	 * 
	 * @param errCode
	 *            �쳣����
	 * @param errMsg
	 *            �쳣��Ϣ
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
	 * ˽����ȡ�쳣��Ϣ������������errdict.properties�����ļ��ж�ȡ
	 * 
	 * @param errCode
	 * 			          �쳣����
	 * @param args
	 *			        �Զ����쳣��Ϣ
	 * @return String
	 */
	private static String getErrMsg(String errCode, String... args) {
		// ��δ��args���д�������չ
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
	  * �쳣��ϸ
	  * @param throwable
	  * @return
	  */
	 public static String[] render(Throwable throwable){
	  StringWriter sw = new StringWriter();
	  PrintWriter pw = new PrintWriter(sw);
	  try {
		  throwable.printStackTrace(pw);
	  } catch (RuntimeException ex) {
		  LOGGER.error("�쳣��ϸ��ȡ���д���(��Դ:BusinessException)",ex);
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
		  LOGGER.error("�쳣��Ϣ��ȡ����(��Դ:BusinessException)",ex);
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
