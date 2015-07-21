package _java_._common_.innerclass1;

/**
 * Copyright (c) 2014, All Rights Reserved.
 * 
 * �ֲ��ڲ��ࣨ�����е��ڲ��ࡢ�������е��ڲ��ࣩ����Χ���ͨ��?
 * 
 * @Author hetor, tao.he1989@gmail.com
 * @Date May 18, 2014 4:11:55 PM
 * @Since JDK1.7
 * @version 1.0.0
 */
public class MethodInnerClassCommunication {

    private String field0 = "outer field0";
    private String field1 = "outer field1";
    private static String sField = "MethodInnerClassCommunication sField";

    public void method0() {
        System.out.println("outer -> method0()");
    }

    public static void sMethod() {
        System.out.println("MethodInnerClassCommunication -> sMethod()");
    }

    public IA createMethodInnerClass() {
        
        final int i = 0;
        
        class MethodInnerClass implements IA {
            
            private String field0 = "methodInner field0";
            
            public void method0() {
                System.out.println("methodInner -> method0()");
            }
            
            @Override
            public void func() {
                System.out.println("methodInner -> func()");
                //methodInner
                System.out.println(field0);
                method0();
                //method
                System.out.println(i);
                //outer
                System.out.println(MethodInnerClassCommunication.this.field0 + " , " + field1 + " , " + sField);
                MethodInnerClassCommunication.this.method0();
                sMethod();
            }
        }
        return new MethodInnerClass();
    }

    public static void main(String[] args) {
        MethodInnerClassCommunication outer = new MethodInnerClassCommunication();
        IA local = outer.createMethodInnerClass();
        local.func();
    }
}
