package test.pomela.java.serialize.json;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import pomela.java.common.entities.EntityMockerFactory;
import pomela.java.common.entities.Order;
import pomela.java.common.entities.OrderMocker;
import pomela.java.common.utils.PrintUtil;
import pomela.java.serialize.json.GsonJsonUtil;

import com.google.gson.reflect.TypeToken;

/**
 * Created by tao.he on 2015/10/20.
 */
public class GsonJsonUtilTest {

	private OrderMocker mocker = EntityMockerFactory.getOrderMocker();

	@Test
	public void test_pojo() {
		Order order = mocker.mockOne();

		String s = GsonJsonUtil.toJson(order);
		PrintUtil.toConsole(s);

		Order order1 = GsonJsonUtil.fromJson(s, Order.class);
		PrintUtil.toConsole(order1);
	}

	@Test
	public void test_Generic() {
		/** array <T> **/
		Order[] orderArray = mocker.mockArray(2);
		TypeToken<Order[]> arrayTK = new TypeToken<Order[]>() {};
		String sArray = GsonJsonUtil.toJson(orderArray, arrayTK);
		PrintUtil.toConsole(sArray);

		Order[] orderArray1 = GsonJsonUtil.fromJson(sArray, arrayTK);
		PrintUtil.toConsole(orderArray1);


		/** List<T> Gson默认使用ArrayList **/
		List<Order> orderList = mocker.mockList(2);

		TypeToken<List<Order>> listTK = new TypeToken<List<Order>>() {};
		String sList = GsonJsonUtil.toJson(orderList, listTK);
		PrintUtil.toConsole(sList);

		List<Order> orderList1 = GsonJsonUtil.fromJson(sList, listTK);
		PrintUtil.toConsole(orderList1);


		/** Map<K, V> Gson默认使用LinkedTreeMap **/
		Map<String, Order> orderMap = mocker.mockMap(2);

		TypeToken<Map<String, Order>> mapTK = new TypeToken<Map<String, Order>>() {};
		String sMap = GsonJsonUtil.toJson(orderMap, mapTK);
		PrintUtil.toConsole(sMap);

		Map<String, Order> orderMap1 = GsonJsonUtil.fromJson(sMap, mapTK);
		PrintUtil.toConsole(orderMap1);
	}
}
