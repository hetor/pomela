/**
 * @Description:
 * 匿名内部类
 * @author hetao tao.he1989@gmail.com
 * @date 2013年12月4日下午7:37:02
 */
package _java_._common_.innerclass_;

public class AnonymousInnerClassTester {

    public static void main(String[] args) {
        OuterClass2 outerClass2 = new OuterClass2();
        outerClass2.getC(1, "ok", true).f();
    }
}

class OuterClass2 {
    
    private int i;
    
    C getC(int x, String s, final boolean key) {
        return new C(x, s) {
            {//do something initialization here
                OuterClass2.this.i++; //also has OuterClass.this
                System.out.println("also has OuterClass.this, can get the field i: " + i);
                if(key) {
                    System.out.println("匿名内部类中使用一个在外部定义的对象，那么编译器会要求其参数引用是final的。");
                }else {
                    System.out.println("匿名内部类没有命名的构造器，但通过实例初始化，能够达到为一个构造器的效果");
                }
            }
            @Override
            protected void f() {
                System.out.println("父类构造函数有参数");
            }
        };
    }
}

abstract class C {
    @SuppressWarnings("unused")
    private int x;
    @SuppressWarnings("unused")
    private String s;
    
    public C() {}
    
    public C(int x,  String s) {
        this.x = x;
        this.s = s;
    }
    
    protected abstract void f();
}