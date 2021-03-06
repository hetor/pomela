package test.pomela.java.serialize.json;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import pomela.java.common.date.DateFormatUtils;
import pomela.java.common.date.DatePattern;
import pomela.java.common.entities.EntityMockerFactory;
import pomela.java.common.entities.Order;
import pomela.java.common.entities.OrderMocker;
import pomela.java.common.utils.PrintUtil;
import pomela.java.serialize.json.JacksonJsonUtil;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Created by tao.he on 2015/10/13.
 */
public class JacksonJsonUtilTest {

	private static final OrderMocker orderMocker = EntityMockerFactory.getOrderMocker();

	@Test
	public void test_pojoJson() {
		Order order = orderMocker.mockOne();
		String s = JacksonJsonUtil.toJson(order);
		System.out.println(s);

		Order order1 = JacksonJsonUtil.fromJson(s, Order.class);
		PrintUtil.toConsole(JacksonJsonUtil.toJson(order1, true));

		//自定义
		String s1 = JacksonJsonUtil.toJson(order, new JsonSerializer<Order>() {
			@Override
			public void serialize(Order value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
				gen.writeStartObject();
				gen.writeStringField("out_id", value.getOutId());
				gen.writeStringField("title_id", value.getUserId());
				gen.writeStringField("user_id", value.getUserId());
				gen.writeStringField("create_time", DateFormatUtils.toStr(value.getCreateTime(), DatePattern.PATTERN_1));
				gen.writeStringField("update_time", value.getUserId());
				gen.writeEndObject();
			}
		});

		PrintUtil.toConsole(s1);

	}

	@Test
	public void test_ArrayJson() {
		Order[] orders = orderMocker.mockArray(2);

		String s = JacksonJsonUtil.toJson(orders, true);
		PrintUtil.toConsole(s);

		PrintUtil.toConsole(JacksonJsonUtil.fromJson(s, new TypeReference<Order[]>() {
		}));
		PrintUtil.toConsole(JacksonJsonUtil.fromJson(s, Order[].class));

		//more
		List[] a = new List[2];
		a[0] = orderMocker.mockList(2);
		a[1] = orderMocker.mockList(2);
		String s1 = JacksonJsonUtil.toJson(a, true);
		PrintUtil.toConsole(s1);
		PrintUtil.toConsole(JacksonJsonUtil.fromJson(s1, new TypeReference<List<Order>[]>() {
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
					gen.writeStringField("create_time", DateFormatUtils.toStr(o.getCreateTime(), DatePattern.PATTERN_1));
					gen.writeStringField("update_time", o.getUserId());
					gen.writeEndObject();
				}
				gen.writeEndArray();
			}
		});

		PrintUtil.toConsole(s2);
	}

	@Test
	public void test_ListJson() {
		List<Order> orders = orderMocker.mockList(2);

		String s = JacksonJsonUtil.toJson(orders);
		PrintUtil.toConsole(s);

		PrintUtil.toConsole(JacksonJsonUtil.fromJson(s, new TypeReference<List<Order>>() {
		}));
		PrintUtil.toConsole(JacksonJsonUtil.fromJson2List(s, Order.class));

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
					gen.writeStringField("create_time", DateFormatUtils.toStr(o.getCreateTime(), DatePattern.PATTERN_1));
					gen.writeStringField("update_time", o.getUserId());
					gen.writeEndObject();
				}
				gen.writeEndArray();
			}
		});
		PrintUtil.toConsole(s1);
	}

	@Test
	public void test_MapJson() {
		Map<String, Order> orderMap = orderMocker.mockMap(2);

		String s = JacksonJsonUtil.toJson(orderMap, true);
		PrintUtil.toConsole(s);

		PrintUtil.toConsole(JacksonJsonUtil.fromJson(s, new TypeReference<HashMap<String, Order>>() {
		}));
		PrintUtil.toConsole(JacksonJsonUtil.fromJson2Map(s, String.class, Order.class));

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
					gen.writeStringField("create_time", DateFormatUtils.toStr(o.getCreateTime(), DatePattern.PATTERN_1));
					gen.writeStringField("update_time", o.getUserId());
					gen.writeEndObject();
				}
				gen.writeEndArray();
			}
		});

		PrintUtil.toConsole(s1);
	}

	@Test
	public void test_FileJson() throws IOException {
		File file = new File("C:\\Users\\tao.he\\Code\\json.txt");

		Order order = orderMocker.mockOne();
		JacksonJsonUtil.toJson(order, new FileWriter(file), true);

		Order order1 = JacksonJsonUtil.fromJson(new FileReader(file), Order.class);
		System.out.println(JacksonJsonUtil.toJson(order1));
	}
}
