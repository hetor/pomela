package pomela.java.serialize.java.serialization;

import java.io.*;

/**
 * Created by hetor on 16/5/14.
 * 
 * A simple class with generic serialize and deserialize method implementations
 */
public class SerializationUtil {

	// deserialize to Object from given file
	public static Object deserialize(String fileName) throws IOException, ClassNotFoundException {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(fileName);
			ois = new ObjectInputStream(fis);
			Object obj = ois.readObject();
			return obj;
		} finally {
			ois.close();
			fis.close();
		}
	}

	// serialize the given object and save it to file
	public static void serialize(Serializable obj, String fileName) throws IOException {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(fileName);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(obj);
		} finally {
			oos.close();
			fos.close();
		}
	}

}
