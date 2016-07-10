package pomela.java.reflection.classes;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Arrays;

/**
 * Created by hetor on 16/6/28.
 */
public class ReflectionForClasses {

	/**
	 * Class对象获取途径:
	 * 1. 静态变量class
	 * 2. 对象getClass()
	 * 3. java.lang.Class.forName(String fullyClassifiedClassName)
	 *
	 * getCanonicalName(): class权威的名字
	 */
	public static void getClassObject() {
		Class<?> concreteClass = ConcreteClass.class;
		concreteClass = new ConcreteClass(5).getClass();

		try {
			//below method is used most of the times in frameworks like JUnit
			//Spring dependency injection, Tomcat web container
			//Eclipse auto completion of method names, hibernate, Struts2 etc.
			//because ConcreteClass is not available at compile time
			concreteClass = Class.forName("pomela.java.reflection.classes.ConcreteClass");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		//Canonical Name
		System.out.println(concreteClass.getCanonicalName()); // prints pomela.java.reflection.classes.ConcreteClass

		//for primitive types, wrapper classes and arrays
		Class<?> booleanClass = boolean.class;
		System.out.println(booleanClass.getCanonicalName()); // prints boolean

		Class<?> cDouble = Double.TYPE;
		System.out.println(cDouble.getCanonicalName()); // prints double

		try {
			Class<?> cDoubleArray = Class.forName("[D");
			System.out.println(cDoubleArray.getCanonicalName()); // prints double[]
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Class<?> twoDStringArray = String[][].class;
		System.out.println(twoDStringArray.getCanonicalName()); // prints java.lang.String[][]
	}

	/**
	 * getSuperClass(): 如果Class是Object,接口,原始类型,void,返回null;如果Class是数组返回 class java.lang.Object
	 */
	public static void getSuperClass() {
		try {
			Class<?> superClass = Class.forName("pomela.java.reflection.classes.ConcreteClass").getSuperclass();
			System.out.println(superClass); // prints class pomela.java.reflection.classes.BaseClass
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		System.out.println(Object.class.getSuperclass()); // prints null
		System.out.println(BaseInterface.class.getSuperclass()); // prints null
		System.out.println(boolean.class.getSuperclass()); // prints null
		System.out.println(void.class.getSuperclass()); // prints null
		System.out.println(String[][].class.getSuperclass()); // prints class java.lang.Object
	}

	/**
	 * getClasses(): 返回类的所有public的成员class,interface,enum(包括从父类继承的)
	 * interface,原始类型,数组,void返回空数组
	 */
	public static void getPublicMemberClasses() {
		Class<?> concreteClass = ConcreteClass.class;
		Class<?>[] classes = concreteClass.getClasses();
		//prints [class com.journaldev.reflection.ConcreteClass$ConcreteClassPublicClass,
		//class com.journaldev.reflection.ConcreteClass$ConcreteClassPublicEnum,
		//interface com.journaldev.reflection.ConcreteClass$ConcreteClassPublicInterface,
		//class com.journaldev.reflection.BaseClass$BaseClassInnerClass,
		//class com.journaldev.reflection.BaseClass$BaseClassMemberEnum]
		System.out.println(Arrays.toString(classes));

		System.out.println(Arrays.toString(BaseInterface.class.getClasses())); //empty array
		System.out.println(Arrays.toString(boolean.class.getClasses())); //empty array
		System.out.println(Arrays.toString(String[][].class.getClasses())); //empty array
		System.out.println(Arrays.toString(void.class.getClasses())); //empty array
	}

	/**
	 * getDeclaredClasses(): 返回类声明的所有成员class,interface,enum不包括从父类继承的
	 */
	public static void getDeclaredClasses() {
		Class<?>[] explicitClasses = new Class<?>[0];
		try {
			explicitClasses = Class.forName("pomela.java.reflection.classes.ConcreteClass").getDeclaredClasses();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//prints [class com.journaldev.reflection.ConcreteClass$ConcreteClassDefaultClass,
		//class com.journaldev.reflection.ConcreteClass$ConcreteClassDefaultEnum,
		//class com.journaldev.reflection.ConcreteClass$ConcreteClassPrivateClass,
		//class com.journaldev.reflection.ConcreteClass$ConcreteClassProtectedClass,
		//class com.journaldev.reflection.ConcreteClass$ConcreteClassPublicClass,
		//class com.journaldev.reflection.ConcreteClass$ConcreteClassPublicEnum,
		//interface com.journaldev.reflection.ConcreteClass$ConcreteClassPublicInterface]
		System.out.println(Arrays.toString(explicitClasses));
	}

	/**
	 * getDeclaringClass(): 返回声明它的class
	 * getEnclosingClass(): 返回与它最接近的class,如果它没有父类返回null
	 */
	public static void getDeclaringClass() {
		Class<?> innerClass = null;
		try {
			innerClass = Class.forName("pomela.java.reflection.classes.ConcreteClass$ConcreteClassDefaultClass");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//prints pomela.java.reflection.classes.ConcreteClass
		System.out.println(innerClass.getDeclaringClass().getCanonicalName());
		System.out.println(innerClass.getEnclosingClass().getCanonicalName());
	}

	/**
	 * 获取包名
	 */
	public static void getPackageName() {
		Class<?> concreteClass = null;
		try {
			concreteClass = Class.forName("pomela.java.reflection.classes.ConcreteClass");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Package aPackage = concreteClass.getPackage();
		System.out.println(aPackage.getName());
	}

	public static void getClassModifiers() {
		Class<?> concreteClass = null;
		Class<?> baseInterfaceClass = null;
		try {
			concreteClass = Class.forName("pomela.java.reflection.classes.ConcreteClass");
			baseInterfaceClass = Class.forName("pomela.java.reflection.classes.BaseInterface");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		int modifiers = concreteClass.getModifiers();
		System.out.println(Modifier.toString(modifiers)); //prints public

		System.out.println(Modifier.toString(baseInterfaceClass.getModifiers())); //prints public abstract interface
	}

	public static void getTypeParameters() {
		//Get Type parameters (generics)
		TypeVariable<?>[] typeParameters = new TypeVariable<?>[0];
		try {
			typeParameters = Class.forName("java.util.HashMap").getTypeParameters();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		for(TypeVariable<?> t : typeParameters) {
			System.out.print(t.getName() + ",");
		}
		System.out.println();
		//prints K,V
	}

	/**
	 * getGenericInterfaces:获取该类实现的接口(包含泛型信息)
	 * getInterfaces:获取该类实现的接口
	 */
	public static void getImplementedInterfaces() {
		Class<?> aClass = null;
		try {
			aClass = Class.forName("java.util.HashMap");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Type[] genericInterfaces = aClass.getGenericInterfaces();
		//prints [java.util.Map<K, V>, interface java.lang.Cloneable, interface java.io.Serializable]
		System.out.println(Arrays.toString(genericInterfaces));

		Class<?>[] interfaces = aClass.getInterfaces();
		//prints [interface java.util.Map, interface java.lang.Cloneable, interface java.io.Serializable]
		System.out.println(Arrays.toString(interfaces));
	}

	/**
	 * getMethods:返回所有public的方法,包含父类和父接口中的
	 */
	public static void getAllPublicMethods() {
		Method[] publicMethods = new Method[0];
		try {
			publicMethods = Class.forName("pomela.java.reflection.classes.ConcreteClass").getMethods();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		//prints public methods of ConcreteClass, BaseClass, Object
		System.out.println(Arrays.toString(publicMethods));
	}

	/**
	 * getConstructors: 获取该类的所有的public的构造方法
	 */
	public static void getAllPublicConstructors() {
		//Get All public constructors
		Constructor<?>[] publicConstructors = new Constructor<?>[0];
		try {
			publicConstructors = Class.forName("pomela.java.reflection.classes.ConcreteClass").getConstructors();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//prints public constructors of ConcreteClass
		System.out.println(Arrays.toString(publicConstructors));
	}

	/**
	 * getFields:返回类的所有公共fields包含父类和父接口中公共fields
	 */
	public static void getAllPublicFields() {
		//Get All public fields
		Field[] publicFields = new Field[0];
		try {
			publicFields = Class.forName("pomela.java.reflection.classes.ConcreteClass").getFields();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//prints public fields of ConcreteClass, it's superclass and super interfaces
		System.out.println(Arrays.toString(publicFields));
	}

	/**
	 * 返回class,fields,methods的上的所有注解,反射只针对RetentionPolicy.RUNTIME的注解有效
	 */
	public static void getAllAnnotations() {
		Annotation[] annotations = new Annotation[0];
		try {
			annotations = Class.forName("pomela.java.reflection.classes.ConcreteClass").getAnnotations();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//prints [@java.lang.Deprecated()]
		System.out.println(Arrays.toString(annotations));
	}

	public static void main(String[] args) {
		getClassObject();
		getSuperClass();
		getPublicMemberClasses();
		getDeclaredClasses();
		getDeclaringClass();
		getPackageName();
		getClassModifiers();
		getTypeParameters();
		getImplementedInterfaces();
		getAllPublicMethods();
		getAllPublicConstructors();
		getAllPublicFields();
		getAllAnnotations();
	}
}
