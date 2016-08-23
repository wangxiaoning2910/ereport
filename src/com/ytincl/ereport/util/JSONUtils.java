package com.ytincl.ereport.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

import com.ytincl.ereport.constant.CommonConstants;




public class JSONUtils {
	
	private static ObjectMapper om = null;
	static {
	    try { 
	    	om = new ObjectMapper();
	    } catch (Exception e) {
	    }
	}
	private JSONUtils() {}
	
	public static String set2json(Set<?> set) {
		try{
		  if(null!=set&&set.size()>0)
	      return new String(jsonFromObject(set, CommonConstants.DEFAULT_ENCODE), CommonConstants.DEFAULT_ENCODE);
	    } catch (Exception e) {
	    }
	    return CommonConstants.NULL_STRING;
	}
	
	
	public static String object2json(Object obj){
		try
	    {
		  if(null!=obj)
	      return new String(jsonFromObject(obj, CommonConstants.DEFAULT_ENCODE), CommonConstants.DEFAULT_ENCODE);
	    } catch (Exception e) {
	    }
	    return CommonConstants.NULL_STRING;
	}
	
	
	public static String bean2json(Object obj){
		try
	    {
		  if(null!=obj)
	      return new String(jsonFromObject(obj, CommonConstants.DEFAULT_ENCODE), CommonConstants.DEFAULT_ENCODE);
	    } catch (Exception e) {
	    }
	    return CommonConstants.NULL_STRING;
	}
	
	public static void obj2Writer(Object obj, HttpServletResponse response, String contentType){
		try {
			response.setContentType(contentType);
			Writer write = response.getWriter();
			if(null!=obj&&null!=write)
			jsonFromObject(obj, write);
		} catch (Exception e) {
		}
	  }
	
	
	public static Map<String, Object> MapFromJson(String jsonStr) {
	    Map obj = (Map)objectFromJson(jsonStr,HashMap.class);
	    return obj;
	}

	
	public static <T> T objectFromJson(String jsonStr, Class<T> classType) {
	    JsonParser localJsonParser = null;
	    T localObject1=null;
	    try {
	      localJsonParser = om.getJsonFactory().createJsonParser(jsonStr);
	      localObject1 = localJsonParser.readValueAs(classType);
	    }catch (RuntimeException e){
	      return null;
	    } catch (Exception e) {
	      return null;
	    } finally {
	      if (localJsonParser != null)
	        try {
	          localJsonParser.close();
	        }catch (IOException e){
	        }
	    }
	    return localObject1;
	}

	  
	  public static String jsonStrFromObject(Object obj, String charset){
	    try
	    {
	      if(null!=obj&&null!=charset&&!charset.trim().toLowerCase().equals(""))
	      return new String(jsonFromObject(obj, charset), charset);
	    } catch (UnsupportedEncodingException e) {
	    }
	    return CommonConstants.NULL_STRING;
	  }

	  
	  public static byte[] jsonFromObject(Object obj, String charset){
		byte [] byteArray=null;
	    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
	    JsonGenerator localJsonGenerator = null;
	    try {
	      localJsonGenerator = om.getJsonFactory().createJsonGenerator(new OutputStreamWriter(localByteArrayOutputStream,charset));
	      localJsonGenerator.writeObject(obj);
	      localJsonGenerator.flush();
	    } catch (RuntimeException e) {
	    } catch (Exception e) {
	    } finally {
	      if (null!=localJsonGenerator){
	        try {
	          localJsonGenerator.close();
	        } catch (IOException e) {
	        }
	      }
	      byteArray=localByteArrayOutputStream.toByteArray();
	      if(null!=localByteArrayOutputStream){
	    	  try {
	    		localByteArrayOutputStream.flush();
				localByteArrayOutputStream.close();
			  } catch (IOException e) {
			  }
	      }
	    }
	    return byteArray;
	  }

	  
	  public static byte[] jsonFromObject(Object obj, Writer write){
		byte [] byteArray=null;
	    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
	    JsonGenerator localJsonGenerator = null;
	    try {
	      localJsonGenerator = om.getJsonFactory().createJsonGenerator(write);
	      localJsonGenerator.writeObject(obj);
	      localJsonGenerator.flush();
	    } catch (RuntimeException e) {
	    	return null;
	    } catch (Exception e) {
	    	return null;
	    } finally {
	      if (localJsonGenerator != null){
	        try {
	          localJsonGenerator.close();
	        } catch (IOException e) {
	        }
	      }
	      byteArray=localByteArrayOutputStream.toByteArray();
	      if(null!=localByteArrayOutputStream){
	    	  try {
	    		localByteArrayOutputStream.flush();
				localByteArrayOutputStream.close();
			  } catch (IOException e) {
			  }
	      }
	    }
	    return byteArray;
	  }

}
