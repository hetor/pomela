package pomela.guava.common;

import java.util.Date;

import pomela.java.common.APIAnalyzer;
import pomela.java.common.bo.OrderBO;
import pomela.java.common.entities.Order;

/**
 * Created by tao.he on 2015/9/30.
 */
public class GuavaComparisonChainAnalyzer implements APIAnalyzer {
	@Override
	public void doAnalysis() {
		Order order1 = new Order();
		order1.setCreateTime(new Date().getTime());

		Order order2 = new Order();
		order2.setCreateTime(new Date().getTime());

		OrderBO order1BO = new OrderBO(order1);
		/** ComparisonChain **/
		System.out.println(order1BO.createEarlyThan(order2));
	}

	public static void main(String[] args) {
		new GuavaComparisonChainAnalyzer().doAnalysis();
	}
}
