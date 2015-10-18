package pomela.java.serialize.json.fastjson;

import com.alibaba.fastjson.serializer.PropertyFilter;

/**
 * Created by hetor on 15/10/18.
 *
 * 根据PropertyName和PropertyValue来判断是否序列化
 */
public class MyPropertyFilter implements PropertyFilter {
    @Override
    public boolean apply(Object object, String name, Object value) {
        return false;
    }
}