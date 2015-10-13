package pomela.java.common.entities;

/**
 * Created by tao.he on 2015/10/13.
 */
public class EntityMockerFactory {

	private static final OrderMorker orderMorker = new OrderMorker();

	public static OrderMorker getOrderMorker() {
		return orderMorker;
	}
}
