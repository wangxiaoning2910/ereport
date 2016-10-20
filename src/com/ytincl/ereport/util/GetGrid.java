package com.ytincl.ereport.util;

import java.util.HashMap;
import java.util.Map;

public class GetGrid {
	
	private int x;
	private int y;
	private String grid;
	private Object[] grids;
	private final static Map<String, Integer> CHARTONUM = new HashMap<String, Integer>();
	static{
		CHARTONUM.put("A", 0);
		CHARTONUM.put("B", 1);
		CHARTONUM.put("C", 2);
		CHARTONUM.put("D", 3);
		CHARTONUM.put("E", 4);
		CHARTONUM.put("F", 5);
		CHARTONUM.put("G", 6);
		CHARTONUM.put("H", 7);
		CHARTONUM.put("I", 8);
		CHARTONUM.put("J", 9);
		CHARTONUM.put("K", 10);
		CHARTONUM.put("L", 11);
		CHARTONUM.put("M", 12);
		CHARTONUM.put("N", 13);
		CHARTONUM.put("O", 14);
		CHARTONUM.put("P", 15);
		CHARTONUM.put("Q", 16);
		CHARTONUM.put("R", 17);
		CHARTONUM.put("S", 18);
		CHARTONUM.put("T", 19);
		CHARTONUM.put("U", 20);
		CHARTONUM.put("V", 21);
		CHARTONUM.put("W", 22);
		CHARTONUM.put("X", 23);
		CHARTONUM.put("Y", 24);
		CHARTONUM.put("Z", 25);
	}
	public int getX() {
		String x = (String)grids[0];
		System.out.println("x==="+x);
		System.out.println("x.length()==="+x.length());
		if(x.length() == 2){
			System.out.println("x.substring(0,0)====="+x.substring(0,1));
			int a = CHARTONUM.get(x.substring(0,1))+1;
			System.out.println("a==="+a);
			int b = 26 * a;
			System.out.println("b==="+b);
			this.x = b + CHARTONUM.get(x.substring(1,2));
		}else{
			this.x = CHARTONUM.get(x);
			
		}
		return this.x;
	}
	public int getY() {
		this.y = Integer.parseInt(String.valueOf(grids[1]));
		return this.y;
	}
	public GetGrid(String grid){
		this.grid = grid;
		setGrids();
	}
	private void setGrids(){
		String str = this.grid;
		grids = str.split("-");
	}
}

