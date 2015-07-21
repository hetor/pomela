package org.pomela.concurrent.synchronized_Lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VolatileSerialNumberChecker implements Runnable{
    private static CircularSet serials = new CircularSet(1000);
    
    @Override
    public void run() {
        while(true) {
            int serialNumber = SerialNumberGenerator.nextSerialNumber();
            if(serials.contains(serialNumber)) {
                System.out.println("Duplicate: " + serialNumber);
                System.exit(0);
            }
            serials.add(serialNumber);
        }
    }
    
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i=0; i<10; i++) {
            exec.execute(new VolatileSerialNumberChecker());
        }
    }
}

class SerialNumberGenerator {
    private static volatile int serialNumber = 0;
    
    public static int nextSerialNumber() { //add synchronized
        return serialNumber++; // Not thread-safe
    }
}

class CircularSet {
    private int[] array;
    private int len;
    private int index = 0;
    
    public CircularSet(int size) {
        array = new int[size];
        len = size;
        //Initialize to a value not produced
        //by the VolatileSerialNumberGenerator
        for(int i=0; i<array.length; i++) {
            array[i] = -1;
        }
    }
    
    public synchronized void add(int i) {
        array[index] = i;
        //Wrap index and write over old elements
        index = ++index % len;
    }
    
    public synchronized boolean contains(int val) {
        for(int a : array)
            if(a == val) return true;
        return false;
    }
}