package pomela.java.reflection.classes;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by hetor on 16/6/30.
 */
public class ReflectionForAnnotations {
	public static void main(String[] args) {
		try {
			for (Method method : ReflectionForAnnotations.class.getClassLoader()
					.loadClass(("pomela.java.reflection.classes.ReflectionForAnnotations")).getMethods()) {
				// checks if MethodInfo annotation is present for the method
				if (method.isAnnotationPresent(pomela.java.reflection.classes.MethodInfo.class)) {
					try {
						// iterates all the annotations available in the method
						for (Annotation anno : method.getDeclaredAnnotations()) {
							System.out.println("Annotation in Method '" + method + "' : " + anno);
						}
						MethodInfo methodAnno = method.getAnnotation(MethodInfo.class);
						if (methodAnno.revision() == 1) {
							System.out.println("Method with revision no 1 = " + method);
						}

					} catch (Throwable ex) {
						ex.printStackTrace();
					}
				}
			}
		} catch (SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
