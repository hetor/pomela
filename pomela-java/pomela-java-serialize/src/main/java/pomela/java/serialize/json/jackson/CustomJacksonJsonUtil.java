package pomela.java.serialize.json.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Created by tao.he on 2015/10/17.
 */
public class CustomJacksonJsonUtil {

	private static final ObjectMapper customMapper = new ObjectMapper();

	static {
		SimpleModule module = new SimpleModule();

		module.addSerializer(new OrderSerializer());
		module.addSerializer(new ListOrderSerializer());
		module.addSerializer(new MapOrderSerializer());

		customMapper.registerModule(module);
	}

	public static String toJson(Object o) {
		try {
			return customMapper.writeValueAsString(o);
		} catch (Throwable th) {
			throw new RuntimeException(th);
		}
	}
}
