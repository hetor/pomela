package pomela.java.serialize.json.jackson;

/**
 * Created by tao.he on 2015/10/16.
 */
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CustomKeySerializerDemo
{
	public static void main(String[] args) throws Exception
	{
		Map<Date, String> myMap = new HashMap<Date, String>();
		myMap.put(new Date(), "now");
		Thread.sleep(100);
		myMap.put(new Date(), "later");

		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(myMap));

		SimpleModule module = new SimpleModule();
		module.addKeySerializer(Date.class, new DateAsTimestampSerializer());

		MapType myMapType = TypeFactory.defaultInstance().constructMapType(HashMap.class, Date.class, String.class);

		ObjectWriter writer = new ObjectMapper().registerModule(module).writerFor(myMapType);
		System.out.println(writer.writeValueAsString(myMap));
	}
}

class DateAsTimestampSerializer extends JsonSerializer<Date>
{
	@Override
	public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException
	{
		jgen.writeFieldName(String.valueOf(value.getTime()));
	}
}