package pomela.java.common.entities;

import java.util.List;

/**
 * Created by tao.he on 2015/10/13.
 */
public interface EntityMorker<T> {
	T mockOne();
	List<Order> mockList(int size);
}
