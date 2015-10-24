package pomela.java.common.entities;

import pomela.java.common.date.DateFormatUtils;
import pomela.java.common.date.DatePattern;
import pomela.java.common.utils.RandomUtil;

import java.util.*;

/**
 * Created by tao.he on 2015/10/13.
 */
public class OrderMocker implements EntityMocker<Order, String> {

	@Override
	public Order mockOne() {
		Date nowDate = new Date();
		long now = nowDate.getTime();

		Order order = new Order();
		order.setOutId(DateFormatUtils.toStr(nowDate, DatePattern.PATTERN_5) + RandomUtil.getNumbers(5, true));
		order.setCreateTime(nowDate);
		order.setUpdateTime(now);
		order.setTitle("title_" + RandomUtil.getNumbers(3, false));
		order.setUserId("U_" + UUID.randomUUID().toString());
		return order;
	}

	@Override
	public Order[] mockArray(int size) {
		Order[] orders = new Order[size];
		for(int i=0; i < size; i++) {
			orders[i] = mockOne();
		}
		return orders;
	}

	@Override
	public List<Order> mockList(int size) {
		List<Order> orders = new ArrayList<>(size);
		for(int i=0; i < size; i++) {
			orders.add(mockOne());
		}
		return orders;
	}

	@Override
	public Map<String, Order> mockMap(int size) {
		Map<String, Order> orderMap = new HashMap<>(size);
		for(int i=0; i < size; i++) {
			Order order = mockOne();
			orderMap.put(order.getUserId(), order);
		}
		return orderMap;
	}

}
