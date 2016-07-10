package pomela.java.serialize.java.serialization.proxy;

import pomela.java.serialize.java.serialization.SerializationUtil;

import java.io.IOException;

/**
 * Created by hetor on 16/5/14.
 */
public class SerializationProxyTest {

	public static void main(String[] args) {
		String fileName = "data.ser";

		Data data = new Data("Pankaj");

		try {
			SerializationUtil.serialize(data, fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			Data newData = (Data) SerializationUtil.deserialize(fileName);
			System.out.println(newData);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

}