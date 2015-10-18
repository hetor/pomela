package pomela.java.serialize.json.fastjson;

import com.alibaba.fastjson.serializer.ValueFilter;

/**
 * Created by hetor on 15/10/18.
 *
 * 修改Value
 */
public class MyValueFilter implements ValueFilter {
    @Override
    public Object process(Object object, String name, Object value) {
        return null;
    }
}
