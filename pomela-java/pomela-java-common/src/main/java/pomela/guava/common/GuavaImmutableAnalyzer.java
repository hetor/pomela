package pomela.guava.common;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import pomela.java.common.APIAnalyzer;
import pomela.java.common.entities.Order;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by tao.he on 2015/9/30.
 *
 * 为什么要使用不可变集合
 * 不可变对象有很多优点，包括：

 * 当对象被不可信的库调用时，不可变形式是安全的；
 * 不可变对象被多个线程调用时，不存在竞态条件问题
 * 不可变集合不需要考虑变化，因此可以节省时间和空间。所有不可变的集合都比它们的可变形式有更好的内存利用率（分析和测试细节）；
 * 不可变对象因为有固定不变，可以作为常量来安全使用。
 * 创建对象的不可变拷贝是一项很好的防御性编程技巧。Guava为所有JDK标准集合类型和Guava新集合类型都提供了简单易用的不可变版本。
 * JDK也提供了Collections.unmodifiableXXX方法把集合包装为不可变形式，但我们认为不够好：

 * 笨重而且累赘：不能舒适地用在所有想做防御性拷贝的场景；
 * 不安全：要保证没人通过原集合的引用进行修改，返回的集合才是事实上不可变的；
 * 低效：包装过的集合仍然保有可变集合的开销，比如并发修改的检查、散列表的额外空间，等等。
 * 如果你没有修改某个集合的需求，或者希望某个集合保持不变时，把它防御性地拷贝到不可变集合是个很好的实践。
 *
 * ImmutableCollection
 * ImmutableList
 * ImmutableSet
 * ImmutableSortedSet
 * ImmutableMap
 * ImmutableSortedMap
 * ImmutableMultiset
 * ImmutableSortedMultiset
 * ImmutableMultimap
 * ImmutableListMultimap
 * ImmutableSetMultimap
 * ImmutableBiMap
 * ImmutableClassToInstanceMap
 * ImmutableTable
 */
public class GuavaImmutableAnalyzer implements APIAnalyzer {

	public static final ImmutableSet<String> COLOR_NAMES = ImmutableSet.of(
			"red",
			"orange",
			"yellow",
			"green",
			"blue",
			"purple");

	public static final ImmutableSet<String> GOOGLE_COLORS =
			ImmutableSet.<String>builder()
					.addAll(Arrays.asList("RED", "YELLOW"))
					.add("GREEN")
					.build();

	public final ImmutableSet<Order> ORDERS;

	public GuavaImmutableAnalyzer(Set<Order> orders) {
		/** ImmutableSet.copyOf **/
		ORDERS = ImmutableSet.copyOf(orders);
	}

	@Override
	public void doAnalysis() {
		/** 虽然ORDERS包含的对象不能变，但是对象内部的数据还是可以变的 **/
		Order order = ORDERS.asList().get(0);
		order.setOutId("123");
		order.setUpdateTime(new Date().getTime());
		System.out.println(ORDERS.toString());

		/** asList **/
		ImmutableList<String> bars = COLOR_NAMES.asList();
	}

	public static void main(String[] args) {
		Set<Order> orders = new HashSet<>();
		Order order0 = new Order();
		order0.setOutId("1");
		Order order1 = new Order();
		order1.setOutId("2");
		Order order2 = new Order();
		order2.setOutId("3");
		orders.add(order0);
		orders.add(order1);
		orders.add(order2);

		new GuavaImmutableAnalyzer(orders).doAnalysis();
	}
}
