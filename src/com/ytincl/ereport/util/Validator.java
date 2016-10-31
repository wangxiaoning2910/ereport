package com.ytincl.ereport.util;

import java.util.ArrayList;
import java.util.List;

public class Validator {
	
	
	public static void main(String[] args) {
		Validator validator=new Validator();
    	String[] a1={"a1","С��","99"};
    	String[] a2={"a2","С��","89"};
    	String[] a3={"a3","С��","79"};
    	String[] a4={"a4","Сë","69"};
    	
    	List list=new ArrayList();
    	list.add(a1);
    	list.add(a2);
    	list.add(a3);
    	list.add(a4);
    	
    	String[] b1={"b1","С��","9"};
    	String[] b2={"b2","С��","8"};
    	String[] b3={"b3","С��","7"};
    	String[] b4={"b4","Сë","6"};
    	
    	
    	
    	List list2=new ArrayList();
    	list2.add(b1);
    	list2.add(b2);
    	list2.add(b3);
    	list2.add(b4);
    	
    	String[] c1={"c1","С��","90"};
    	String[] c2={"c2","С��","80"};
    	String[] c3={"c3","С��","70"};
    	String[] c4={"c4","Сë","60"};
    	
    	
    	List list3=new ArrayList();
    	list3.add(c1);
    	list3.add(c2);
    	list3.add(c3);
     	list3.add(c4);
    	
    	
    	
    	List totalList=new ArrayList();
    	totalList.add(list);
    	totalList.add(list2);
    	totalList.add(list3);
    	
    	String[]props =new String[]{"a1","b1","c1"};
    	
    	System.out.println(validator.valiDateAndtarget(list3, props));
    	System.out.println(validator.valiDatetarget(totalList));
	}
	
	

	/*
	 * �ж��ϴ��ļ���ģ��ı�ͷ�����Ƿ�һ��
	 */
	
	public boolean valiDateAndtarget(List<String[]> tagertList,String []fprop){
		boolean flag=false;
		for(int i=0;i<tagertList.size();i++){
			  String []str=tagertList.get(i);
			  if(str.length==fprop.length){
				  flag=true;
			  }else{
				  flag=false;
				  break;
			  }
		}
		return flag;
	}
	
	/**
	 * �ж��ϴ��ļ�֮�����Ƿ����ͬ
	 */
	public boolean valiDatetarget(List<List<String[]>> tagertList){
		boolean flag=false;
		int singleLength=0;
		for(int i=0;i<tagertList.size();i++){
			  List<String[]> singleList=tagertList.get(1);
			  singleLength=singleList.size();
			  //�ж��ļ�֮�����Ƿ����
			  if(singleLength==tagertList.get(i).size()){
				  flag=true;
			  }
			  else{
				  flag=false;
				  break;
			  }
		}
		return flag;
	}
	
}
