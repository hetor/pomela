package pomela.java.serialize.json.jackson;

import java.io.IOException;

import pomela.java.common.date.DateFormatUtils;
import pomela.java.common.date.DatePattern;
import pomela.java.common.entities.Order;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * Created by tao.he on 2015/10/16.
 */
public class OrderSerializer extends StdSerializer<Order> {

	public OrderSerializer() {
		super(Order.class);
	}

	@Override
	public void serialize(Order value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		gen.writeStringField("out_id", value.getOutId());
		gen.writeStringField("title_id", value.getUserId());
		gen.writeStringField("user_id", value.getUserId());
		gen.writeStringField("create_time", DateFormatUtils.toStr(value.getCreateTime(), DatePattern.PATTERN_1));
		gen.writeStringField("update_time", value.getUserId());
		gen.writeEndObject();
	}
}
