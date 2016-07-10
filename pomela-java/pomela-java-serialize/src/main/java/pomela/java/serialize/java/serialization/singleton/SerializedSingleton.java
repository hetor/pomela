package pomela.java.serialize.java.serialization.singleton;

import java.io.Serializable;

/**
 * Created by hetor on 16/5/14.
 */
public class SerializedSingleton implements Serializable {

	private static final long serialVersionUID = -7604766932017737115L;

	private SerializedSingleton() {
	}

	private static class SingletonHelper {
		private static final SerializedSingleton instance = new SerializedSingleton();
	}

	public static SerializedSingleton getInstance() {
		return SingletonHelper.instance;
	}

	private Object readResolve() {
		return getInstance();
	}
}
