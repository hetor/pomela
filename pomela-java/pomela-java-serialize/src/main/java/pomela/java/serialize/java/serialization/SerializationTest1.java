package pomela.java.serialize.java.serialization;

import java.io.IOException;

/**
 * Created by hetor on 16/5/14.
 */
public class SerializationTest1 {

	public static void main(String[] args) {
		String fileName = "employee1.ser";
//		Employee1 emp = new Employee1("a");
//		emp.id = 100;
//		emp.name = "Pankai";
//		emp.salary = 5000;
//
//		// serialize to file
//		try {
//			SerializationUtil.serialize(emp, fileName);
//		} catch (IOException e) {
//			e.printStackTrace();
//			return;
//		}

		Employee1 empNew = null;
		try {
			empNew = (Employee1) SerializationUtil.deserialize(fileName);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

//		System.out.println("emp Object::" + emp);
		System.out.println("empNew Object::" + empNew);
	}

}
