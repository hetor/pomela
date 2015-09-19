package pomela.java.common.math;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Created by tao.he on 2015/9/19.
 */
public class BigDecimalAnalyzer {

	/**
	 * 数值格式化一：推荐
	 */
	public void printFormat() {
		DecimalFormat f0 = new DecimalFormat("0.00");
		f0.setRoundingMode(RoundingMode.HALF_UP);

		DecimalFormat f1 = new DecimalFormat("#.##");
		f1.setRoundingMode(RoundingMode.HALF_UP);

		BigDecimal d1 = new BigDecimal(0.001);
		System.out.println("pattern-0.0 : " + f0.format(d1));
		System.out.println("pattern-#.# : " + f1.format(d1));
	}

	/**
	 * 数值格式化二：陷阱
	 */
	public void notOneObject() {
		BigDecimal d1 = new BigDecimal("50.088990");
		BigDecimal d2 = d1.setScale(2, RoundingMode.HALF_UP);
		System.out.println("=== d2 is not d1 but a new Object ===");
		System.out.println(d1.toString());
		System.out.println(d2.toString());

		System.out.println("=========== best practice ===========");
		BigDecimal d3 = new BigDecimal("80.282808").setScale(3, BigDecimal.ROUND_HALF_UP);
		System.out.println(d3.floatValue());
	}


	public static void main(String[] args) {
		BigDecimalAnalyzer analyzer = new BigDecimalAnalyzer();

		System.out.println("================================================== notOneObject ==================================================");
		analyzer.notOneObject();
		System.out.println("================================================== printFormat ===================================================");
		analyzer.printFormat();
	}

}
