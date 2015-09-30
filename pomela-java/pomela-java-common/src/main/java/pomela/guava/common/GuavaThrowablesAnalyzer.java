package pomela.guava.common;

import com.google.common.base.Throwables;
import pomela.java.common.APIAnalyzer;

/**
 * Created by tao.he on 2015/9/30.
 */
public class GuavaThrowablesAnalyzer implements APIAnalyzer {

	@Override
	public void doAnalysis() {
		Throwable thr = new RuntimeException("ee");

//		Throwables.propagate(thr);

		try {
			Throwables.propagateIfInstanceOf(thr, Exception.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

//		Throwables.propagateIfPossible(thr);

		try {
			Throwables.propagateIfPossible(thr, Exception.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("causal: " + Throwables.getCausalChain(thr));

		System.out.println("root cause: " + Throwables.getRootCause(thr));

		System.out.println("stack trace: " + Throwables.getStackTraceAsString(thr));
	}

	public static void main(String[] args) {
		new GuavaThrowablesAnalyzer().doAnalysis();
	}
}
