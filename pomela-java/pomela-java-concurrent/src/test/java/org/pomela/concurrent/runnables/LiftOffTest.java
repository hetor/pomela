package org.pomela.concurrent.runnables;

/**
 * main函数所在的主线程循环调用run方法，执行完后主线程执行输出语句
 * @author hetao
 */
public class LiftOffTest {
    public static void main(String[] args) {
        LiftOff launch = new LiftOff();
        launch.run();
        System.out.println("LiftOffTest main!");
    }
}
