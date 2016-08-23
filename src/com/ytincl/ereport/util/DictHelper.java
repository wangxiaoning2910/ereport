package com.ytincl.ereport.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ytincl.ereport.constant.CommonConstants;
import com.ytincl.ereport.model.DictEntity;


/**
 * 
 * 数据字典辅助类
 * @since 20160817
 * @version 1.0
 */
public class DictHelper {
	
	private static Map<String,Map<String,String>> DICTMAP=null;
	private static Map<String,Map<String,List<DictEntity>>> DICTENTITYMAP=null;
	private static Map<String,Map<String,String>> dictMap=new HashMap<String,Map<String,String>>();
	private static Map<String,Map<String,List<DictEntity>>> dictEntityMap=new HashMap<String,Map<String,List<DictEntity>>>();
	
	/**日志**/
	private static final Logger LOGGER = LoggerFactory.getLogger(DictHelper.class);
	
	/**构造**/
	public static void init(Map<String,Properties> proMap){
		if(null!=proMap&&!proMap.isEmpty()){
			Set<String> keySet=proMap.keySet();
			Iterator<String> proIterator= keySet.iterator();
			while(proIterator.hasNext()){
				String areaName=proIterator.next();
				Properties proObj=proMap.get(areaName);
				initDictInfo(areaName,proObj);
				LOGGER.debug("文件名:"+areaName);
			}
			DICTMAP=Collections.unmodifiableMap(dictMap);
			DICTENTITYMAP=Collections.unmodifiableMap(dictEntityMap);
		}
	}
	
	/**
	 * 初始化字典
	 * @param areaName
	 * @param pro
	 */
	private static void initDictInfo(String areaName,Properties pro){
		if(null!=areaName&&null!=pro){
			if(!dictMap.containsKey(areaName)&&!dictEntityMap.containsKey(areaName)){
				generateProperties(areaName,pro);
			}else{
				//文件已经加载
			}
		}
	}
	
	/**
	 * 遍历生成静态集合
	 * @param areaName 文件名
	 * @param pro      properties对象
	 */
	private static  void generateProperties(String areaName,Properties pro){
		Map<String,String> mapStr=new HashMap<String,String>();
		Map<String,List<DictEntity>> mapEntity=new HashMap<String,List<DictEntity>>();
		Set<Object> keySet=pro.keySet();
		Iterator<Object> iter=keySet.iterator();
		while(iter.hasNext()){
			Object keyObj=iter.next();
			if(null!=keyObj&&!keyObj.toString().trim().equals("")){
				String keyStr=keyObj.toString();
				String ValueStr=pro.getProperty(keyStr);
				generateStrMap(mapStr,keyStr,ValueStr);
				generateEntityMap(mapEntity,keyStr,ValueStr);
			}
		}
		dictMap.put(areaName, mapStr);
		dictEntityMap.put(areaName, mapEntity);
	}
	
	/**
	 * 遍历Map集合
	 * @param mapStr map集合
	 * @param key	   键
	 * @param value	  值
	 */
	private static  void generateStrMap(Map<String,String> mapStr,String key,String value){
		mapStr.put(key, value);
	}
	
	/**
	 * 遍历字典对象集合
	 * @param mapEntity 字典对象集合
	 * @param key       键
	 * @param value     值
	 */
	private static  void generateEntityMap(Map<String,List<DictEntity>> mapEntity,String key,String value){
		List<DictEntity> entityList=new ArrayList<DictEntity>();
		if(null!=value&&!CommonConstants.NULL_STRING.equals(value.toString().trim())){
			String [] strArray=value.split(CommonConstants.PROPERTIES_SPLIT);
			for(String strV:strArray){
				String [] attrArray=strV.split(CommonConstants.PROPERTIES_ATTR_SPLIT);
				if(attrArray.length==CommonConstants.DICT_ARRAY_LENGTH){
					DictEntity de=new DictEntity(attrArray[0],attrArray[1]);
					entityList.add(de);
				}
			}
		}
		mapEntity.put(key, entityList);
	}
	
	/**
	 * 据文件名获取数据字典集合(多行模式)
	 * @param areaName 文件名
	 * @return
	 */
	public static Map<String,String>  getDictByAreaName(String areaName){
		Map<String,String> areaMap=null;
		if(null!=DICTMAP&&!DICTMAP.isEmpty()&&DICTMAP.containsKey(areaName)){
			areaMap=new HashMap<String,String>();
			areaMap=DICTMAP.get(areaName);
		}else{
			LOGGER.debug("文件字典:"+areaName+",不存在!");
		}
		return areaMap;
	}
	
	/**
	 * 据文件名和键标示获取数据值(多行模式)
	 * @param areaName
	 * @param name
	 * @return
	 */
	public static String getDictByName(String areaName,String name){
		String value=CommonConstants.NULL_STRING;
		Map<String,String> areaMap=getDictByAreaName(areaName);
		if(null!=areaMap&&!areaMap.isEmpty()){
			if(areaMap.containsKey(name)){
				value=areaMap.get(name);
			}else{
				LOGGER.debug("文件字典:"+areaName+"存在,但该文件字典中不存在[键]为:"+name+"的字典项");
			}
		}
		return value;
	}
	
	/**
	 * 据文件名和键标示获取数据值(多行模式),支持默认值
	 * @param areaName
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public static String getDictByName(String areaName,String name,String defaultValue){
		String value=getDictByName(areaName,name);
		if(value.equals(CommonConstants.NULL_STRING)){
			value=defaultValue;
		}
		return value;
	}
	/**
	 * 据文件名获取字典实体集合(单行模式)
	 * @param areaName 文件名
	 * @return
	 */
	public static Map<String,List<DictEntity>>  getDictEntityByAreaName(String areaName){
		Map<String,List<DictEntity>> areaMap=null;
		if(null!=DICTENTITYMAP&&!DICTENTITYMAP.isEmpty()&&DICTENTITYMAP.containsKey(areaName)){
			areaMap=new HashMap<String,List<DictEntity>>();
			areaMap=DICTENTITYMAP.get(areaName);
		}else{
			LOGGER.debug("文件字典:"+areaName+",不存在!");
		}
		return areaMap;
	}
	
	/**
	 * 据文件名和键标示获取字典实体集(单行模式)
	 * @param areaName
	 * @param name
	 * @return
	 */
	public static List<DictEntity> getDictEntityByName(String areaName,String name){
		List<DictEntity> ld=null;
		Map<String,List<DictEntity>> areaMap=getDictEntityByAreaName(areaName);
		if(null!=areaMap&&!areaMap.isEmpty()){
			if(areaMap.containsKey(name)){
				ld=areaMap.get(name);
			}else{
				LOGGER.debug("文件字典:"+areaName+"存在,但该文件字典中不存在[键]为:"+name+"的字典项");
			}
		}
		return ld;
	}
	
	/**
	 * 获取键集合
	 * @param listEntity
	 * @return
	 */
	public static List<String> getKeyListByEntity(List<DictEntity> listEntity){
		List<String> listStr=null;
		if(null!=listEntity&&!listEntity.isEmpty()){
			listStr=new ArrayList<String>();
			for(DictEntity de:listEntity){
				listStr.add(de.getKey());
			}
		}
		return listStr;
	}
}
