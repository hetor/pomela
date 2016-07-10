package pomela.java.reflection.classes;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by hetor on 16/6/30.
 */
public class ReflectionForConstructors {

	/**
	 * getConstructor():
	 */
	public static void getPublicConstructor() {
		Constructor<?> constructor = null;
		try {
			constructor = Class.forName("pomela.java.reflection.classes.ConcreteClass").getConstructor(int.class);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//getting constructor parameters
		System.out.println(Arrays.toString(constructor.getParameterTypes())); // prints "[int]"

		Constructor<?> hashMapConstructor = null;
		try {
			hashMapConstructor = Class.forName("java.util.HashMap").getConstructor(null);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(Arrays.toString(hashMapConstructor.getParameterTypes())); // prints "[]"
	}

	/**
	 * newInstance():
	 */
	public static void instantiateObjectUsingConstructor() {
		try {
			Constructor<?> constructor = Class.forName("pomela.java.reflection.classes.ConcreteClass").getConstructor(int.class);
			Object myObj = constructor.newInstance(10);
			Method myObjMethod = myObj.getClass().getMethod("method1", null);
			myObjMethod.invoke(myObj, null); //prints "Method1 impl."

			Constructor<?> hashMapConstructor = Class.forName("java.util.HashMap").getConstructor(null);
			System.out.println(Arrays.toString(hashMapConstructor.getParameterTypes())); // prints "[]"
			HashMap<String,String> myMap = (HashMap<String,String>) hashMapConstructor.newInstance(null);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		getPublicConstructor();
		instantiateObjectUsingConstructor();
	}
}
