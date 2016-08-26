package com.ytincl.ereport.util.exception;
 
import java.util.HashMap;
import java.util.Map;

public class ResponseMessage {
	public final static Map<String, String> map = new HashMap<String, String>();  
	static {  
	    map.put("000001", "ϵͳ�����쳣");  
	    map.put("000002", "��ָ���쳣");  
	    map.put("000003", "����ת���쳣");  
	    map.put("000004", "I/O�쳣");  
	    map.put("000005", "δ֪�����쳣");  
	    map.put("000006", "����Խ���쳣");  
	    map.put("000007", "400����");  
	    map.put("000009", "405����");  
	    map.put("000010", "406����");  
	    map.put("000011", "500����");  
	    map.put("100000", "���׳ɹ�"); 
	    map.put("100001", "��¼������");  
	    map.put("100002", "��ȡ����ʧ��");  
	    map.put("100003", "��¼ʧ��,���û�������");  
	    map.put("100004", "��¼ʧ��,�������");  
	    map.put("000012", "���ݿ�����ʧ��");  
	} 
	public static String getResponseMessage(String rspCode){
		return map.get(String.valueOf(rspCode));
	}
}


