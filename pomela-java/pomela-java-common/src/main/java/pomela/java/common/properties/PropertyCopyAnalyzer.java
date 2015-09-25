package pomela.java.common.properties;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by tao.he on 2015/9/25.
 */
public class PropertyCopyAnalyzer {

	private static void copyProperties() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		//BeanUtils.copyProperties 不安全BigDecimal为null时copy异常
		ProBean bean0 = new ProBean();
		bean0.setStr("a");
		bean0.setBigDec(new BigDecimal(1));
		bean0.setDou(1.2D);
		bean0.setFlo(1.1F);
		bean0.setInte(1);
		bean0.setLon(2L);

		ProBean bean1 = new ProBean();
		bean1.setStr("b");
		bean1.setDou(1.5D);
		bean1.setBigDec(new BigDecimal(2));

		BeanUtils.copyProperties(bean0, bean1);
		System.out.println(ReflectionToStringBuilder.toString(bean0));

		//PropertyUtils.copyProperties
		ProBean bean2 = new ProBean();
		bean2.setStr("a");
		bean2.setBigDec(new BigDecimal(1));
		bean2.setDou(1.2D);
		bean2.setFlo(1.1F);
		bean2.setInte(1);
		bean2.setLon(2L);

		ProBean bean3 = new ProBean();
		bean3.setStr("b");
		bean3.setDou(1.5D);

		PropertyUtils.copyProperties(bean2, bean3);
		System.out.println(ToStringBuilder.reflectionToString(bean2));

		//org.springframework.beans.BeanUtils.copyProperties
		ProBean bean4 = new ProBean();
		bean4.setStr("a");
		bean4.setBigDec(new BigDecimal(1));
		bean4.setDou(1.2D);
		bean4.setFlo(1.1F);
		bean4.setInte(1);
		bean4.setLon(2L);

		ProBean bean5 = new ProBean();
		bean5.setStr("b");
		bean5.setDou(1.5D);

		org.springframework.beans.BeanUtils.copyProperties(bean5, bean4);
		System.out.println(ToStringBuilder.reflectionToString(bean4));

		//BeanUtilsBean
		ProBean bean6 = new ProBean();
		bean6.setStr("a");
		bean6.setBigDec(new BigDecimal(1));
		bean6.setDou(1.2D);
		bean6.setFlo(1.1F);
		bean6.setInte(1);
		bean6.setLon(2L);
		bean6.setStri("stri");

		ProBean bean7 = new ProBean();
		bean7.setStr("b");
		bean7.setDou(1.5D);

		BeanUtilsBean beanUtils = new BeanUtilsBean() {
			@Override
			public void copyProperty(Object dest, String name, Object value) throws IllegalAccessException, InvocationTargetException {
				if (value != null) {
					super.copyProperty(dest, name, value);
				}
			}
		};
		beanUtils.copyProperties(bean6, bean7);
		System.out.println(ToStringBuilder.reflectionToString(bean6));

		//MergeBeanUtils.merge
		ProBean bean8 = new ProBean();
		bean8.setStr("a");
		bean8.setBigDec(new BigDecimal(1));
		bean8.setDou(1.2D);
		bean8.setFlo(1.1F);
		bean8.setInte(1);
		bean8.setLon(2L);
		bean8.setStri("stri");

		ProBean bean9 = new ProBean();
		bean9.setStr("b");
		bean9.setDou(1.5D);
		MergeBeanUtils.merge(bean9, bean8);
		System.out.println(ToStringBuilder.reflectionToString(bean8));
	}

	public static void main(String[] args) throws Exception {
		copyProperties();
	}

}


