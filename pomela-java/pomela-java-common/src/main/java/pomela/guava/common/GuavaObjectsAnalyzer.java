package pomela.guava.common;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import pomela.java.common.APIAnalyzer;
import pomela.java.common.entities.Order;

/**
 * Created by tao.he on 2015/9/30.
 */
public class GuavaObjectsAnalyzer implements APIAnalyzer {
	@Override
	public void doAnalysis() {
		/** Objects.equal **/
		System.out.println(Objects.equal("a", "a")); // returns true
		System.out.println(Objects.equal(null, "a")); // returns false
		System.out.println(Objects.equal("a", null)); // returns false
		System.out.println(Objects.equal(null, null)); // returns true

		System.out.println(java.util.Objects.equals("a", "a")); // returns true
		System.out.println(java.util.Objects.equals(null, "a")); // returns false
		System.out.println(java.util.Objects.equals("a", null)); // returns false
		System.out.println(java.util.Objects.equals(null, null)); // returns true

		/** Objects.hashCode **/
		System.out.println(Objects.hashCode("username", "password"));

		System.out.println(java.util.Objects.hash("username", "password"));


		/** MoreObjects.toStringHelper toString **/
		System.out.println(MoreObjects.toStringHelper(this).add("x", 1).toString());
		// Returns "ClassName{x=1}"
		System.out.println(MoreObjects.toStringHelper("MyOrder:").add("order", new Order()).toString());
		// Returns "MyObject{x=1}"
	}

	public static void main(String[] args) {
		new GuavaObjectsAnalyzer().doAnalysis();
	}
}
