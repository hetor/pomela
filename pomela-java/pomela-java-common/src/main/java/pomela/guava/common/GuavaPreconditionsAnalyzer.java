package pomela.guava.common;

import java.util.Arrays;
import java.util.List;

import pomela.java.common.APIAnalyzer;
import pomela.java.common.enums.OrderState;

import com.google.common.base.Preconditions;

/**
 * Created by tao.he on 2015/9/30.
 *
 * ȱ�㣺�׳��Ķ���ԭ����RuntimeException�������Զ���Exception
 * �������еļ�����������Ԥ�ڶ�Ҫ�׳��쳣
 */
public class GuavaPreconditionsAnalyzer implements APIAnalyzer {

	@Override
	public void doAnalysis() {

		/** Preconditions.checkArgument **/
		String name = "tao.he";
		Preconditions.checkArgument(isNameLegal(name), "'%s' is illegal", name);

		/**
		 * Preconditions.checkNotNull
		 * checkNotNullֱ�ӷ��ؼ��Ĳ�������������ڹ��캯���б����ֶεĵ��и�ֵ���this.field = checkNotNull(field)
		 */
		Object o = "123";
		Preconditions.checkNotNull(o, "param can not be null", o);

		/** Preconditions.checkState **/
		OrderState state1 = OrderState.INIT;
		OrderState state2 = OrderState.PAY_SUCC;
		Preconditions.checkState(state1.canChangeTo(state2), "%s can not change %s", state1, state2);

		/**
		 * Preconditions.checkElementIndex
		 * ����ֵ�����������б��ַ����������е�Ԫ�أ���List.get(int), String.charAt(int)
		 */
		List<String> iList = Arrays.asList("a", "b");
		Preconditions.checkElementIndex(2, iList.size());

		/**
		 * Preconditions.checkPositionIndex ע��elementIndex��positionIndex������
		 * λ��ֵ��λ�÷�Χ��������ȡ�б��ַ��������飬��List.subList(int��int), String.substring(int)
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
