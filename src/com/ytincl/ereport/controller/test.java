package com.ytincl.ereport.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.ytincl.ereport.pojo.ResolveExcel;
import com.ytincl.ereport.util.ExportExcel;
import com.ytincl.ereport.util.ReadExcel;

public class test {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		ExportExcel<testdownloaddata> ex = new ExportExcel<testdownloaddata>();
		String[] headers = { "����", "����1", "����2", "����3", "����4","����5","����6","����7" };
		List<testdownloaddata> list = new ArrayList<testdownloaddata>();
		list.add(new testdownloaddata("201609", "content1", "content2", "content3", "content4", "content5", "conten6", "content7"));
		list.add(new testdownloaddata("201609", "content1", "content2", "content3", "content4", "content5", "conten6", "content7"));
		list.add(new testdownloaddata("201609", "content1", "content2", "content3", "content4", "content5", "conten6", "content7"));
		list.add(new testdownloaddata("201609", "content1", "content2", "content3", "content4", "content5", "conten6", "content7"));
		OutputStream out = new FileOutputStream("E://a.xls");
		ex.exportExcel(headers, list, out);
		try {
			out.close();
			System.out.println("excel�����ɹ���");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
