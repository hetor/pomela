/**
 * @Description:
 * something about inner class
 * @author hetao tao.he1989@gmail.com
 * @date 2013年12月4日下午6:40:17
 */
package _java_._common_.innerclass_;

public class InnerClassTester {
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        
        OuterClass outerClass = new OuterClass();
        IA a = outerClass.getA();
        a.execute();

        OuterClass.C1 c1 = outerClass.new C1(); //.new
        OuterClass.C2 c2 = outerClass.new C2();
        OuterClass.C3 c3 = outerClass.new C3();
        
    }
}

class OuterClass {

    public class C1 {
//        public static int a = 0; 普通内部类不能有static数据、static方法、static内部类
        public OuterClass getOuter() {
            return OuterClass.this; //.this
        }
    }
    
    protected class C2 {}
    
    class C3 {}
    
    /**Hide implementation details**/
    private class C4 implements IA{
        @Override
        public void execute() {
            System.out.println("Hide implementation details");
        }
        
    }
    
    public IA getA() {
        return new C4();
    }
}

interface IA {
    void execute();
}