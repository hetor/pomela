package jsonserializer;

import com.google.gson.Gson;

public class GenericJsonSerializer implements IJsonSerializer {

	private static Gson gson = new Gson();

	private GenericJsonSerializer() {}

	private static GenericJsonSerializer instance = new GenericJsonSerializer();

	public static GenericJsonSerializer getInstance() {
		return instance;
	}

	@Override
	public String serialize(Object target) {
		return gson.toJson(target);
	}

	@Override
	public boolean support(Object target) {
		return target != null;
	}

}
