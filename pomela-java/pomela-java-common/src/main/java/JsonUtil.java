package java;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by hetor on 15/10/11.
 */
public class JsonUtil {
    static ObjectMapper mapper = new ObjectMapper();

    public static String toJSON(Object obj) {
        StringWriter writer = new StringWriter();
        try {
            mapper.writeValue(writer, obj);
        } catch (JsonGenerationException e) {
            throw new RuntimeException(e);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return writer.toString();
    }

    public static <T> T fromJSON(String json, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, clazz);
        } catch (JsonParseException e) {
            throw new RuntimeException(e);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> fromJSON2List(String json, Class<T> clazz) {
        @SuppressWarnings("unchecked")
        List<LinkedHashMap<String, Object>> list = fromJSON(json, List.class);
        List<T> retList = new ArrayList<T>();
        for (LinkedHashMap<String, Object> flightItem : list) {
            String str = toStr(flightItem);
            T item = fromJSON(str, clazz);
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

