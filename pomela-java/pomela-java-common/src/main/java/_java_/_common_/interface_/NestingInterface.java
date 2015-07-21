/**
 * 
 */
package _java_._common_.interface_;

/**
 * @Description:
 * NestingInterface
 * @author hetao tao.he1989@gmail.com
 * @date 2013年12月3日下午8:56:45
 */

class A {
    interface IBB {
        void f();
    }
    public class BBImp implements IBB {
        @Override
        public void f() {}
    }
    @SuppressWarnings("unused")
    private class BBImp2 implements IBB {
        @Override
        public void f() {}
    }
    public interface ICC {
        void f();
    }
    class CCImp implements ICC {
        @Override
        public void f() {}
    }
    @SuppressWarnings("unused")
    private class CCImp2 implements ICC {
        @Override
        public void f() {}
    }
    private interface IDD {
        void f();
    }
    @SuppressWarnings("unused")
    private class DDImp implements IDD {
        @Override
        public void f() {}
    }
    public class DDImp2 implements IDD {
        @Override
        public void f() {}
    }
    public IDD getDD() {
        return new DDImp2();
    }
    private IDD dRef;
    public void reveiveDD(IDD d) {
        dRef = d;
        dRef.f();
    }
}

interface IEE {
    interface IGG {
        void f();
    }
    public interface IHH {
        void f();
    }
    void g();
    //cannot be private within an interface
    //private interface III{}
}

public class NestingInterface {
    public class BBImp implements A.IBB {
        public void f() {}
    }
    class CCImp implements A.ICC {
        public void f() {}
    }
    //cannot implement a private interface except, A.IDD is not visible
//    class DImp implements A.IDD {
//        public void f() {}
//    }
    /**Do not need to implements the nesting interfaces in IEE**/
    class EEImp implements IEE {
        public void g() {}
    }
    class EEGImp implements IEE.IGG {
        public void f() {}
    }
    class EEImp2 implements IEE {
        public void g() {}
        class EEG implements IEE.IGG {
            public void f() {}
        }
    }
    public static void main(String[] args) {
        A a = new A();
        
        //Can't access A.IDD
//        A.IDD ad = a.getDD();
        
        //Doesn't return anything but A.IDD
//        A.DDImp2 di2 = a.getDD();
        
        //Cannot access a member of the interface
//        a.getDD().f();
        
        //Only another A can do anything with getDD();
        A a2 = new A();
        a2.reveiveDD(a.getDD());
    }
}