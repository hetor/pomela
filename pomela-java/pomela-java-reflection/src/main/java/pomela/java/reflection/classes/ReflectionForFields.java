package pomela.java.reflection.classes;

import java.lang.reflect.Field;

/**
 * Created by hetor on 16/6/30.
 */
public class ReflectionForFields {

	/**
	 * getField(): 先从指定的class中找,然后到父接口中找,然后到父类中找,找不到throws NoSuchFieldException
	 */
	public static void getPublicField() {
		try {
			Field field = Class.forName("pomela.java.reflection.classes.ConcreteClass").getField("interfaceInt");
			System.out.println(field.getName());
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * getDeclaringClass():获取声明该field的class
	 */
	public static void getFieldDeclaringClass() {
		try {
			Field field = Class.forName("pomela.java.reflection.classes.ConcreteClass").getField("interfaceInt");
			Class<?> fieldClass = field.getDeclaringClass();
			System.out.println(fieldClass.getCanonicalName()); //prints pomela.java.reflection.classes.BaseInterface
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * getType():返回field的type的class对象,如果是原始类型,返回包装类对象
	 */
	public static void getFieldType() {
		Field field = null;
		try {
			field = Class.forName("pomela.java.reflection.classes.ConcreteClass").getField("publicInt");
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Class<?> fieldType = field.getType();
		System.out.println(fieldType.getCanonicalName()); //prints int
	}

	/**
	 * get():返回Object,如果是原始类型返回包装类型对象,如果field是static的方法的参数可以直接传null
	 * set*():有多个,针对原始类型的set方法,可以先判断field的type,然后选择正确的set方法;如果field是final的会抛出java.lang.IllegalAccessException
	 */
	public static void getSetPublicFieldValue() {
		try {
			Class<?> concreteClass = Class.forName("pomela.java.reflection.classes.ConcreteClass");
			Field field = concreteClass.getField("publicInt");
			Field field1 = concreteClass.getField("o");
			ConcreteClass obj = new ConcreteClass(5);
			System.out.println(field.get(obj)); //prints 5
			System.out.println(field1.get(null));
			field.setInt(obj, 10); //setting field value to 10 in object
			System.out.println(field.get(obj)); //prints 10
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 反射可以通过关闭java访问权限检查来调用private的field和method
	 */
	public static void getSetPrivateFieldValue() {
		try {
			Field privateField = Class.forName("pomela.java.reflection.classes.ConcreteClass").getDeclaredField("privateString");
			//turning off access check with below method call
			privateField.setAccessible(true);
			ConcreteClass objTest = new ConcreteClass(1);
			System.out.println(privateField.get(objTest)); // prints "private string"
			privateField.set(objTest, "private string updated");
			System.out.println(privateField.get(objTest)); //prints "private string updated"
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 不能set final变量,为什么?
	 */
	public static void setStaticFinalFiledValue() {
		try {
			Field privateField = Class.forName("pomela.java.reflection.classes.ConcreteClass").getDeclaredField("staticFinalField");
			privateField.setAccessible(true);
			ConcreteClass objTest = new ConcreteClass(1);
			System.out.println(privateField.get(objTest)); // prints "private string"
			privateField.set(objTest, "modified");
			System.out.println(privateField.get(objTest)); //prints "private string updated"
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
//		getPublicField();
//		getFieldDeclaringClass();
//		getFieldType();
//		getSetPublicFieldValue();
//		getSetPrivateFieldValue();
		setStaticFinalFiledValue();
	}
}
