package pomela.java.serialize.java.serialization;


import java.io.Serializable;

/**
 * Created by hetor on 16/5/14.
 *
 * 此例为了说明Serializable序列化不需要变量的getter/setter方法也不需要无参构造函数
 */
public class Employee1 implements Serializable {

	private static final long serialVersionUID = 6731030470151752973L;

	public String name;

	public int id;

	// Since salary is a transient variable, it’s value was not saved to file
	// and hence not retrieved in the new object.
	transient public int salary;

	public String password;

	// Similarly static variable values are also not serialized since they
	// belongs to class and not object.
	public static long count;

	public Employee1(String key) {

	}

	@Override
	public String toString() {
		return "Employee{name=" + name + ",id=" + id + ",salary=" + salary + "}";
	}

}
