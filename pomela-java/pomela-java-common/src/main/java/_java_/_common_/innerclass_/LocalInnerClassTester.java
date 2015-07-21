/**
 * @Description:
 * 局部内部类：
 * 
 * @author hetao tao.he1989@gmail.com
 * @date 2013年12月4日下午7:21:58
 */
package _java_._common_.innerclass_;

public class LocalInnerClassTester {

    public static void main(String[] args) {
        OuterClass1 outerClass1 = new OuterClass1();
        outerClass1.getIA().execute();
    }
}

class OuterClass1 {
    IA getIA() {
        class InnerClass implements IA { //局部内部类不能有访问说明符，public,protected,private
            @Override
            public void execute() {
                System.out.println("use local inner class in a method");
            }
        }
        return new InnerClass();
    }
    
    public IA f(boolean key) {
        if(key) {
            class InnerClass implements IA {
                @Override
                public void execute() {
                    System.out.println("use local inner class in a if block");
                }
            }
            return new InnerClass();
        }else {
            return new IA() {
                @Override
                public void execute() {
                    System.out.println("use anonymous inner class in a if block");
                }
            };
        }
    }
}
