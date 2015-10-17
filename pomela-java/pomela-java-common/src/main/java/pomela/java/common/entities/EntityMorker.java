package pomela.java.common.entities;

import java.util.List;
import java.util.Map;

/**
 * Created by tao.he on 2015/10/13.
 *
 * @param <T>
 *     entity class
 * @param <K>
 *     key class for map
 */
public interface EntityMorker<T, K> {
	T mockOne();
	T[] mockArray(int size);
	List<T> mockList(int size);
	Map<K, T> mockMap(int size);
}
