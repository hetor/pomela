package pomela.java.serialize.json.fastjson;

import com.alibaba.fastjson.serializer.NameFilter;

/**
 * Created by hetor on 15/10/18.
 *
 * 修改Key，如果需要修改Key,process返回值则可
 */
public class MyNameFilter implements NameFilter {
    @Override
    public String process(Object object, String name, Object value) {
        return null;
    }
}
