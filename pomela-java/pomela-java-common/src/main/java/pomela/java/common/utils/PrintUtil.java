package pomela.java.common.utils;

import java.util.List;
import java.util.Map;

/**
 * Created by tao.he on 2015/10/14.
 */
public class PrintUtil {

	private PrintUtil() {}

	public static void toConsole(Object pojo) {
		System.out.println(pojo);
		System.out.println();
	}

	public static void toConsole(Object[] pojoList) {
		for (int i=0; i < pojoList.length; i++) {
			System.out.println(i + ": " + pojoList[i]);
		}
		System.out.println();
	}

	public static void toConsole(List<?> pojoList) {
		for (int i=0; i < pojoList.size(); i++) {
			System.out.println(i + ": " + pojoList.get(i));
		}
		System.out.println();
	}

	public static void toConsole(Map<?, ?> pojoMap) {
		for (Map.Entry<?, ?> entry : pojoMap.entrySet()) {
			System.out.println("key: " + entry.getKey() + " value: " + entry.getValue());
		}
		System.out.println();
	}
}
