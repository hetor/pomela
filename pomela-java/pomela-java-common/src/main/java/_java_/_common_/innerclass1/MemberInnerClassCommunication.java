package _java_._common_.innerclass1;

/**
 * Copyright (c) 2014, All Rights Reserved. 
 *
 * ��Ա�ڲ�������Χ���ͨ��?
 *
 * @Author hetor, tao.he1989@gmail.com
 * @Date May 18, 2014 1:33:08 PM
 * @Since JDK1.7
 * @version 1.0.0
 */
public class MemberInnerClassCommunication {

    private String field0 = "outer field0";
    private String field1 = "outer field1";
    private static String sField = "MemberInnerClassCommunication sField";
    
    public void method0() {
        System.out.println("outer -> method0()");
    }

    public static void sMethod() {
        System.out.println("MemberInnerClassCommunication -> sMethod()");
    }
    
    private class MemberInnerClass {
        
        private String field0 = "memberInner field0";
        
        public void method0() {
            System.out.println("memberInner -> method0()");
        }
        
        public void func() {
            System.out.println("memberInner -> func()");
            //inner
            System.out.println(field0);
            method0();
            //outer
            System.out.println(MemberInnerClassCommunication.this.field0 + " , " + field1 + " , " + sField);
            MemberInnerClassCommunication.this.method0();
            sMethod();
        }
    }
    
    public static void main(String[] args) {
        MemberInnerClassCommunication outer = new MemberInnerClassCommunication();
        MemberInnerClassCommunication.MemberInnerClass inner = outer.new MemberInnerClass();
        inner.func();
    }
}
