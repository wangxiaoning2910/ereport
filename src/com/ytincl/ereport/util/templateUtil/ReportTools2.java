package com.ytincl.ereport.util.templateUtil;

import java.util.List;

public class ReportTools2 {
	public static final int JasperPDF = 0;
	public static final int JasperExcel = 1;
	public static final int JXLExcel = 2;
	public static final int POIExcel = 3;
	public static final int NewJXLExcel = 4;
	public static final String[] str = { "a", "b", "c", "d", "e", "f", "g",
			"h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
			"u", "v", "w", "x", "y", "z", "aa", "ab", "ac", "ad", "ae", "af",
			"ag", "ah", "ai", "aj", "ak", "al", "am", "an", "ao", "ap", "aq",
			"ar", "as", "at", "au", "av", "aw", "ax", "ay", "az", "ba", "bb",
			"bc", "bd", "be", "bf", "bg", "bh", "bi", "bj", "bk", "bl", "bm",
			"bn", "bo", "bp", "bq", "br", "bs", "bt", "bu", "bv", "bw", "bx",
			"by", "bz", "ca", "cb", "cc", "cd", "ce", "cf", "cg", "ch", "ci",
			"cj", "ck", "cl", "cm", "cn", "co", "cp", "cq", "cr", "cs", "ct",
			"cu", "cv", "cw", "cx", "cy", "cz", "da", "db", "dc", "dd", "de",
			"df", "dg", "dh", "di", "dj", "dk", "dl", "dm", "dn", "do", "dp",
			"dq", "dr", "ds", "dt", "du", "dv", "dw", "dx", "dy", "dz", "ea",
			"eb", "ec", "ed", "ee", "ef", "eg", "eh", "ei", "ej", "ek", "el",
			"em", "en", "eo", "ep", "eq", "er", "es", "et", "eu", "ev", "ew",
			"ex", "ey", "ez", "fa", "fb", "fc", "fd", "fe", "ff", "fg", "fh",
			"fi", "fj", "fk", "fl", "fm", "fn", "fo", "fp", "fq", "fr", "fs",
			"ft", "fu", "fv", "fw", "fx", "fy", "fz" };

	public ReportTools2() {
	}

	public static void main(String[] args) {
		ReportTools2 tables = new ReportTools2();
//		tables.getStringToCood_ColRow("AA8");
//		tables.getPos_ColRow("C8", "F8");
		System.out.println(tables.getStringToCoodNextNRowCoodString("c8", 2));
	}

	public static int[] getStringToCood_ColRow(String Cood) {
		int[] cood = new int[2];
		String strOne = "", strTwo = "";
		if (Cood != null || Cood.length() != 0) {
			for (int n = 0; n < Cood.length(); n++) {
				if (Cood.charAt(n) < 48 || Cood.charAt(n) > 58) {
					strOne += Cood.charAt(n);
					
				} else {
					strTwo = Cood.substring(n, Cood.length());
					break;
				}
			}
		}
		cood[0] = getColumnsIndex(strOne);
		cood[1] = getRowsIndex(strTwo);		
		//System.out.println("cood[0]="+cood[0]);
		//System.out.println("cood[1]="+cood[1]);
		return cood;
	}

	public static String[] getPos_ColRow(String first, String end) {
		int[] firstarr = getStringToCood_ColRow(first);		
		int rowIndex = firstarr[1] + 1;
		int[] endarr = getStringToCood_ColRow(end);
		String[] strarr = new String[endarr[0] - firstarr[0] + 1];		
		for (int i = 0, n = firstarr[0]; n <= endarr[0]; n++) {
			strarr[i++] = str[n] + rowIndex;
		}
		for (int i = 0; i < strarr.length; i++) {
			//System.out.print("getPos="+strarr[i] + " ");
		}
		return strarr;
	}

	public static int[] getStringToCoodNextNRowCood(String Cood, int n) {
		int[] cood = getStringToCood_ColRow(Cood);
		cood[1] = cood[1] + n;
		return cood;
	}

	public static String getStringToCoodNextNRowCoodString(String Cood, int next) {
		int[] cood = new int[2];
		String positionCood = "";
		String strOne = "", strTwo = "";
		if (Cood != null || Cood.length() != 0) {
			for (int n = 0; n < Cood.length(); n++) {
				if (Cood.charAt(n) < 48 || Cood.charAt(n) > 58) {
					strOne += Cood.charAt(n);
				} else {
					strTwo = Cood.substring(n, Cood.length());
					break;
				}
			}
		}
		cood[0] = getColumnsIndex(strOne);
		cood[1] = getRowsIndex(strTwo) + next;
		positionCood = strOne + cood[1];
		return positionCood;
	}

	public static int[] getStringToCoodNextNRowCoodPosition(String Cood,
			int next) {
		int[] cood = new int[2];
		String strOne = "", strTwo = "";
		if (Cood != null || Cood.length() != 0) {
			for (int n = 0; n < Cood.length(); n++) {
				if (Cood.charAt(n) < 48 || Cood.charAt(n) > 58) {
					strOne += Cood.charAt(n);
				} else {
					strTwo = Cood.substring(n, Cood.length());
					break;
				}
			}
		}
		cood[0] = getColumnsIndex(strOne);
		cood[1] = getRowsIndex(strTwo) + next;
		return cood;
	}

	public static int[] getStringToCoodNextRowCood(String Cood) {
		int[] cood = getStringToCood_ColRow(Cood);
		cood[1] = ++cood[1];
		return cood;
	}

	public static int getColumnsIndex(String word) {

		int columnsIndex = -1;
		for (int i = 0; i < str.length; i++) {
			if (word.equalsIgnoreCase(str[i])) {
				columnsIndex = i;
				break;
			}
		}
		return columnsIndex;
	}

	public static int getRowsIndex(String word) {
		int rowsIndex = -1;
		if (Integer.valueOf(word).intValue() - 1 >= 0) {
			rowsIndex = Integer.valueOf(word).intValue() - 1;
		}
		return rowsIndex;
	}
	
	
	public static int getFpropsIndexByWord(String[] fprop,String word){
		//String word="c6";
		int index=0;
		for(int i=0;i<fprop.length;i++){
			if(fprop[i].equals(word)){
				index=i;
				break;
			}
		}
		return index;
	}
	
	public static List<String[]> getSumColByIndexForSumList(List<String[]> firstList,List<String[]> tmpList,int index){
		
		for(int i=0;i<firstList.size();i++){
			
			  String []str=firstList.get(i);
			  String []tmpstr=tmpList.get(i);
			  for(int k=0;k<str.length;k++){
				  if(k==index){
					  str[k]=str[k]+tmpstr[k];
				  }
			  }
			firstList.set(i, str);
			
		}
		
		
		return firstList;
	}
	
}
