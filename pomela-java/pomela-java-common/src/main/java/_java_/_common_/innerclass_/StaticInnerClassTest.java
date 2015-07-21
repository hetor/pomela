package _java_._common_.innerclass_;

/**
 * Created by hetor on 15/4/22.
 */
public class StaticInnerClassTest {

    public static void main(String[] args) {
        OuterClass3.SInnerClass sInnerClass = new OuterClass3.SInnerClass();
        sInnerClass.f();
        OuterClass3.SInnerClass.sf();

        ISA.SInnerClass sInnerClass2 = new ISA.SInnerClass();
        sInnerClass2.f();
    }
}

class OuterClass3 {
    static class SInnerClass { //静态内部类可以包含static的数据、static方法、static内部类、和非static的数据、方法、内部类
        int a;
        void f() {}
        class C {}
        static int sa;
        static void sf() {}
        static class SC {}
    }
}

interface ISA {
    class SInnerClass{ //public static by default
        void f(){}
    }
    abstract class S{

    }
}
