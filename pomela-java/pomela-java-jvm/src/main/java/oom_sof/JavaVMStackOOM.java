package oom_sof;

/**
 * Created by hetor on 15/4/13.
 * -Xss2M
 */
public class JavaVMStackOOM {
    private void dontStop() {
        while(true) {

        }
    }

    public void stackLeakByThread() {
        while(true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }
}
