package com.tangerine.yuzu.traps;

/**
 * Created by tao.he on 2015/9/25.
 *
 * 空指针异常，因此不要使用Integer和int直接==比较，使用equals代替，最好不要同时使用int和Integer
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
