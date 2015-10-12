package pomela.java.serialize.json;

import java.io.*;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * Created by tao.he on 2015/10/12.
 */
public class JacksonJsonUtil {
	private static final ObjectMapper objectMapper = new ObjectMapper();
	private static final JsonFactory jsonFactory = new JsonFactory();
	private static final ObjectReader objectReader = objectMapper.reader();
	private static final ObjectWriter objectWriter = objectMapper.writer();


	/**
	 * jsonString转 对象
	 *
	 * @param jsonString
	 *            json数据
	 * @param tClass
	 *            转义后的class
	 * @param <T>
	 *            class
	 * @return 列表
	 */
	public static <T> T fromJson(String jsonString, Class<T> tClass) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(jsonString, tClass);
		} catch (Throwable th) {
			throw new RuntimeException(th);
		}
	}

	/**
	 * jsonString转 对象
	 *
	 * @param jsonString
	 *            json数据
	 * @param tClass
	 *            转义后的class
	 * @param <T>
	 *            class
	 * @return 列表
	 */
	public static <T> T getObject(String jsonString, TypeReference<T> tClass) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(jsonString, tClass);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 从FileReader中的数据反序列化为对象
	 *
	 * @param fr        FileReader
	 * @param pojoClass 需要反序列化的类型
	 * @param <T>
	 * @return
	 */
	public static <T> T fromJson(FileReader fr, Class<T> pojoClass) {
		try {
			return objectMapper.readValue(fr, pojoClass);
		} catch (Throwable th) {
			throw new RuntimeException(th);
		}
	}

	/**
	 * jsonString转 list
	 *
	 * @param jsonString
	 *            json数据
	 * @param tClass
	 *            转义后的class
	 * @param <T>
	 *            class
	 * @return 列表
	 */
	public static <T> List<T> getList(String jsonString, final Class<T> tClass) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(jsonString, new TypeReference<List<T>>() {
				@Override
				public Type getType() {
					return new ParameterizedType() {
						@Override
						public Type[] getActualTypeArguments() {
							return new Type[]{tClass};
						}

						@Override
						public Type getRawType() {
							return List.class;
						}

						@Override
						public Type getOwnerType() {
							return null;
						}
					};
				}
			});
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 对象转json
	 *
	 * @param javaBean
	 *            对象
	 * @return json的字符串
	 */
	public static String getJsonString(Object javaBean) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(javaBean);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 将对象转化为json
	 *
	 * @param pojo        需要序列化的对象
	 * @param prettyPrint 是否打印优化，即换行
	 * @return
	 */
	public static String toJson(Object pojo, boolean prettyPrint) {
		StringWriter sw = new StringWriter();
		try {
			JsonGenerator jg = jsonFactory.createJsonGenerator(sw);
			if (prettyPrint) {
				jg.useDefaultPrettyPrinter();
			}
			objectMapper.writeValue(jg, pojo);
		} catch (Throwable th) {
			throw new RuntimeException(th);
		}
		return sw.toString();
	}

	/**
	 * 将对象序列化，并输出到FileWriter
	 *
	 * @param pojo        需要序列化的对象
	 * @param fw          需要输出的FileWriter
	 * @param prettyPrint 是否打印优化，即换行
	 */
	public static void toJson(Object pojo, FileWriter fw, boolean prettyPrint) {
		try {
			JsonGenerator jg = jsonFactory.createJsonGenerator(fw);
			if (prettyPrint) {
				jg.useDefaultPrettyPrinter();
			}
			objectMapper.writeValue(jg, pojo);
		} catch (Throwable th) {
			throw new RuntimeException(th);
		}
	}

	/**
	 * @param jsonString
	 * @return
	 */
	public static <K, V> HashMap<K, V> getMap(String jsonString) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(jsonString, HashMap.class);
		} catch (Exception e) {
		}
		return null;
	}

	/*
	 * 将jsonString转成map
	 */
	/*
	 * public static Map stringToMap(String jsonString) throws JSONException {
	 * JSONObject jsonObject = new JSONObject(jsonString); Map result = new
	 * HashMap(); Iterator iterator = jsonObject.keys(); String key = null;
	 * String value = null; while (iterator.hasNext()) { key = (String)
	 * iterator.next(); value = jsonObject.getString(key); result.put(key,
	 * value); } return result; }
	 */

	// public static String mapToJson(Map<String, Object> map) {
	// ObjectMapper objectMapper = new ObjectMapper();
	// String result = "";
	// JSON.toJSONString(result);
	// //objectMapper.writeValue(result, map);
	// }

	public static void writeEntity2Json(Object object) throws IOException {
		objectMapper.writeValue( new File("D:\\developSoft\\aaadowload\\testjson1\\lib\\aa.txt"),object );
		objectMapper.writeValue( System.out,object );

	}

	public static <T> List<T> fromJSON2List(String json, Class<T> clazz) {
		@SuppressWarnings("unchecked")
		List<LinkedHashMap<String, Object>> list = fromJson(json, List.class);
		List<T> retList = new ArrayList<T>();
		for (LinkedHashMap<String, Object> flightItem : list) {
			String str = toStr(flightItem);
			T item = fromJson(str, clazz);
			retList.add(item);
		}
		return retList;
	}

	private static String toStr(LinkedHashMap<String, Object> flightItem) {
		StringBuffer sb = new StringBuffer("{");
		Set<String> set = flightItem.keySet();
		for (String string : set) {
			sb.append("\"" + string + "\":");
			sb.append("\"" + flightItem.get(string) + "\",");
		}
		String str = sb.toString().substring(0, sb.length() - 1);
		str += "}";
		return str;
	}
}
