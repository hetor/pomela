package _java_._common_.innerclass1;

/**
 * Copyright (c) 2014, All Rights Reserved. 
 *
 * ��̬�ڲ�������Χ���ͨ��?
 *
 * @Author hetor, tao.he1989@gmail.com
 * @Date May 18, 2014 9:43:57 PM
 * @Since JDK1.7
 * @version 1.0.0
 */
public class StaticInnerClassCommunication {
    
    private String field = "field";
    private static String sField = "sField";
    
    public void method() {
        System.out.println("outer -> method()");
    }
    
    public static void sMethod() {
        System.out.println("StaticInnerClassCommunication -> sMethod()");
    }

    private static class StaticInnerClass {
        private String field = "field";
        private static String sField = "sField";
        
        public void func() {
            System.out.println(field);
            System.out.println(sField);
            System.out.println(StaticInnerClassCommunication.sField);
            StaticInnerClassCommunication.sMethod();
        }
    }
    
    public static void main(String[] args) {
        StaticInnerClass staticInner = new StaticInnerClass();
        staticInner.func();
    }
}
