package org.pomela.concurrent.threads.codeStyle;

/**
 * Created by hetor on 15/4/6.
 * 使用内部类来将线程代码隐藏在类中
 */
public class InnerThread1 {

    Inner inner;

    public InnerThread1(String name) {
        inner = new Inner(name);
    }

    private class Inner extends Thread {
        Inner(String name) {
            super(name);
            start();
        }
        @Override
        public void run() {
            //do something
        }
    }

    //other code
}
