package _java_._common_.innerclass1;

/**
 * Copyright (c) 2014, All Rights Reserved. 
 *
 * �����ڲ�������Χ���ͨ��?
 *
 * @Author hetor, tao.he1989@gmail.com
 * @Date May 18, 2014 8:25:07 PM
 * @Since JDK1.7
 * @version 1.0.0
 */
public class AnonymousInnerClassCommunication {

    private String field0 = "outer field0";
    private String field1 = "outer field1";
    private static String sField = "AnonymousInnerClassCommunication sField";

    public void method0() {
        System.out.println("outer -> method0()");
    }

    public static void sMethod() {
        System.out.println("AnonymousInnerClassCommunication -> sMethod()");
    }
    
    public void func(final String p0) {
        IA anonyInner = new IA(){
            private String field0 = "anonyInner field0";
            
            public void method0() {
                System.out.println("anonyInner -> method0()");
            }
            
            @Override
            public void func() {
                System.out.println("anonyInner -> func()");
                //anonymousInner
                System.out.println(p0);
                System.out.println(field0);
                method0();
                //outer
                System.out.println(AnonymousInnerClassCommunication.this.field0 + " , " + field1 + " , " + sField);
                AnonymousInnerClassCommunication.this.method0();
                sMethod();
            }
        };
        anonyInner.func();
        
        AbstractClass abstractInner = new AbstractClass(100) { //���û����в���Ĺ��캯��?
            @Override
            public void func() {
                System.out.println(getI());
            }
        };
        abstractInner.func();
    }

    public static void main(String[] args) {
        AnonymousInnerClassCommunication outer = new AnonymousInnerClassCommunication();
        outer.func("hello");
    }
}
