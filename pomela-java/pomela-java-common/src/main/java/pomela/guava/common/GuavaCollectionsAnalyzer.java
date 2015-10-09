package pomela.guava.common;

import com.google.common.base.Function;
import com.google.common.base.Supplier;
import com.google.common.collect.*;
import com.google.common.primitives.Ints;
import pomela.java.common.APIAnalyzer;

import java.util.*;

/**
 * Created by tao.he on 2015/10/8.
 */
public class GuavaCollectionsAnalyzer implements APIAnalyzer {
	@Override
	public void doAnalysis() {
		/** Collections2 **/

		/** Lists **/
		List<String> list = Lists.newArrayList();
		List<String> theseElements = Lists.newArrayList("alpha", "beta", "gamma");
		List<String> exactly100 = Lists.newArrayListWithCapacity(100);
		List<String> approx100 = Lists.newArrayListWithExpectedSize(100);

		/** Maps **/
		Map<String, String> map = Maps.newLinkedHashMap();

		ImmutableMap<Integer, String> stringsByIndex = Maps.uniqueIndex(theseElements,
				new Function<String, Integer>() {
					public Integer apply(String string) {
						return string.length();
					}
				});

		/** Sets **/
		Set<String> set = Sets.newHashSet();
		Set<String> copySet = Sets.newHashSet("alpha", "beta", "gamma");
		Set<String> approx100Set = Sets.newHashSetWithExpectedSize(100);

		/** Queues **/

		/** Multisets **/

		/** Multimaps **/
		Map<String, Integer> map2 = ImmutableMap.of("a", 1, "b", 1, "c", 2);
		// multimap��["a" => {1}, "b" => {1}, "c" => {2}]
		SetMultimap<String, Integer> multimap = Multimaps.forMap(map2);
		// inverse��[1 => {"a","b"}, 2 => {"c"}]
//		Multimap<Integer, String> inverse = Multimaps.invertFrom(multimap, HashMultimap<Integer, String>.create());

		ListMultimap<String, Integer> myMultimap = Multimaps.newListMultimap(
				Maps.<String, Collection>newTreeMap(),
				new Supplier<LinkedList>() {
					public LinkedList get() {
						return Lists.newLinkedList();
					}
				});


		/**
		 * Multiset
		 * Guava������¼�������û�б�¶ԭʼ��������Ҳû���ڹ��������ṩ��ʼ������������ֱ���ڼ��������ṩ�˾�̬��������
		 */
		Multiset<String> multiset = HashMultiset.create();

		/** Iterables **/
		Iterable<Integer> concatenated = Iterables.concat(
				Ints.asList(1, 2, 3),
				Ints.asList(4, 5, 6)); // concatenated����Ԫ�� 1, 2, 3, 4, 5, 6
		Integer lastAdded = Iterables.getLast(concatenated);
		Integer theElement = Iterables.getOnlyElement(concatenated);
		//���set���ǵ�Ԫ�ؼ����ͻ�����ˣ�

		/** Tables **/
		// ʹ��LinkedHashMaps���HashMaps
		Table<String, Character, Integer> table = Tables.newCustomTable(
		Maps.<String, Map<Character, Integer>>newLinkedHashMap(),
		new Supplier<Map<Character, Integer>>() {
			public Map<Character, Integer> get() {
				return Maps.newLinkedHashMap();
			}
		});

	}
}
