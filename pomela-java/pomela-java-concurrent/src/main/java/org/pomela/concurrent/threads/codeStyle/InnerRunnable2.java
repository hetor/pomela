package org.pomela.concurrent.threads.codeStyle;

/**
 * Created by hetor on 15/4/6.
 */
public class InnerRunnable2 {
    private Thread t;

    public InnerRunnable2(String name) {
        t = new Thread(new Runnable() {
            @Override
            public void run() {
                //do something
            }
        }, name);
        t.start();
    }
}
