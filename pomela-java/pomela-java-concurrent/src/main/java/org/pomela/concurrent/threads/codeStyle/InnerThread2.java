package org.pomela.concurrent.threads.codeStyle;

/**
 * Created by hetor on 15/4/6.
 */
public class InnerThread2 {
    private Thread t;
    public InnerThread2(String name) {
        t = new Thread(name) {
            @Override
            public void run() {
                //do something
            }
        };
        t.start();
    }
}
