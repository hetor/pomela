package test.pomela.java.common.date;

import java.util.Date;

import org.junit.Test;

import pomela.java.common.date.DateUtil;

/**
 * Created by tao.he on 2015/10/13.
 */
public class DateUtilTest {

	@Test
	public void test_toStr() {
		String s = DateUtil.toStr(new Date(), DateUtil.DatePattern.PATTERN_4);
		System.out.println(s);
	}

	@Test
	public void test_fromStr() {
		Date date = DateUtil.fromStr("13/10/2015:20:06:54", DateUtil.DatePattern.PATTERN_1);
		System.out.println(DateUtil.toStr(date));
	}
}
