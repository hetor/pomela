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
 * ΪʲôҪʹ�ò��ɱ伯��
 * ���ɱ�����кܶ��ŵ㣬������

 * �����󱻲����ŵĿ����ʱ�����ɱ���ʽ�ǰ�ȫ�ģ�
 * ���ɱ���󱻶���̵߳���ʱ�������ھ�̬��������
 * ���ɱ伯�ϲ���Ҫ���Ǳ仯����˿��Խ�ʡʱ��Ϳռ䡣���в��ɱ�ļ��϶������ǵĿɱ���ʽ�и��õ��ڴ������ʣ������Ͳ���ϸ�ڣ���
 * ���ɱ������Ϊ�й̶����䣬������Ϊ��������ȫʹ�á�
 * ��������Ĳ��ɱ俽����һ��ܺõķ����Ա�̼��ɡ�GuavaΪ����JDK��׼�������ͺ�Guava�¼������Ͷ��ṩ�˼����õĲ��ɱ�汾��
 * JDKҲ�ṩ��Collections.unmodifiableXXX�����Ѽ��ϰ�װΪ���ɱ���ʽ����������Ϊ�����ã�

 * ���ض�����׸���������ʵ������������������Կ����ĳ�����
 * ����ȫ��Ҫ��֤û��ͨ��ԭ���ϵ����ý����޸ģ����صļ��ϲ�����ʵ�ϲ��ɱ�ģ�
 * ��Ч����װ���ļ�����Ȼ���пɱ伯�ϵĿ��������粢���޸ĵļ�顢ɢ�б�Ķ���ռ䣬�ȵȡ�
 * �����û���޸�ĳ�����ϵ����󣬻���ϣ��ĳ�����ϱ��ֲ���ʱ�����������Եؿ��������ɱ伯���Ǹ��ܺõ�ʵ����
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
		/** ��ȻORDERS�����Ķ����ܱ䣬���Ƕ����ڲ������ݻ��ǿ��Ա�� **/
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
