package oom_sof;

/**
 * Created by hetor on 15/4/13.
 *
 * -Xss256k
 * -Xoss256k
 *
 * -Xoss参数设置本地方法栈（HotSpot不区分虚拟机栈和本地方法栈，所以这个参数不起作用）
 * -Xss参数设置虚拟机栈
 */
public class JavaVMStackSOF {
    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF oom = new JavaVMStackSOF();

        try {
            oom.stackLeak();
        } catch (Throwable th) {
            System.out.println("stack length:" + oom.stackLength);
            throw th;
        }
    }
}
