package com.ytincl.ereport.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.ytincl.ereport.pojo.ResolveExcel;
import com.ytincl.ereport.pojo.RowData;
import com.ytincl.ereport.util.ExportExcel;
import com.ytincl.ereport.util.ExportExcel1;
import com.ytincl.ereport.util.ReadExcel;

public class test {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		ExportExcel1 ex = new ExportExcel1();
		String tilele = "sheet1";
		String[] headers = { "日期", "内容1", "内容2", "内容3", "内容4","内容5","内容6","内容7" };
		ArrayList<RowData> list = new ArrayList<RowData>();
		for(int i = 0;i < 100;i++){
			RowData rd = new RowData();
			String[] strs = {"20160921","456","你试试","你再试试"};
			rd.setStrs(strs);
			list.add(rd);
		}
		OutputStream out = new FileOutputStream("E://a.xls");
		ex.exportExcel(tilele, headers, list, out);;
		try {
			out.close();
			System.out.println("excel导出成功！");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
