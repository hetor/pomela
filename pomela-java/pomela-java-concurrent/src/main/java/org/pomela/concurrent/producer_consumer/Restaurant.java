package org.pomela.concurrent.producer_consumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by hetor on 15/4/6.
 */
class Meal {
    private final int orderNum;
    public Meal(int orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {
        return "Meal " + orderNum;
    }
}

class Waiter implements Runnable {
    private Restaurant r;

    public Waiter(Restaurant r) {
        this.r = r;
    }

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()) {
                synchronized (this) {
                    while(r.meal == null) {
                        wait();
                    }
                }

                System.out.println("Waiter got " + r.meal);

                synchronized (r.chef) {
                    r.meal = null;
                    r.chef.notifyAll();
                }
            }
        }catch (InterruptedException e) {
            System.out.println("Waiter interrupted");
        }
    }
}

class Chef implements Runnable {

    int count = 0;
    private Restaurant r;

    public Chef(Restaurant r) {
        this.r = r;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while(r.meal != null) {
                        wait();
                    }
                }

                if(++count == 10) {
                    System.out.println("Out of food, closing");
                    r.exec.shutdownNow();
                }

                System.out.println("Order up! ");

                synchronized (r.waiter) {
                    r.meal = new Meal(count);
                    r.waiter.notifyAll();
                }
                TimeUnit.MILLISECONDS.sleep(100); //interrupt后，在试图调用sleep方法是抛出InterruptedException
            }
        } catch (InterruptedException e) {
            System.out.println("Chef interrupted");
        }
    }
}

public class Restaurant {
    Meal meal = null;
    ExecutorService exec = Executors.newCachedThreadPool();
    Chef chef = new Chef(this);
    Waiter waiter = new Waiter(this);

    public Restaurant() {
        exec.execute(chef);
        exec.execute(waiter);
    }

    public static void main(String[] args) {
        new Restaurant();
    }
}
