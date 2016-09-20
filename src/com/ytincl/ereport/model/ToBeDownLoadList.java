package com.ytincl.ereport.model;
import java.util.ArrayList;
import com.ytincl.ereport.pojo.ToBeDownLoadFile;
public class ToBeDownLoadList {
	private ArrayList<ToBeDownLoadFile> filelist;
	public ArrayList<ToBeDownLoadFile> getFilelist() {
		return filelist;
	}
	public void setFilelist(ArrayList<ToBeDownLoadFile> filelist) {
		this.filelist = filelist;
	}
}
