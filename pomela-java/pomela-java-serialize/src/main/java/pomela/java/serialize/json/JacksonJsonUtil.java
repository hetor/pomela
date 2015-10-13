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

	/**
	 * ObjectMapper and JsonFactory
	 * The basic rule of Jackson thread-safety is that factories follow
	 * "thread-safe after configuration" philosophy. This means that:
	 *
	 * Configuring an instance is not synchronized or thread-safe, i.e. do not
	 * change settings while using it (which makes sense for other reasons too
	 * -- not all settings take effect once mapper has been in use, due to
	 * caching of serializers and deserializers) Once configuration is complete,
	 * operation is fully thread-safe and synchronized in few places where that
	 * is needed, for symbol table and buffer reuse.
	 *
	 * So as long as you first configure such factories from a single thread,
	 * and only then use it (from any number of threads), usage will be
	 * thread-safe without additional synchronization.
	 */
	private static final ObjectMapper objectMapper = new ObjectMapper();
	private static final JsonFactory jsonFactory = new JsonFactory();

	/**
	 * ObjectReader and ObjectWriter instances are immutable and thread-safe:
	 * they are created by ObjectMapper, and once constructed, their state NEVER
	 * CHANGES. To get instance with different configuration, one uses factory
	 * methods to create NEW INSTANCES (existing one is not changed). This
	 * allows complete thread-safe use at any point in life cycle.
	 */
	private static final ObjectReader objectReader = objectMapper.reader();
	private static final ObjectWriter objectWriter = objectMapper.writer();

	/**
	 * jsonStringת ����
	 *
	 * @param jsonStr
	 *            json����
	 * @param pojoClass
	 *            ת����class
	 * @param <T>
	 *            class
	 * @return �б�
	 */
	public static <T> T fromJson(String jsonStr, final Class<T> pojoClass) {
		try {
			return objectReader.forType(pojoClass).readValue(jsonStr);
		} catch (Throwable th) {
			throw new RuntimeException(th);
		}
	}

	/**
	 * jsonStringת ����
	 *
	 * @param jsonStr
	 *            json����
	 * @param typeRef
	 *            TypeReference
	 * @param <T>
	 *            class
	 * @return �б�
	 */
	public static <T> T fromJson(String jsonStr, TypeReference<T> typeRef) {
		try {
			return objectReader.forType(typeRef).readValue(jsonStr);
		} catch (Throwable th) {
			throw new RuntimeException(th);
		}
	}

	/**
	 * ��FileReader�е����ݷ����л�Ϊ����
	 *
	 * @param fr
	 *            FileReader
	 * @param pojoClass
	 *            ��Ҫ�����л�������
	 * @param <T>
	 * @return
	 */
	public static <T> T fromJson(FileReader fr, Class<T> pojoClass) {
		try {
			return objectReader.forType(pojoClass).readValue(fr);
		} catch (Throwable th) {
			throw new RuntimeException(th);
		}
	}

	/**
	 * jsonStringת list
	 *
	 * @param jsonStr
	 *            json����
	 * @param pojoClass
	 *            ת����class
	 * @param <T>
	 *            class
	 * @return �б�
	 */
	public static <T> List<T> fromJson2List(String jsonStr, final Class<T> pojoClass) {
		return fromJson(jsonStr, new TypeReference<List<T>>() {
			@Override
			public Type getType() {
				return new ParameterizedType() {
					@Override
					public Type[] getActualTypeArguments() {
						return new Type[]{pojoClass};
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
	}

	/**
	 * ����תjson
	 *
	 * @param javaBean
	 *            ����
	 * @return json���ַ���
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
	 * ������ת��Ϊjson
	 *
	 * @param pojo
	 *            ��Ҫ���л��Ķ���
	 * @param prettyPrint
	 *            �Ƿ��ӡ�Ż���������
	 * @return
	 */
	public static String toJson(Object pojo, boolean prettyPrint) {
		try {
			if (prettyPrint) {
				return objectWriter.withFeatures(JsonGenerator.Feature.IGNORE_UNKNOWN)
						.withDefaultPrettyPrinter()
						.writeValueAsString(pojo);
			} else {
				return objectWriter.withFeatures(JsonGenerator.Feature.IGNORE_UNKNOWN)
						.writeValueAsString(pojo);
			}
		} catch (Throwable th) {
			throw new RuntimeException(th);
		}
	}

	/**
	 *
	 * @param pojo
	 *            ��Ҫ���л��Ķ���
	 * @return
	 */
	public static String toJson(Object pojo) {
		return toJson(pojo, false);
	}

	/**
	 * ���������л����������FileWriter
	 *
	 * @param pojo
	 *            ��Ҫ���л��Ķ���
	 * @param fw
	 *            ��Ҫ�����FileWriter
	 * @param prettyPrint
	 *            �Ƿ��ӡ�Ż���������
	 */
	public static void toJson(Object pojo, FileWriter fw, boolean prettyPrint) {
		try {
			if (prettyPrint) {
				objectWriter.withFeatures(JsonGenerator.Feature.IGNORE_UNKNOWN)
						.withDefaultPrettyPrinter()
						.writeValue(fw, pojo);
			} else {
				objectWriter.withFeatures(JsonGenerator.Feature.IGNORE_UNKNOWN)
						.writeValue(fw, pojo);
			}
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
	 * ��jsonStringת��map
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
		objectMapper.writeValue(new File("D:\\developSoft\\aaadowload\\testjson1\\lib\\aa.txt"), object);
		objectMapper.writeValue(System.out, object);

	}

	// public static <T> List<T> fromJSON2List(String json, Class<T> clazz) {
	// @SuppressWarnings("unchecked")
	// List<LinkedHashMap<String, Object>> list = fromJson(json, List.class);
	// List<T> retList = new ArrayList<T>();
	// for (LinkedHashMap<String, Object> flightItem : list) {
	// String str = toStr(flightItem);
	// T item = fromJson(str, clazz);
	// retList.add(item);
	// }
	// return retList;
	// }

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
