package pomela.java.serialize.json;

import java.io.*;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Created by tao.he on 2015/10/12.
 *
 * �޸�ʱ��С��ObjectMapper�Ĳ�����ȫ�����⣨���������õ�ʱ��ʹ��ObjectMapper��
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
	private static final ObjectMapper _ObjectMapper = new ObjectMapper();
//	private static final JsonFactory jsonFactory = new JsonFactory();

	/**
	 * ObjectReader and ObjectWriter instances are immutable and thread-safe:
	 * they are created by ObjectMapper, and once constructed, their state NEVER
	 * CHANGES. To get instance with different configuration, one uses factory
	 * methods to create NEW INSTANCES (existing one is not changed). This
	 * allows complete thread-safe use at any point in life cycle.
	 */
	private static final ObjectReader _ObjectReader = _ObjectMapper.reader();
	private static final ObjectWriter _ObjectWriter = _ObjectMapper.writer();


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
			return _ObjectReader.forType(pojoClass).readValue(jsonStr);
		} catch (Throwable th) {
			throw new RuntimeException(th);
		}
	}

	/**
	 * jsonStringת ����
	 *
	 * NOTE:ʹ������ӿڻᵼ��ҵ���߼���ֱ������Jackson��API
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
			return _ObjectReader.forType(typeRef).readValue(jsonStr);
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
	public static <T> T fromJson(FileReader fr, final Class<T> pojoClass) {
		try {
			return _ObjectReader.forType(pojoClass).readValue(fr);
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
	 * jsonStringתmap
	 *
	 * jacksonĬ��ʹ�õ���LinkedHashMap����֤˳��
	 *
	 * @param jsonStr
	 *            json����
	 * @param kClass
	 *            key��class
	 * @param vClass
	 *            value��class
	 * @param <K>
	 *            class
	 * @param <V>
	 *            class
	 * @return �б�
	 */
	public static <K, V> Map<K, V> fromJson2Map(String jsonStr, final Class<K> kClass, final Class<V> vClass) {
		return fromJson(jsonStr, new TypeReference<Map<K, V>>() {
			@Override
			public Type getType() {
				return new ParameterizedType() {
					@Override
					public Type[] getActualTypeArguments() {
						return new Type[]{kClass, vClass};
					}

					@Override
					public Type getRawType() {
						return Map.class;
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
	 *
	 * @param o
	 *            ��Ҫ���л��Ķ���
	 * @return
	 */
	public static String toJson(Object o) {
		return toJson(o, false);
	}

	/**
	 * ������ת��Ϊjson
	 *
	 * @param o
	 *            ��Ҫ���л��Ķ���
	 * @param prettyPrint
	 *            �Ƿ��ӡ�Ż���������
	 * @return
	 */
	public static String toJson(Object o, boolean prettyPrint) {
		try {
			if (prettyPrint) {
				return _ObjectWriter.withFeatures(JsonGenerator.Feature.IGNORE_UNKNOWN)
						.withDefaultPrettyPrinter()
						.writeValueAsString(o);
			} else {
				return _ObjectWriter.withFeatures(JsonGenerator.Feature.IGNORE_UNKNOWN)
						.writeValueAsString(o);
			}
		} catch (Throwable th) {
			throw new RuntimeException(th);
		}
	}

	/**
	 * ���������л����������FileWriter
	 *
	 * @param o
	 *            ��Ҫ���л��Ķ���
	 * @param fw
	 *            ��Ҫ�����FileWriter
	 * @param prettyPrint
	 *            �Ƿ��ӡ�Ż���������
	 */
	public static void toJson(Object o, FileWriter fw, boolean prettyPrint) {
		try {
			if (prettyPrint) {
				_ObjectWriter.withFeatures(JsonGenerator.Feature.IGNORE_UNKNOWN)
						.withDefaultPrettyPrinter()
						.writeValue(fw, o);
			} else {
				_ObjectWriter.withFeatures(JsonGenerator.Feature.IGNORE_UNKNOWN)
						.writeValue(fw, o);
			}
		} catch (Throwable th) {
			throw new RuntimeException(th);
		}
	}

	/**
	 * 自定义序列化
	 * @param o
	 * @param js
	 * @return
	 */
	public static String toJson(Object o, JsonSerializer js) {
		try {
			ObjectMapper om = new ObjectMapper();
			SimpleModule module = new SimpleModule();
			module.addSerializer(o.getClass(), js);
			om.registerModule(module);
			return om.writeValueAsString(o);
		} catch (Throwable th) {
			throw new RuntimeException(th);
		}
	}
}
