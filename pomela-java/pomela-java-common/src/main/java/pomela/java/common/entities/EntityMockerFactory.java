package pomela.java.common.entities;

/**
 * Created by tao.he on 2015/10/13.
 */
public class EntityMockerFactory {

	private static final OrderMocker orderMocker = new OrderMocker();

	public static OrderMocker getOrderMocker() {
		return orderMocker;
	}
}
