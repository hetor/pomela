package temp.netease;

import java.util.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * @author bjfyzhao
 *
 */
public class JsonUtilsJsonLib {
	/**
	 * 根据jsonStr返回Map数据，该Map不支持嵌套
	 * @param jsonStr
	 * @return
	 */
	public static Map getTopMapFromJsonStr(String jsonStr){
		JSONObject jsonObj = JSONObject.fromObject(jsonStr);
		Iterator keyIter = jsonObj.keys();
		
		Map<String,Object> keyValueMap = new HashMap<String,Object>();
		while(keyIter.hasNext()){
			String key = (String) keyIter.next();
			Object value = jsonObj.get(key);
			keyValueMap.put(key, value);
		}
		
		return keyValueMap;
	}
	
	
	/**
	 * 根据原始的json字符串得到对应的<key,value> Map对象
	 * @param jsonStr
	 * @return
	 */
	public static Map<String,Object> getMapFromJsonStr(String jsonStr){
		JSONObject jsonObj = JSONObject.fromObject(jsonStr);
		
		return (Map<String,Object>) JsonUtilsFastJson.parseJSONObj(jsonObj);
	}
	
	/**
	 * 根据原始的jsonStr得到JSONObject对象，供程序自行处理JSONObject对象
	 * @param jsonStr
	 * @return
	 */
	public static JSONObject getJSOnObject(String jsonStr){
		JSONObject jsonObj = JSONObject.fromObject(jsonStr);
		
		return jsonObj;
	}
	
	/**
	 * 根据Object生成json字符串
	 * @param object
	 * @return
	 */
	public static String getJsonStrFromObj(Object object){
		JSONObject jsonObj = JSONObject.fromObject(object);
		return jsonObj.toString();
	}
	
	/**
	 * 根据map直接生成json字符串
	 * @param map
	 * @return
	 */
	public static String getJsonStrFromMap(Map map){
		JSONObject jsonObj = JSONObject.fromObject(map);
		return jsonObj.toString();
	}
	
	/**
	 * 根据原始的json array字符串得到对应的List<Map<String,Object>>对象
	 * [{}, {}]
	 * @param jsonStr
	 * @return
	 */
	public static List<Map<String,Object>> getMapLstFromJsonStr(String jsonStr){
		JSONArray jsonArray = JSONArray.fromObject(jsonStr);
		
		return (List<Map<String,Object>>) JsonUtilsFastJson.parseJSONObj(jsonArray);
	}
	
	/**
	 * 递归地将一个JSONArray或JSONObject转换为Map对象
	 * @param obj
	 * @return
	 */
	private static Object parseJSONObj(Object obj){
		Object result = null;
		if (obj == null){
			//error
			return null;
		}
		else{
			if (obj instanceof JSONArray){
				JSONArray arrayObj = (JSONArray)obj;
				List<Object> list = new ArrayList<Object>();
				for (Object element : arrayObj.toArray()){
					list.add(JsonUtilsFastJson.parseJSONObj(element));
				}
				result = list;
			}
			else if (obj instanceof JSONObject){
				JSONObject jsonObj = (JSONObject)obj;
				Map<String, Object> map = new HashMap<String, Object>();
				for (Object key : jsonObj.keySet()){
					map.put(key.toString(), JsonUtilsFastJson.parseJSONObj(jsonObj.get(key.toString())));
				}
				return map;
			}else{
				result = obj;
			}
		}
		return result;
	}
}
