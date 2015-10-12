/**
 * Copyright 2014-2015, NetEase, Inc. All Rights Reserved.
 * 
 * Date: May 5, 2015
 */
package temp.netease;

import static java.util.Objects.requireNonNull;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;

/**
 * Json工具类
 *
 * @author shenjiang<hzshenjiang@corp.netease.com>
 *
 */
public class JsonUtilsFastJson {
	
	private JsonUtilsFastJson() {
	}
	
	public static JSONObject addJsonField(Object object, String fieldName, Object fieldValue) {
		
		requireNonNull(fieldName);
		JSONObject json = null;
		if (object == null) {
			json = new JSONObject();
		} else {
			if (object instanceof JSONObject) {
				json = (JSONObject)object;
			} else {
				json = (JSONObject)JSON.toJSON(object);
			}
		}
		json.put(fieldName, fieldValue);
		
		return json;
	}
	
	/**
	 * 用于excel生成的json数据转换成list
	 *
	 * @param jsonBody
	 * @param elementClass
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> json2List(String jsonBody, Class<T> elementClass) {
		
		JSONObject object = JSON.parseObject(jsonBody);
			
		if (object == null) {
			return Collections.EMPTY_LIST; 
		}
		
		List<T> resultList = new ArrayList<>();
		for (Entry<String, Object> entry : object.entrySet()) {
			if (entry.getValue() instanceof JSONObject) {
				String elementJson = ((JSONObject)entry.getValue()).toJSONString();
				T element = JSON.parseObject(elementJson, elementClass);
				resultList.add(element);
			}
		}
		
		return resultList;
	}
	
	/**
	 * 
	 * @Description: json序列化对象
	 * @author hzhuangxiaojun
	 * @date 2015年6月8日 下午9:01:14 
	 * @param o
	 * @return
	 */
	public static String objectToJson(Object o, SerializerFeature... serializerFeatures) {
		return JSON.toJSONString(o, serializerFeatures);
	}
	
	
	/**
	 * 将Java数据对象转换成fastjson中的Json对象，同时支持过滤对象中的字段
	 *
	 * <p><tt>fastjson中的{@linkplain JSON#toJSON(Object)}方法不支持字段过滤</tt>
	 * @param javaObject Java对象
	 * @param filters  过滤器
	 * @return
	 * @see FieldFilter
	 */
	public static Object toJSON(Object javaObject, SerializeFilter... filters) {		
		
		if (javaObject == null) {
            return null;
        }

        if (javaObject instanceof JSON) {
            return (JSON) javaObject;
        }

        if (javaObject instanceof Map) {
            @SuppressWarnings("unchecked")
			Map<Object, Object> map = (Map<Object, Object>) javaObject;

            JSONObject json = new JSONObject(map.size());

            for (Entry<Object, Object> entry : map.entrySet()) {
                Object key = entry.getKey();
                String jsonKey = TypeUtils.castToString(key);
                Object jsonValue = toJSON(entry.getValue(), filters);
                json.put(jsonKey, jsonValue);
            }

            return json;
        }

        if (javaObject instanceof Collection) {
            @SuppressWarnings("unchecked")
			Collection<Object> collection = (Collection<Object>) javaObject;

            JSONArray array = new JSONArray(collection.size());

            for (Object item : collection) {
                Object jsonValue = toJSON(item, filters);
                array.add(jsonValue);
            }

            return array;
        }

        Class<?> clazz = javaObject.getClass();

        if (clazz.isEnum()) {
            return ((Enum<?>) javaObject).name();
        }

        if (clazz.isArray()) {
            int len = Array.getLength(javaObject);

            JSONArray array = new JSONArray(len);

            for (int i = 0; i < len; ++i) {
                Object item = Array.get(javaObject, i);
                Object jsonValue = toJSON(item, filters);
                array.add(jsonValue);
            }

            return array;
        }

        ParserConfig mapping = ParserConfig.getGlobalInstance();
        if (mapping.isPrimitive(clazz)) {
            return javaObject;
        }

        try {
            List<FieldInfo> getters = TypeUtils.computeGetters(clazz, null);

            JSONObject json = new JSONObject(getters.size());

            for (FieldInfo field : getters) {
            	
            	if (applyField(javaObject, filters, field)) {
	                Object value = field.get(javaObject);
	                Object jsonValue = toJSON(value, filters);
	
	                json.put(field.getName(), jsonValue);
            	}
            }

            return json;
        } catch (IllegalAccessException e) {
            throw new JSONException("toJSON error", e);
        } catch (InvocationTargetException e) {
            throw new JSONException("toJSON error", e);
        }
	}
        
	private static boolean applyField(Object value, SerializeFilter[] filters, FieldInfo field) {
		
		if (filters == null) {
			return true;
		}
		
    	for (SerializeFilter filter : filters) {
    		if (filter instanceof FieldFilter) {
    			FieldFilter fieldFilter = (FieldFilter)filter;
    			if (!fieldFilter.apply(value, field)) {
    				return false;
    			}
    		}
    	}
    	
    	return true;
	}
        
}
