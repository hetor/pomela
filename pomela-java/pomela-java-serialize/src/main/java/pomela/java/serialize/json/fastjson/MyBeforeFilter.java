package pomela.java.serialize.json.fastjson;

import com.alibaba.fastjson.serializer.BeforeFilter;

/**
 * Created by hetor on 15/10/18.
 *
 * 序列化时在最前添加内容
 *
 * do something before serialize
 */
public class MyBeforeFilter extends BeforeFilter {

    @Override
    public void writeBefore(Object object) {

    }
}
