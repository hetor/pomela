package pomela.java.common.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tao.he on 2016/1/14.
 */
public class BeanUtil {

	public static Map<String, Object> toMap(Object o) {
		return toMap(o, false);
	}

	public static Map<String, Object> toMap(Object o, boolean ignoreNullValue) {
		Map<String, Object> map;
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(o.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			map = new HashMap<>(propertyDescriptors.length);
			for (PropertyDescriptor property : propertyDescriptors) {
				String propertyName = property.getName();
				if (!"class".equals(propertyName)) {
					Method getter = property.getReadMethod();
					Object value = getter.invoke(o);
					if(!ignoreNullValue || null != value) {
						map.put(propertyName, value);
					}
				}
			}
			return Collections.unmodifiableMap(map);
		} catch (Exception e) {
			throw new RuntimeException("BeanUtil.toMap error", e);
		}
	}

	public static <T> T fromMap(Map<String, Object> map, Class<T> clazz) {
		try {
			T t = clazz.newInstance();
			BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String propertyName = property.getName();
				System.out.println("--" + propertyName);
				if (map.containsKey(propertyName)) {
					Object value = map.get(propertyName);
					Method setter = property.getWriteMethod();
					setter.invoke(t, value);
				}
			}
			return t;
		} catch (Exception e) {
			throw new RuntimeException("BeanUtil.fromMap Error ", e);
		}
	}
}
