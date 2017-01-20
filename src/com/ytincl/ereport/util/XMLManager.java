package com.ytincl.ereport.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class XMLManager {
	public  XMLManager(){}
	private static String getXmlPath(HttpServletRequest req){
		String  xmlFilePath = null;
		try {
			xmlFilePath = req.getSession().getServletContext().getRealPath("/WEB-INF/classes/config/analyzeruelregulation.xml");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return xmlFilePath;
	}
	
	
	private static List<Map<String,String>> getRuelRegulations(HttpServletRequest req){
		String xmlfilepath = getXmlPath(req);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    dbf.setIgnoringElementContentWhitespace(true);
	    List<Map<String,String>> maplist = new ArrayList<Map<String,String>>();
	    try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(xmlfilepath); // 使用dom解析xml文件
            NodeList regulationlist = doc.getElementsByTagName("regulation"); 
            for (int i = 0; i < regulationlist.getLength(); i++){ // 循环处理对象
                Element regulation = (Element)regulationlist.item(i);;
                Map<String, String> map = new HashMap<>();
                for (Node node = regulation.getFirstChild(); node != null; node = node.getNextSibling()){  
                    if (node.getNodeType() == Node.ELEMENT_NODE){  
                		String name = node.getNodeName();  
                        String value = node.getFirstChild().getNodeValue();  
                        map.put(name, value);
                    }  
                }  
                maplist.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return maplist;
	}
	/**
	 * 根据文件名获取对应该文件的规则
	 * @author wangxiaoning
	 * @since 20161012
	 * @return Map<String,String>
	 * */
	public static Map<String,String> getRuelRegulation(HttpServletRequest req,String filename){
		List<Map<String,String>> list  = getRuelRegulations(req);
		Map<String,String> map;
		for(int i = 0;i<list.size();i++){
			map = list.get(i);
			if(map.get("name") != null && filename.contains(map.get("name"))){
				return map;
			}
		}
		return null;
	}
}

