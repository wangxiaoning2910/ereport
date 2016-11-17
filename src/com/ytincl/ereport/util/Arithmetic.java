package com.ytincl.ereport.util;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ����������ʽ����
 * 
 * @author penli
 * 
 */
public class Arithmetic {
	
	
	public static void main(String args[]) {
		
		
		String aaa=new String("abc");
		String b="";
		aaa=aaa.replace("abc", "123");
		//b=b.replace("b", "2");
		//b=b.replace("c", "3");
		System.out.println("aaa="+aaa);
		System.out.println("b="+b);
	
		//String a=ruleToNumber(test);
		
		//System.out.println(arithmetic(a));
		
		
		//System.out.println(arithmetic("2.2+((3+4)*2-22)/2*3.2"));
		
		String[] mode={"a","b","c"};
		String[] modeValue={"1","2","3"};
		//String test="(A+B)*C+(B//2)";
		String test="(a+b)*c+a+b*2/3".toLowerCase();
		String testValue=ruleToNumber(mode,modeValue,test);

		
	}
	public static String computeRow(String[]mode,String[] modeValue,String test){
		ExcelReader excelReader=new ExcelReader();
		ReportTools2 reportTools2=new ReportTools2();
		excelReader.showOneArray(mode);
		excelReader.showOneArray(modeValue);
		test=test+"+0";
		System.out.println("��ʽ��"+test);
		String [] fprop=ReportTools2.getLetterByFprop(mode).clone();
		excelReader.showOneArray(fprop);
		test=test.toLowerCase();
		String testValue=ruleToNumber(fprop,modeValue,test);		
		String value=""+arithmetic(testValue);

		System.out.println("���Σ�"+testValue);
		System.out.println("�����"+value);
		return value;
	}
	
	
	
	public String[] getWordByCol(String []fprops){
		String []ret=new String[fprops.length];
		for(int i=0;i<fprops.length;i++){
			//String []str=ReportTools2.getStringToCood_ColRow(fprops[i]);
		}
		
		
		return ret;
		
	}
	
	

	public static String ruleToNumber(String[]fprops,String[] fpropsValue,String test){

		for(int i=0;i<fprops.length;i++){
			String name=fprops[i];
			String value=fpropsValue[i];
			System.out.println("name="+name);
			System.out.println("value="+value);			
			test=test.replaceAll(name,value);
		}
		return test;
	}
	public static String ruleToNumber2(String[]fprops,String[] fpropsValue,String test){
		
		
		char[] strByte=test.toCharArray() ;
		for(int i=0;i<strByte.length;i++){
			for(int j=0;j<fprops.length;j++){
				String temp=strByte[i]+"";
				 if(temp.equalsIgnoreCase(fprops[j])){
					// String zh=String.valueOf(j);
					 String zh=fpropsValue[j];
					 System.out.println("62=="+zh);
					 strByte[i]=zh.charAt(0);
				 }
			}
		}
		
		String result=String.copyValueOf(strByte);
		return result;

		
	}	
	
	/*
	 * ���������������±�
	 * (A+B)*C ת��Ϊ��1+2��*3
	 * String[]mode Ϊ ReportTools2���е�str
	 */
	public static String ruleToNumber(String str){
		String[] mode={"a","b","c"};
		String[] modeValue={"1","2","3"};

		char[] strByte=str.toCharArray() ;
		for(int i=0;i<strByte.length;i++){
			for(int j=0;j<mode.length;j++){
				String temp=strByte[i]+"";
				 if(temp.equalsIgnoreCase(mode[j])){
					 String zh=String.valueOf(j);
					 strByte[i]=zh.charAt(0);
				 }
			}
		}
		
		String result=String.copyValueOf(strByte);
		return result;
	}
	
	public static double arithmetic(String exp) {
		String result = parseExp(exp).replaceAll("[\\[\\]]", "");
		return Double.parseDouble(result);
	}

	/**
	 * ������������������ʽ������2+((3+4)*2-22)/2*3
	 * 
	 * @param expression
	 * @return
	 */
	public static String parseExp(String expression) {
		// String numberReg="^((?!0)\\d+(\\.\\d+(?<!0))?)|(0\\.\\d+(?<!0))$";
		expression = expression.replaceAll("\\s+", "").replaceAll(
				"^\\((.+)\\)$", "$1");
		String checkExp = "\\d";
		String minExp = "^((\\d+(\\.\\d+)?)|(\\[\\-\\d+(\\.\\d+)?\\]))[\\+\\-\\*\\/]((\\d+(\\.\\d+)?)|(\\[\\-\\d+(\\.\\d+)?\\]))$";
		// ��С���ʽ����
		if (expression.matches(minExp)) {
			String result = calculate(expression);

			return Double.parseDouble(result) >= 0 ? result : "[" + result
					+ "]";
		}
		// ���㲻�����ŵ���������
		String noParentheses = "^[^\\(\\)]+$";
		String priorOperatorExp = "(((\\d+(\\.\\d+)?)|(\\[\\-\\d+(\\.\\d+)?\\]))[\\*\\/]((\\d+(\\.\\d+)?)|(\\[\\-\\d+(\\.\\d+)?\\])))";
		String operatorExp = "(((\\d+(\\.\\d+)?)|(\\[\\-\\d+(\\.\\d+)?\\]))[\\+\\-]((\\d+(\\.\\d+)?)|(\\[\\-\\d+(\\.\\d+)?\\])))";
		if (expression.matches(noParentheses)) {
			Pattern patt = Pattern.compile(priorOperatorExp);
			Matcher mat = patt.matcher(expression);
			if (mat.find()) {
				String tempMinExp = mat.group();
				expression = expression.replaceFirst(priorOperatorExp,
						parseExp(tempMinExp));
			} else {
				patt = Pattern.compile(operatorExp);
				mat = patt.matcher(expression);

				if (mat.find()) {
					String tempMinExp = mat.group();
					expression = expression.replaceFirst(operatorExp,
							parseExp(tempMinExp));
				}
			}
			return parseExp(expression);
		}
		// ��������ŵ���������
		String minParentheses = "\\([^\\(\\)]+\\)";
		Pattern patt = Pattern.compile(minParentheses);
		Matcher mat = patt.matcher(expression);
		if (mat.find()) {
			String tempMinExp = mat.group();
			expression = expression.replaceFirst(minParentheses,
					parseExp(tempMinExp));
		}
		return parseExp(expression);
	}

	/**
	 * ������С��λ����������ʽ���������֣�
	 * 
	 * @param exp
	 * @return
	 */
	public static String calculate(String exp) {
		exp = exp.replaceAll("[\\[\\]]", "");
		String number[] = exp.replaceFirst("(\\d)[\\+\\-\\*\\/]", "$1,").split(
				",");
		BigDecimal number1 = new BigDecimal(number[0]);
		BigDecimal number2 = new BigDecimal(number[1]);
		BigDecimal result = null;

		String operator = exp.replaceFirst("^.*\\d([\\+\\-\\*\\/]).+$", "$1");
		if ("+".equals(operator)) {
			result = number1.add(number2);
		} else if ("-".equals(operator)) {
			result = number1.subtract(number2);
		} else if ("*".equals(operator)) {
			result = number1.multiply(number2);
		} else if ("/".equals(operator)) {
			System.out.println("179=="+number2);
//			result = number1.divide(number2);
			result = number1.divide(number2,5,BigDecimal.ROUND_HALF_EVEN);

		}

		return result != null ? result.toString() : null;
	}
}
