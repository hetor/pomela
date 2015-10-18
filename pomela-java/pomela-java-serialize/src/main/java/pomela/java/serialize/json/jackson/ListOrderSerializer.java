package pomela.java.serialize.json.jackson;

import java.io.IOException;
import java.util.List;

import pomela.java.common.date.DateFormatUtils;
import pomela.java.common.date.DatePattern;
import pomela.java.common.entities.Order;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.TypeFactory;

/**
 * Created by tao.he on 2015/10/16.
 */
public class ListOrderSerializer extends StdSerializer<List<Order>> {

	private static final CollectionLikeType orderListType = TypeFactory.defaultInstance()
			.constructCollectionLikeType(List.class, Order.class);

	public ListOrderSerializer() {
		super(orderListType);
	}

	@Override
	public void serialize(List<Order>value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartArray();
		for (Order o : value) {
			gen.writeStartObject();
			gen.writeStringField("out_id", o.getOutId());
			gen.writeStringField("title_id", o.getUserId());
			gen.writeStringField("user_id", o.getUserId());
			gen.writeStringField("create_time", DateFormatUtils.toStr(o.getCreateTime(), DatePattern.PATTERN_1));
			gen.writeStringField("update_time", o.getUserId());
			gen.writeEndObject();
		}
		gen.writeEndArray();
	}
}
