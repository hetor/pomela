package test.pomela.java.common.date;

import java.util.Date;

import org.junit.Test;

import pomela.java.common.date.DateFormatUtils;
import pomela.java.common.date.DatePattern;

/**
 * Created by tao.he on 2015/10/13.
 */
public class DateFormatUtilsTest {

	@Test
	public void test_toStr() {
		String s = DateFormatUtils.toStr(new Date(), DatePattern.PATTERN_4);
		System.out.println(s);
	}

	@Test
	public void test_fromStr() {
		Date date = DateFormatUtils.fromStr("13/10/2015:20:06:54", DatePattern.PATTERN_1);
		System.out.println(DateFormatUtils.toStr(date));
	}
}
