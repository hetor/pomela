package pomela.java.serialize.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.ParseProcess;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.lang.reflect.Type;

/**
 * Created by tao.he on 2015/10/12.
 */
public class FastJsonUtil {

    public static String toJson(Object o) {
        return JSON.toJSONString(o);
    }

    /**
     * 反序列化能够自动识别如下日期格式：
     * ISO-8601日期格式
     * yyyy-MM-dd
     * yyyy-MM-dd HH:mm:ss
     * yyyy-MM-dd HH:mm:ss.SSS
     * 毫秒数字
     * 毫秒数字字符串
     * .NET JSON日期格式
     * new Date(198293238)
     *
     * @param o
     * @param dateFormat
     * @return
     */
    public static String toJson(Object o, String dateFormat) {
        return JSON.toJSONStringWithDateFormat(o, dateFormat);
    }

    public static String toJson(Object o, SerializerFeature... features) {
        return JSON.toJSONString(o, features);
    }

    public static String toJson(Object o, SerializeFilter filter, SerializerFeature... features) {
        return JSON.toJSONString(o, filter, features);
    }

    public static String toJson(Object o, SerializeFilter[] filters, SerializerFeature... features) {
        return JSON.toJSONString(o, filters, features);
    }

    public static String toJson(Object o, SerializeConfig config, SerializerFeature... features) {
        return JSON.toJSONString(o, config, features);
    }

    public static String toJson(Object o, SerializeConfig config, SerializeFilter filter, SerializerFeature... features) {
        return JSON.toJSONString(o, config, filter, features);
    }

    public static String toJson(Object o, SerializeConfig config, SerializeFilter[] filters, SerializerFeature... features) {
        return JSON.toJSONString(o, config, filters, features);
    }





//    public static <T> T fromJson(String jsonStr, Class<T> clazz) {
//        return JSON.parseObject(jsonStr, clazz);
//    }

    public static <T> T fromJson(String jsonStr, Class<T> clazz, Feature... features) {
        return JSON.parseObject(jsonStr, clazz, features);
    }

    public static <T> T fromJson(String jsonStr, Class<T> clazz, ParseProcess pp) {
        return JSON.parseObject(jsonStr, clazz, pp);
    }

    public static <T> T fromJson(String jsonStr, Class<T> clazz, ParseProcess pp, Feature... features) {
        return JSON.parseObject(jsonStr, clazz, pp, features);
    }

    public static <T> T fromJson(String jsonStr, Type type, Feature... features) {
        return JSON.parseObject(jsonStr, type, features);
    }

    public static <T> T fromJson(String jsonStr, Type type, int featureValues, Feature... features) {
        return JSON.parseObject(jsonStr, type, featureValues, features);
    }

    public static <T> T fromJson(String jsonStr, Type type, ParseProcess pp, Feature... features) {
        return JSON.parseObject(jsonStr, type, pp, features);
    }

    public static <T> T fromJson(String jsonStr, Type type, ParserConfig config, int featureValues, Feature... features) {
        return JSON.parseObject(jsonStr, type, config, featureValues, features);
    }

    public static <T> T fromJson(String jsonStr, Type type, ParserConfig config, ParseProcess pp, int featureValues, Feature... features) {
        return JSON.parseObject(jsonStr, type, config, pp, featureValues, features);
    }

    public static <T> T fromJson(String jsonStr, TypeReference<T> tRef, Feature... features) {
        return JSON.parseObject(jsonStr, tRef, features);
    }

}
