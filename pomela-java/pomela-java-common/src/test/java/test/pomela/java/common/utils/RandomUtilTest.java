package test.pomela.java.common.utils;

import org.junit.Test;
import pomela.java.common.utils.RandomUtil;

/**
 * Created by tao.he on 2015/10/13.
 */
public class RandomUtilTest {

	@Test
	public void test_getNumbers() {
		for(int i=0; i<100; i++) {
			String numbers = RandomUtil.getNumbers(1000, true);
			System.out.println(numbers);
			System.out.println(numbers.length());
			System.out.println();
		}
	}
}
