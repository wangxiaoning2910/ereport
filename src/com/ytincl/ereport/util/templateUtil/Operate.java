package com.ytincl.ereport.util.templateUtil;

import java.util.Stack;

/**
 * ����ջ����������������� ������ջ��ʵ��������ȣ�һ��ջ����������Ҫ���������numStack,һ����������������ȷ�priStack
 * 
 * �����㷨ʵ��˼·Ϊ���õ�ǰȡ�õ��������priStackջ��������Ƚ����ȼ��������ڣ�����Ϊ�������㣬����ջ����
 * �����ڣ���Ϊ�����ں��棬���Ի����㣬����ջ��Ԫ�س�ջ��ȡ�����������㣻 ��С�ڣ���ͬ��ȡ��ջ��Ԫ�����㣬������������ջ���������ȼ�'(' > '*'
 * = '/' > '+' = '-' > ')'
 * 
 */
public class Operate {
	private Stack<Character> priStack = new Stack<Character>();// ������ջ
	private Stack<Integer> numStack = new Stack<Integer>();;// ������ջ

	/**
	 * ������Ҫ�������ַ��������ؼ�����(�˴���Ϊʱ�����⣬ʡ�ԺϷ�����֤)
	 * 
	 * @param str
	 *            ��Ҫ���м����ı��ʽ
	 * @return ������
	 */
	public int caculate(String str) {
		// 1.�ж�string������û�зǷ��ַ�
		String temp;// ������ʱ��Ŷ�ȡ���ַ�
		// 2.ѭ����ʼ�����ַ��������ַ��������꣬�ҷ���ջΪ��ʱ����������
		StringBuffer tempNum = new StringBuffer();// ������ʱ��������ַ���(��Ϊ��λ��ʱ)
		StringBuffer string = new StringBuffer().append(str);// �������棬���Ч��

		while (string.length() != 0) {
			temp = string.substring(0, 1);
			string.delete(0, 1);
			// �ж�temp����tempΪ������ʱ
			if (!isNum(temp)) {
				// 1.��ʱ��tempNum�ڼ�Ϊ��Ҫ����������ȡ������ѹջ���������tempNum
				if (!"".equals(tempNum.toString())) {
					// �����ʽ�ĵ�һ������Ϊ����
					int num = Integer.parseInt(tempNum.toString());
					numStack.push(num);
					tempNum.delete(0, tempNum.length());
				}
				// �õ�ǰȡ�õ��������ջ��������Ƚ����ȼ��������ڣ�����Ϊ�������㣬����ջ���������ڣ���Ϊ�����ں��棬���Ի����㣬����ջ��Ԫ�س�ջ��ȡ�����������㣻
				// ��С�ڣ���ͬ��ȡ��ջ��Ԫ�����㣬������������ջ��

				// �жϵ�ǰ�������ջ��Ԫ�����ȼ���ȡ��Ԫ�أ����м���(��Ϊ���ȼ�����С��ջ��Ԫ�أ���С�ڵڶ���Ԫ�صȵȣ���Ҫ��ѭ���ж�)
				while (!compare(temp.charAt(0)) && (!priStack.empty())) {
					int a = (int) numStack.pop();// �ڶ���������
					int b = (int) numStack.pop();// ��һ��������
					char ope = priStack.pop();
					int result = 0;// ������
					switch (ope) {
					// ����ǼӺŻ��߼��ţ���
					case '+':
						result = b + a;
						// ������������������ջ
						numStack.push(result);
						break;
					case '-':
						result = b - a;
						// ������������������ջ
						numStack.push(result);
						break;
					case '*':
						result = b * a;
						// ������������������ջ
						numStack.push(result);
						break;
					case '/':
						result = b / a;// ������������������ջ
						numStack.push(result);
						break;
					}

				}
				// �жϵ�ǰ�������ջ��Ԫ�����ȼ��� ����ߣ����ߵ���ƽ��������󣬽���ǰ�������ţ����������ջ
				if (temp.charAt(0) != '#') {
					priStack.push(new Character(temp.charAt(0)));
					if (temp.charAt(0) == ')') {// ��ջ��Ϊ'('������ǰԪ��Ϊ')'ʱ�����������������꣬ȥ������
						priStack.pop();
						priStack.pop();
					}
				}
			} else
				// ��Ϊ�ǲ�����ʱ�����֣�
				tempNum = tempNum.append(temp);// ����������һλ���ӵ��Զ���������(�����Ǹ�λ����ʱ��)
		}
		return numStack.pop();
	}

	/**
	 * �жϴ�����ַ��ǲ���0-9������
	 * 
	 * @param str
	 *            ������ַ���
	 * @return
	 */
	private boolean isNum(String temp) {
		return temp.matches("[0-9]");
	}

	/**
	 * �Ƚϵ�ǰ��������ջ��Ԫ�ز��������ȼ��������ջ��Ԫ�����ȼ��ߣ��򷵻�true�����򷵻�false
	 * 
	 * @param str
	 *            ��Ҫ���бȽϵ��ַ�
	 * @return �ȽϽ�� true�����ջ��Ԫ�����ȼ��ߣ�false�����ջ��Ԫ�����ȼ���
	 */
	private boolean compare(char str) {
		if (priStack.empty()) {
			// ��Ϊ��ʱ����Ȼ ��ǰ���ȼ���ͣ����ظ�
			return true;
		}
		char last = (char) priStack.lastElement();
		// ���ջ��Ϊ'('��Ȼ�����ȼ���ͣ�')'������Ϊջ����
		if (last == '(') {
			return true;
		}
		switch (str) {
		case '#':
			return false;// ������
		case '(':
			// '('���ȼ����,��Ȼ����true
			return true;
		case ')':
			// ')'���ȼ���ͣ�
			return false;
		case '*': {
			// '*/'���ȼ�ֻ��'+-'��
			if (last == '+' || last == '-')
				return true;
			else
				return false;
		}
		case '/': {
			if (last == '+' || last == '-')
				return true;
			else
				return false;
		}
		// '+-'Ϊ��ͣ�һֱ����false
		case '+':
			return false;
		case '-':
			return false;
		}
		return true;
	}

	/*
	 * ���������������±� 
	 * (A+B)*C ===����1+2��*3 
	 * String[]mode Ϊ ReportTools���е�str
	 * 
	 */
	public static String ruleToNumber(String str) {

		String[] mode={"a","b","c"};
		char[] strByte = str.toCharArray();
		for (int i = 0; i < strByte.length; i++) {
			for (int j = 0; j < mode.length; j++) {
				String temp = strByte[i] + "";
				if (temp.equalsIgnoreCase(mode[j])) {
					String zh = String.valueOf(j);
					strByte[i] = zh.charAt(0);
				}
			}
		}

		String result = String.copyValueOf(strByte);
		return result;
	}

	public static void main(String args[]) {
		Operate operate = new Operate();
		/*
//		int t = operate.caculate("(3+4*(4*10-10/2)#");
		
		String test="(A+B)*C+B";
		String a=ruleToNumber(test);
		int r=operate.caculate(a);
		
		System.out.println(r);
		
		*/
		String[] mode={"a","b","c"};
		String[] modeValue={"1","2","3"};
		String test="(A+B)*C+(B/6)";
		String a=Arithmetic.ruleToNumber(mode,modeValue,test);
		System.out.println(test);
		System.out.println(a);
		System.out.println(operate.caculate(a));
	}

}
