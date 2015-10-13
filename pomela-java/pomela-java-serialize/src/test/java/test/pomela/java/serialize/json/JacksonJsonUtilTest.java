package test.pomela.java.serialize.json;

import org.junit.Test;
import pomela.java.common.entities.EntityMockerFactory;
import pomela.java.common.entities.Order;
import pomela.java.common.entities.OrderMorker;
import pomela.java.serialize.json.JacksonJsonUtil;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

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
		System.out.println(JacksonJsonUtil.toJson(order1, true));

	}

	@Test
	public void test_ListJson() {
		List<Order> orders = orderMorker.mockList(2);
		String s = JacksonJsonUtil.toJson(orders);
		System.out.println(s);

		List<Order> orders1 = JacksonJsonUtil.fromJson2List(s, Order.class);
		System.out.println(JacksonJsonUtil.toJson(orders1, true));
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
