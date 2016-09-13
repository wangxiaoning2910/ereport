package com.ytincl.ereport.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ytincl.ereport.pojo.ResolveExcel;
import com.ytincl.ereport.util.ReadExcel;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ResolveExcel re = null;new ResolveExcel();
		List<ResolveExcel> l = new ArrayList<ResolveExcel>();
		List<String[]> list = null;
		try {
			list = new ReadExcel().readExcel("C:\\Users\\wangxiaoning\\Desktop\\12.xlsx");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (list != null) {
			
			for (int i = 0;i<list.size();i++) {
				re = new ResolveExcel();
				String[] str = list.get(i);
				re.setNo(str[0]);
				re.setName(str[1]);
				re.setAge(str[2]);
				re.setScore(str[3]);
				System.out.println("²âÊÔNO=="+re.getNo()+",Name===="+re.getName()+",Age===="+re.getAge()+",Score====="+re.getScore());
				l.add(re);
			}
			
			
		}
	}

}
