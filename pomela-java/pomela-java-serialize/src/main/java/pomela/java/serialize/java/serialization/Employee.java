package pomela.java.serialize.java.serialization;

import java.io.Serializable;

/**
 * Created by hetor on 16/5/14.
 */
public class Employee implements Serializable {

	private static final long serialVersionUID = 6731030470151752973L;

	private String name;

	private int id;

	// Since salary is a transient variable, it’s value was not saved to file
	// and hence not retrieved in the new object.
	transient private int salary;

	private String password;

	// Similarly static variable values are also not serialized since they
	// belongs to class and not object.
	private static long count;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static long getCount() {
		return count;
	}

	public static void setCount(long count) {
		Employee.count = count;
	}

	@Override
	public String toString() {
		return "Employee{name=" + name + ",id=" + id + ",salary=" + salary + "}";
	}

}
