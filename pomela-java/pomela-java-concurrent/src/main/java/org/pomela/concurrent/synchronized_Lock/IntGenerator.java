package org.pomela.concurrent.synchronized_Lock;

/**
 * @author hetao
 */
public abstract class IntGenerator {

    /**
     * 1.canceled标志是boolean类型的，所以它是原子的诸如赋值和返回值这样的简单操作在发生时没有中断的可能。
     * 2.加上volatile是为了保证可视性。
     */
    private volatile boolean canceled = false;
    public abstract int next();
    
    /**
     * Allow this to be canceled
     */
    public void cancel() {
        this.canceled = true;
    }
    
    public boolean isCanceled() {
        return canceled;
    }
}
