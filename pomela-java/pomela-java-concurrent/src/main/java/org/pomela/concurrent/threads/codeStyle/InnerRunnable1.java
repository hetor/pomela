package org.pomela.concurrent.threads.codeStyle;

/**
 * Created by hetor on 15/4/6.
 */
public class InnerRunnable1 {

    private Thread t;
    public InnerRunnable1(String name) {
        t = new Thread(new Inner(), name);
        t.start();
    }

    private class Inner implements Runnable {

        @Override
        public void run() {
            //do something
        }
    }
}
