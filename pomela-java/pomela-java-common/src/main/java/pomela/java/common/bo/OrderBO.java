package pomela.java.common.bo;

import com.google.common.collect.ComparisonChain;
import pomela.java.common.entities.Order;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by tao.he on 2015/9/30.
 */
public class OrderBO {
	private Order innerOrder;

	public OrderBO(Order order) {
		this.innerOrder = checkNotNull(order, "Order can not be when new OrderBO");
	}

	public boolean createEarlyThan(Order order) {
		/** Fluent½Ó¿Ú **/
		return ComparisonChain.start()
			.compare(innerOrder.getCreateTime(), order.getCreateTime())
			.compare(innerOrder.getUpdateTime(), order.getUpdateTime())
			.result() < 0;
	}
}
