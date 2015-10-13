package pomela.java.common.entities;

import pomela.java.common.date.DateUtil;
import pomela.java.common.utils.RandomUtil;

import java.util.*;

/**
 * Created by tao.he on 2015/10/13.
 */
public class OrderMorker implements EntityMorker<Order> {

	@Override
	public Order mockOne() {
		Date nowDate = new Date();
		long now = nowDate.getTime();

		Order order = new Order();
		order.setOutId(DateUtil.toStr(nowDate, DateUtil.DatePattern.PATTERN_5) + RandomUtil.getNumbers(5, true));
		order.setCreateTime(now);
		order.setUpdateTime(now);
		order.setTitle("title_" + RandomUtil.getNumbers(3, false));
		order.setUserId("U_" + UUID.randomUUID().toString());
		return order;
	}

	@Override
	public List<Order> mockList(int size) {
		List<Order> orders = new ArrayList<>(size);
		for(int i=0; i < size; i++) {
			orders.add(mockOne());
		}
		return orders;
	}
}
