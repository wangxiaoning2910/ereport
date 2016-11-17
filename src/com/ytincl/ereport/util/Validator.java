package com.ytincl.ereport.util;

import java.util.ArrayList;
import java.util.List;

public class Validator {
	
	
	public static void main(String[] args) {
		Validator validator=new Validator();
    	String[] a1={"a1","小明","99"};
    	String[] a2={"a2","小红","89"};
    	String[] a3={"a3","小亮","79"};
    	String[] a4={"a4","小毛","69"};
    	
    	List list=new ArrayList();
    	list.add(a1);
    	list.add(a2);
    	list.add(a3);
    	list.add(a4);
    	
    	String[] b1={"b1","小明","9"};
    	String[] b2={"b2","小红","8"};
    	String[] b3={"b3","小亮","7"};
    	String[] b4={"b4","小毛","6"};
    	
    	
    	
    	List list2=new ArrayList();
    	list2.add(b1);
    	list2.add(b2);
    	list2.add(b3);
    	list2.add(b4);
    	
    	String[] c1={"c1","小明","90"};
    	String[] c2={"c2","小红","80"};
    	String[] c3={"c3","小亮","70"};
    	String[] c4={"c4","小毛","60"};
    	
    	
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
	 * 判断上传文件和模板的表头列数是否一致
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
	 * 判断上传文件之间行是否各相同
	 */
	public boolean valiDatetarget(List<List<String[]>> tagertList){
		boolean flag=false;
		int singleLength=0;
		for(int i=0;i<tagertList.size();i++){
			  List<String[]> singleList=tagertList.get(1);
			  singleLength=singleList.size();
			  //判断文件之间行是否相等
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
