package com.tangerine.yuzu.traps;

/**
 * Created by tao.he on 2015/9/25.
 *
 * ��ָ���쳣����˲�Ҫʹ��Integer��intֱ��==�Ƚϣ�ʹ��equals���棬��ò�Ҫͬʱʹ��int��Integer
 */
public class IntegerCompareTrap {
	public static void main(String[] args) {
		Integer a = null;
		int b = 0;

		if(b == a) {
			System.out.println("a equals b");
		} else {
			System.out.println("a not equals b");
		}
	}
}
