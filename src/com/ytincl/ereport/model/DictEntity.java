package com.ytincl.ereport.model;

public class DictEntity {

	/**
	 * 键
	 */
	private String key;
	
	/**
	 * 键-值 对的形式
	 */
	private String keyValue;
	
	public String getKeyValue() {
		return keyValue;
	}


	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}


	public String getKey() {
		return key;
	}


	public void setKey(String key) {
		this.key = key;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	/**
	 * 值
	 */
	private String value;
	
	
	public DictEntity(String key,String value){
		this.key=key;
		this.value=value;
		this.keyValue=key+'-'+value;
	}
}
