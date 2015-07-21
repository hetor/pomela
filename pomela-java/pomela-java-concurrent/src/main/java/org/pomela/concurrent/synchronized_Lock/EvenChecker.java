package org.pomela.concurrent.synchronized_Lock;

/**
 * 偶数检验器
 * @author hetao
 */
public class EvenChecker implements Runnable {
    private IntGenerator generator;
    private final int id;
    
    public EvenChecker(IntGenerator g, int ident) {
        this.generator = g;
        this.id = ident;
    }

    @Override
    public void run() {
        while(!generator.isCanceled()) {
            int value = generator.next();
            if(value % 2 != 0) {
                System.out.println("Not Even!! Thread Id: " + id + " value: " + value);
                generator.cancel();//Cancels EvenChecker
            }
        }
    }
}
