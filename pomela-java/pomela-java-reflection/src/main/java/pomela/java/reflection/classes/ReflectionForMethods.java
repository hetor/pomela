package pomela.java.reflection.classes;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hetor on 16/6/30.
 */
public class ReflectionForMethods {

	/**
	 * getMethod(): 如果当前class找不到,到父类中找
	 */
	public static void getPublicMethod() {
		Method method = null;
		try {
			method = Class.forName("java.util.HashMap").getMethod("put", Object.class, Object.class);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//get method parameter types, prints "[class java.lang.Object, class java.lang.Object]"
		System.out.println(Arrays.toString(method.getParameterTypes()));
		//get method return type, return "class java.lang.Object", class reference for void
		System.out.println(method.getReturnType());
		//get method modifiers
		System.out.println(Modifier.toString(method.getModifiers())); //prints "public"
	}

	/**
	 * invoke(): 如果是static方法参数可以直接传null
	 */
	public static void invokingPublicMethod() {
		try {
			Class<?> aClass = Class.forName("java.util.HashMap");
			Method method =  aClass.getMethod("put", Object.class, Object.class);
			Object o = aClass.newInstance();
			method.invoke(o, "key", "value");
			System.out.println(o); // prints {key=value}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * getDeclaredMethod():该方法能获取到private的方法
	 */
	public static void invokingPrivateMethod() {
		//invoking private method
		try {
			Method method = Class.forName("pomela.java.reflection.classes.BaseClass").getDeclaredMethod("method3", null);
			method.setAccessible(true);
			method.invoke(null, null); //prints "Method3"
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		getPublicMethod();
		invokingPublicMethod();
		invokingPrivateMethod();
	}
}
