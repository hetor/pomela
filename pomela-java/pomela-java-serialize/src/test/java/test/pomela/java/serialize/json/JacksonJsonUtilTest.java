package test.pomela.java.serialize.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.junit.Test;
import pomela.java.common.date.DateUtil;
import pomela.java.common.entities.EntityMockerFactory;
import pomela.java.common.entities.Order;
import pomela.java.common.entities.OrderMorker;
import pomela.java.common.utils.PrintUtil;
import pomela.java.serialize.json.JacksonJsonUtil;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tao.he on 2015/10/13.
 */
public class JacksonJsonUtilTest {

	private static final OrderMorker orderMorker = EntityMockerFactory.getOrderMorker();

	@Test
	public void test_pojoJson() {
		Order order = orderMorker.mockOne();
		String s = JacksonJsonUtil.toJson(order);
		System.out.println(s);

		Order order1 = JacksonJsonUtil.fromJson(s, Order.class);
		PrintUtil.print2Console(JacksonJsonUtil.toJson(order1, true));

		//自定义
		String s1 = JacksonJsonUtil.toJson(order, new JsonSerializer<Order>() {
			@Override
			public void serialize(Order value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
				gen.writeStartObject();
				gen.writeStringField("out_id", value.getOutId());
				gen.writeStringField("title_id", value.getUserId());
				gen.writeStringField("user_id", value.getUserId());
				gen.writeStringField("create_time", DateUtil.toStr(value.getCreateTime(), DateUtil.DatePattern.PATTERN_1));
				gen.writeStringField("update_time", value.getUserId());
				gen.writeEndObject();
			}
		});

		PrintUtil.print2Console(s1);

	}

	@Test
	public void test_ArrayJson() {
		Order[] orders = orderMorker.mockArray(2);

		String s = JacksonJsonUtil.toJson(orders, true);
		PrintUtil.print2Console(s);

		PrintUtil.print2Console(JacksonJsonUtil.fromJson(s, new TypeReference<Order[]>() {}));
		PrintUtil.print2Console(JacksonJsonUtil.fromJson(s, Order[].class));

		//more
		List[] a = new List[2];
		a[0] = orderMorker.mockList(2);
		a[1] = orderMorker.mockList(2);
		String s1 = JacksonJsonUtil.toJson(a, true);
		PrintUtil.print2Console(s1);
		PrintUtil.print2Console(JacksonJsonUtil.fromJson(s1, new TypeReference<List<Order>[]>() {
		}));

		//自定义
		String s2 = JacksonJsonUtil.toJson(orders, new JsonSerializer<Order[]>() {
			@Override
			public void serialize(Order[] value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
				gen.writeStartArray();
				for (Order o : value) {
					gen.writeStartObject();
					gen.writeStringField("out_id", o.getOutId());
					gen.writeStringField("title_id", o.getUserId());
					gen.writeStringField("user_id", o.getUserId());
					gen.writeStringField("create_time", DateUtil.toStr(o.getCreateTime(), DateUtil.DatePattern.PATTERN_1));
					gen.writeStringField("update_time", o.getUserId());
					gen.writeEndObject();
				}
				gen.writeEndArray();
			}
		});

		PrintUtil.print2Console(s2);
	}

	@Test
	public void test_ListJson() {
		List<Order> orders = orderMorker.mockList(2);

		String s = JacksonJsonUtil.toJson(orders);
		PrintUtil.print2Console(s);

		PrintUtil.print2Console(JacksonJsonUtil.fromJson(s, new TypeReference<List<Order>>() {}));
		PrintUtil.print2Console(JacksonJsonUtil.fromJson2List(s, Order.class));

		//自定义
		String s1 = JacksonJsonUtil.toJson(orders, new JsonSerializer<List<Order>>() {
			@Override
			public void serialize(List<Order> value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
				gen.writeStartArray();
				for (Order o : value) {
					gen.writeStartObject();
					gen.writeStringField("out_id", o.getOutId());
					gen.writeStringField("title_id", o.getUserId());
					gen.writeStringField("user_id", o.getUserId());
					gen.writeStringField("create_time", DateUtil.toStr(o.getCreateTime(), DateUtil.DatePattern.PATTERN_1));
					gen.writeStringField("update_time", o.getUserId());
					gen.writeEndObject();
				}
				gen.writeEndArray();
			}
		});

		PrintUtil.print2Console(s1);
	}

	@Test
	public void test_MapJson() {
		Map<String, Order> orderMap = orderMorker.mockMap(2);

		String s = JacksonJsonUtil.toJson(orderMap, true);
		PrintUtil.print2Console(s);

		PrintUtil.print2Console(JacksonJsonUtil.fromJson(s, new TypeReference<HashMap<String, Order>>() {}));
		PrintUtil.print2Console(JacksonJsonUtil.fromJson2Map(s, String.class, Order.class));

		//自定义
		String s1 = JacksonJsonUtil.toJson(orderMap, new JsonSerializer<Map<String, Order>>() {

			@Override
			public void serialize(Map<String, Order> value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
				gen.writeStartArray();
				for (Order o : value.values()) {
					gen.writeStartObject();
					gen.writeStringField("out_id", o.getOutId());
					gen.writeStringField("title_id", o.getUserId());
					gen.writeStringField("user_id", o.getUserId());
					gen.writeStringField("create_time", DateUtil.toStr(o.getCreateTime(), DateUtil.DatePattern.PATTERN_1));
					gen.writeStringField("update_time", o.getUserId());
					gen.writeEndObject();
				}
				gen.writeEndArray();
			}
		});

		PrintUtil.print2Console(s1);
	}

	@Test
	public void test_FileJson() throws IOException {
		File file = new File("C:\\Users\\tao.he\\Code\\json.txt");

		Order order = orderMorker.mockOne();
		JacksonJsonUtil.toJson(order, new FileWriter(file), true);

		Order order1 = JacksonJsonUtil.fromJson(new FileReader(file), Order.class);
		System.out.println(JacksonJsonUtil.toJson(order1));
	}
}
