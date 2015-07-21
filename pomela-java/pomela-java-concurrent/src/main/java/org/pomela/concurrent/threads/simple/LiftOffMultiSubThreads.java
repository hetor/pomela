package org.pomela.concurrent.threads.simple;

import org.pomela.concurrent.runnables.LiftOff;

public class LiftOffMultiSubThreads {
    public static void main(String[] args) {
        for(int i=0; i<5; i++) {
            new Thread(new LiftOff()).start();
        }
        System.out.println("Waiting for LiftOff!");
    }
}
