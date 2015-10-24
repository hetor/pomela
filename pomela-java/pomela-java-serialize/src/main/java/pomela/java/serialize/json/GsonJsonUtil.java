package pomela.java.serialize.json;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import pomela.java.common.entities.Order;
import pomela.java.serialize.json.gson.AnnotationDeserializeExclusionStrategy;
import pomela.java.serialize.json.gson.OrderTypeAdapter;
import pomela.java.serialize.json.gson.AnnotationSerializeExclusionStrategy;
import pomela.java.serialize.json.gson.SpecificClassExclusionStrategy;

import java.lang.reflect.Type;
import java.text.DateFormat;

/**
 * Created by tao.he on 2015/10/12.
 */
public class GsonJsonUtil {

	private static final Gson gson = new Gson();

	private GsonJsonUtil() {}


	public static String toJson(Object o) {
		return gson.toJson(o);
	}

	public static String toJson(Object o, Type type) {
		return gson.toJson(o, type);
	}

	public static String toJson(Object o, TypeToken typeToken) {
		return toJson(o, typeToken.getType());
	}

	/**
	 * Gson API==>
	 * TypeAdapter: http://google.github.io/gson/apidocs/com/google/gson/TypeAdapter.html
	 * JsonSerializer<T>: New applications should prefer TypeAdapter, whose streaming API is more efficient than this interface's tree API.
	 * JsonDeserializer<T>: New applications should prefer TypeAdapter, whose streaming API is more efficient than this interface's tree API.
	 * InstanceCreator:
	 * @return
	 */
	public static String toJsonCustom(Object o) {
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(Order.class, new OrderTypeAdapter())
				.enableComplexMapKeySerialization()
				.serializeNulls()
				.setDateFormat(DateFormat.LONG)
				.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
				.setPrettyPrinting()
				.setVersion(1.0)
				.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES)
				.addSerializationExclusionStrategy(new AnnotationSerializeExclusionStrategy())
				.addDeserializationExclusionStrategy(new AnnotationDeserializeExclusionStrategy())
				.setExclusionStrategies(new SpecificClassExclusionStrategy(Order.class))
				.create();
		return gson.toJson(o);
	}


	public static <T> T fromJson(String jsonStr, Class<T> clazz) {
		return gson.fromJson(jsonStr, clazz);
	}

	public static <T> T fromJson(String jsonStr, Type type) {
		return gson.fromJson(jsonStr, type);
	}

	public static <T> T fromJson(String jsonStr, TypeToken<T> typeToken) {
		return fromJson(jsonStr, typeToken.getType());
	}

}
