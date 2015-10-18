package pomela.java.serialize.json.fastjson;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyPreFilter;

/**
 * Created by hetor on 15/10/18.
 *
 * 根据PropertyName判断是否序列化
 *
 * 和PropertyFilter不同只根据object和name进行判断，在调用getter之前，这样避免了getter调用可能存在的异常。
 */
public class MyPropertyPreFilter implements PropertyPreFilter {
    @Override
    public boolean apply(JSONSerializer serializer, Object object, String name) {
        return false;
    }
}
