package pomela.java.common.properties;

import org.apache.commons.beanutils.BeanUtilsBean;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by tao.he on 2015/9/25.
 */
public class MergeBeanUtils {

	/**
	 * merge source bean properties to dest properties except null value
	 * 
	 * @param source
	 * @param dest
	 */
	public static void merge(Object source, Object dest) {
		if (null == source || null == dest) {
			return;
		}

		BeanUtilsBean beanUtils = new BeanUtilsBean() {
			@Override
			public void copyProperty(Object dest, String name, Object value)
					throws IllegalAccessException, InvocationTargetException {
				if (value != null) {
					super.copyProperty(dest, name, value);
				}
			}
		};

		try {
			beanUtils.copyProperties(dest, source);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException("merge bean exception", e);
		}
	}
}
