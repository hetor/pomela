package pomela.java.serialize.json.fastjson;

import com.alibaba.fastjson.serializer.AfterFilter;

/**
 * Created by hetor on 15/10/18.
 *
 * 序列化时在最后添加内容
 *
 * do something after serialize
 */
public class MyAfterFilter extends AfterFilter {
    @Override
    public void writeAfter(Object object) {

    }
}
