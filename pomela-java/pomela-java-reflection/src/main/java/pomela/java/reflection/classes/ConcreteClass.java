package pomela.java.reflection.classes;

/**
 * Created by hetor on 16/6/22.
 */
@Deprecated
public class ConcreteClass extends BaseClass implements BaseInterface {
	public int publicInt;
	private String privateString = "private string";
	protected boolean protectedBoolean;
	Object defaultObject;
	public static Object o = new Object();

	public ConcreteClass(int i) {
		this.publicInt = i;
	}

	@Override
	public void method1() {
		System.out.println("method1 impl");
	}

	@Override
	public int method2(String str) {
		System.out.println("method2 impl");
		return 0;
	}

	@Override
	public int method4() {
		System.out.println("method4 overriden");
		return 0;
	}

	public int method5(int i) {
		System.out.println("ConcreteClass:method5");
		return 0;
	}

	//inner classes
	public class ConcreteClassPublicClass{
		public class A {

		}
	}
	private class ConcreteClassPrivateClass{}
	protected class ConcreteClassProtectedClass{}
	class ConcreteClassDefaultClass{}

	//member enum
	enum ConcreteClassDefaultEnum{}
	public enum ConcreteClassPublicEnum{}

	//member interface
	public interface ConcreteClassPublicInterface{}
}
