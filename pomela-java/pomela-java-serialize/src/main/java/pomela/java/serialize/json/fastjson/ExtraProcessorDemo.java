package pomela.java.serialize.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;
import pomela.java.common.utils.PrintUtil;
import pomela.java.serialize.json.FastJsonUtil;
import pomela.java.common.entities.CommRet;

import java.lang.reflect.Type;

/**
 * Created by hetor on 15/10/18.
 */
public class ExtraProcessorDemo {
    public static void main(String[] args) {
        CommRet commRet = FastJsonUtil.fromJson("{\"id\":123,\"name\":\"abc\"}", CommRet.class, new ExtraProcessor() {
            public void processExtra(Object object, String key, Object value) {
                CommRet vo = (CommRet) object;
                vo.getData().put(key, value);
            }
        });
        PrintUtil.toConsole(FastJsonUtil.toJson(commRet));

        //ExtraProcessor, ExtraTypeProvider
        ExtraProcessor processor = new MyExtraProcessor();
        CommRet vo1 = JSON.parseObject("{\"id\":123,\"value\":\"123456\"}", CommRet.class, processor);
    }
}

class MyExtraProcessor implements ExtraProcessor, ExtraTypeProvider {
    public void processExtra(Object object, String key, Object value) {
        CommRet vo = (CommRet) object;
        vo.getData().put(key, value);
    }

    public Type getExtraType(Object object, String key) {
        if ("value".equals(key)) {
            return int.class;
        }
        return null;
    }
}