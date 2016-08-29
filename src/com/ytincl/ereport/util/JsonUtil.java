package com.ytincl.ereport.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	private static ObjectMapper mapper = new ObjectMapper();
	//向输出流中写入json字符串
	public void writeEntityJSON(OutputStream out,Object obj) {
		try {
			mapper = new ObjectMapper();
			mapper.writeValue(out, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//将对象转换为json字符串
	public static String convertJSON(Object obj) {
		try {
			return reviseJSON(mapper.writeValueAsString(obj));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	//将json转换为object
	public static <T> T jsonToObj(String json,Class<T> clazz) {
		try {
			return  mapper.readValue(json, clazz);
		} catch (IOException e) {
			return null;
		}
	}
	//对json字符串进行拼装并排序
	public static String reviseJSON(String json) {
		try {
			JsonNode rootNode = mapper.readTree(json);
			Iterator<Map.Entry<String,JsonNode>> its = rootNode.fields();
			json = sortjson(its);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	
	//对json字符串中值为null的去除
	public static String delNull(String json) {
		try {
			Iterator<Map.Entry<String,JsonNode>> its = mapper.readTree(json).fields();
			return dNull(its);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//对json字符串中值为null的去除
	private static String dNull(Iterator<Map.Entry<String,JsonNode>> its) {
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("{");
		while(its.hasNext()) {
			Map.Entry node = its.next();
			JsonNode jsonNode = (JsonNode)node.getValue();
			if(jsonNode.isNull())
				continue;
			if(jsonNode.isObject()) {
				sBuffer.append("\"" + node.getKey() + "\"" + ":" + dNull(jsonNode.fields()) + ",");
				continue;
			}
			sBuffer.append("\"" + node.getKey() + "\"" + ":" + "\"" + jsonNode.asText() + "\"" + ",");
		}
		return sBuffer.substring(0, sBuffer.length()-1) + "}";
	}
	
	//对json进行排序并将值为null的去除
	private static String sortjson(Iterator<Map.Entry<String,JsonNode>> its) {
		StringBuffer sBuffer = new StringBuffer();
		List<Entry> nodeList = new ArrayList<Entry>();
		while(its.hasNext()) {
			Map.Entry<String, JsonNode> node = its.next();
			JsonNode jsonNode = (JsonNode)node.getValue();
			if(jsonNode.isNull())
				continue;
			nodeList.add(new JsonUtil().new Entry(node));
		}
		Collections.sort(nodeList);
		sBuffer.append("{");
		for(Entry e : nodeList) {
			if(e.getValue().isObject()) {
				String value = sortjson(e.getValue().fields());
				if(StringUtils.isBlank(value))
					continue;
				else 
					sBuffer.append("\"" + e.getKey() + "\"" + ":" + value + ",");
				continue;
			} else if(StringUtils.isBlank(e.getValue().asText()))
				continue;
			sBuffer.append("\"" + e.getKey() + "\"" + ":" + "\"" + e.getValue().asText() + "\"" + ",");
		}
		if(StringUtils.isNotBlank(sBuffer.toString()) && !"{".equals(sBuffer.toString()))
			return sBuffer.substring(0, sBuffer.length()-1) + "}";
		else
			return null;
	}
	
	
	//内部类用于对比排序
	class Entry implements Comparable<Entry>{
		private Map.Entry<String, JsonNode> su;
		Entry(Map.Entry<String, JsonNode> su) {
			this.su = su;
		}
		public String getKey() {
			return su.getKey();
		}

		public JsonNode getValue() {
			return su.getValue();
		}

		public JsonNode setValue(JsonNode node) {
			return su.setValue(node);
		}
		
		private int compare(String a,String b,int num) {
			int result = -1;
			if(a.charAt(0) < b.charAt(0)) {
				result = -1;
			} else if(a.charAt(0) == b.charAt(0) && num >0) {
				if((num--) == 1)
					return compare(a.substring(0),b.substring(0),num);
				result = compare(a.substring(1),b.substring(1),num);
			}else {
				result = 1;
			}
			return result;
		}
		@Override
		public int compareTo(Entry other) {
			int thislen = getKey().length();
			int otherlen = other.getKey().length();
			//取最短字符串的长度
			int num = thislen - otherlen < 0 ? thislen : thislen - otherlen > 0 ? otherlen : thislen ;
			return compare(getKey(),other.getKey(),num);
		}
	}
}