package pomela.java.common.utils;

import java.util.Random;

/**
 * Created by tao.he on 2015/10/13.
 */
public class RandomUtil {

	private static final int[] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

	public static String getNumbers(int size, boolean firstZeroAllowed) {
		Random random = new Random();
		StringBuilder sb = new StringBuilder(size);

		int firstNum = random.nextInt(10);
		while(!firstZeroAllowed && firstNum == 0) {
			firstNum = random.nextInt(10);
		}
		sb.append(firstNum);

		for(int i=1; i < size; i++) {
			sb.append(random.nextInt(10));
		}

		return sb.toString();
	}
}
