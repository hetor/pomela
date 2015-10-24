package test.pomela.java.serialize.json;

import java.util.*;

import org.junit.Test;
import pomela.java.common.entities.EntityMockerFactory;
import pomela.java.common.entities.Order;
import pomela.java.common.entities.OrderMocker;
import pomela.java.common.utils.PrintUtil;
import pomela.java.serialize.json.jackson.CustomJacksonJsonUtil;

/**
 * Created by tao.he on 2015/10/15.
 */
public class JsonPerformanceTest {

	private static final OrderMocker orderMocker = EntityMockerFactory.getOrderMocker();
	private static final Order order = orderMocker.mockOne();
	private static final List<Order> orders = orderMocker.mockList(5);
	private static final Map<String, Order> orderMap = orderMocker.mockMap(5);


	@Test
	public void test_Jackson() throws InterruptedException {
		List<Thread> threads = createTaskThread(10, listSerializeTask());

		long start = System.currentTimeMillis();
		for (Thread thread : threads) {
			thread.start();
			thread.join();
		}

		long end = System.currentTimeMillis();

		PrintUtil.toConsole((end - start) + "ms");
	}



	private List<Thread> createTaskThread(int num, Runnable task) {
		List<Thread> threads = new ArrayList<>(num);
		for(int i=0; i<num; i++) {
			threads.add(new Thread(task));
		}
		return threads;
	}

	private Runnable listSerializeTask() {
		final int count = 10000000;
		final Set<String> result = new HashSet<>();

		return new Runnable() {
			@Override
			public void run() {
				for(int i=0; i<count; i++) {
					result.add(CustomJacksonJsonUtil.toJson(orders));
//					result.add(JacksonJsonUtil.toJson(orders, new JsonSerializer<List<Order>>() {
//						@Override
//						public void serialize(List<Order> value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
//							gen.writeStartArray();
//							for (Order o : value) {
//								gen.writeStartObject();
//								gen.writeStringField("out_id", o.getOutId());
//								gen.writeStringField("user_id", o.getUserId());
//								gen.writeEndObject();
//							}
//							gen.writeEndArray();
//						}
//					}));
				}
				PrintUtil.toConsole(result);
			}
		};
	}

}
