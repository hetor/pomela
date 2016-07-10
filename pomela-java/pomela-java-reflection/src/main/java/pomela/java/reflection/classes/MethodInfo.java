package pomela.java.reflection.classes;

import java.lang.annotation.*;

/**
 * Created by hetor on 16/6/30.
 */
@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodInfo{
	String author() default "Pankaj";
	String date();
	int revision() default 1;
	String comments();
}

