package _java_._common_.innerclass1;

/**
 * Copyright (c) 2014, All Rights Reserved. 
 *
 * �ֲ��ڲ��ࣨ�����е��ڲ��ࡢ�������е��ڲ��ࣩ����Χ���ͨ��?
 *
 * @Author hetor, tao.he1989@gmail.com
 * @Date May 18, 2014 8:14:12 PM
 * @Since JDK1.7
 * @version 1.0.0
 */
public class ScopeInnerClassCommunication {

    private String field0 = "outer field0";
    private String field1 = "outer field1";
    private static String sField = "ScopeInnerClassCommunication sField";

    public void method0() {
        System.out.println("outer -> method0()");
    }

    public static void sMethod() {
        System.out.println("ScopeInnerClassCommunication -> sMethod()");
    }

    public void func() {
        
        final int i = 0;
        
        if(true) {
            class ScopeInnerClass implements IA {
                
                private String field0 = "scopeInner field0";
                
                public void method0() {
                    System.out.println("scopeInner -> method0()");
                }
                
                @Override
                public void func() {
                    System.out.println("scopeInner -> func()");
                    //scopeInner
                    System.out.println(field0);
                    method0();
                    //scope
                    System.out.println(i);
                    //outer
                    System.out.println(ScopeInnerClassCommunication.this.field0 + " , " + field1 + " , " + sField);
                    ScopeInnerClassCommunication.this.method0();
                    sMethod();
                }
            }
            ScopeInnerClass scopeInner = new ScopeInnerClass();
            scopeInner.func();
        }
    }

    public static void main(String[] args) {
        ScopeInnerClassCommunication outer = new ScopeInnerClassCommunication();
        outer.func();
    }
}
