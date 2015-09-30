package pomela.guava.common;

import java.util.Arrays;
import java.util.List;

import pomela.java.common.APIAnalyzer;
import pomela.java.common.enums.OrderState;

import com.google.common.base.Preconditions;

/**
 * Created by tao.he on 2015/9/30.
 *
 * 缺点：抛出的都是原生的RuntimeException，不能自定义Exception
 * 不是所有的检查参数不符合预期都要抛出异常
 */
public class GuavaPreconditionsAnalyzer implements APIAnalyzer {

	@Override
	public void doAnalysis() {

		/** Preconditions.checkArgument **/
		String name = "tao.he";
		Preconditions.checkArgument(isNameLegal(name), "'%s' is illegal", name);

		/**
		 * Preconditions.checkNotNull
		 * checkNotNull直接返回检查的参数，让你可以在构造函数中保持字段的单行赋值风格：this.field = checkNotNull(field)
		 */
		Object o = "123";
		Preconditions.checkNotNull(o, "param can not be null", o);

		/** Preconditions.checkState **/
		OrderState state1 = OrderState.INIT;
		OrderState state2 = OrderState.PAY_SUCC;
		Preconditions.checkState(state1.canChangeTo(state2), "%s can not change %s", state1, state2);

		/**
		 * Preconditions.checkElementIndex
		 * 索引值常用来查找列表、字符串或数组中的元素，如List.get(int), String.charAt(int)
		 */
		List<String> iList = Arrays.asList("a", "b");
		Preconditions.checkElementIndex(2, iList.size());

		/**
		 * Preconditions.checkPositionIndex 注意elementIndex和positionIndex的区别
		 * 位置值和位置范围常用来截取列表、字符串或数组，如List.subList(int，int), String.substring(int)
		 */
		String[] strs = {"a", "b"};
		Preconditions.checkPositionIndex(2, strs.length);

		String str1 = "position";
		Preconditions.checkPositionIndexes(1, 6, str1.length());
	}

	private boolean isNameLegal(String name) {
		return name.length() > 3;
	}

	public static void main(String[] args) {
		new GuavaPreconditionsAnalyzer().doAnalysis();
	}
}
