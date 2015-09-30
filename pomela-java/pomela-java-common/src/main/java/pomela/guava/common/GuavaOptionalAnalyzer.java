package pomela.guava.common;

import pomela.java.common.APIAnalyzer;

import com.google.common.base.Optional;


/**
 * Created by tao.he on 2015/9/29.
 */
public class GuavaOptionalAnalyzer implements APIAnalyzer {

	@Override
	public void doAnalysis() {
		/** Optional.of() **/
		Optional<Integer> intOpt = Optional.of(5);
		boolean present = intOpt.isPresent();// returns true
		Integer integer = intOpt.get();// returns 5
		System.out.println(present + " " + integer);

//		Optional<Integer> possible = Optional.of(null); //NullPointerException

		/** Optional.absent() **/
		Optional<Object> absentOpt = Optional.absent();
		System.out.println(absentOpt.isPresent()); //false
//		System.out.println(absentOpt.get()); //IllegalStateException
		System.out.println(absentOpt.or("no reference"));
		System.out.println(absentOpt.orNull());
		System.out.println(absentOpt.asSet());

		/** Optional.fromNullable() **/
		Optional<Object> nullableOpt = Optional.fromNullable(null);
//		System.out.println(nullableOpt.get()); //IllegalStateException

		String nullObject = null;
		System.out.println(Optional.fromNullable(nullObject).or("default"));

		Optional<String> stringOptional = Optional.fromNullable("ht");
		System.out.println(stringOptional.get());

		/** Optional.presentInstances() **/
//		Optional.presentInstances();
	}


	public static void main(String[] args) {
		new GuavaOptionalAnalyzer().doAnalysis();
	}
}
