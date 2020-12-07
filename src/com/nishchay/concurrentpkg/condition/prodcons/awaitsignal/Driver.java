package com.nishchay.concurrentpkg.condition.prodcons.awaitsignal;


public class Driver {

    public static final int LIMIT = 5;

    public static void main(String[] args) {

        SharedObject sharedObject = new SharedObject(true);

        new Thread(() -> produce(sharedObject), "Producer Thread").start();
        new Thread(() -> consume(sharedObject), "Consumer Thread").start();

    }

    //    instead of creating a new class for thread implementation, putting thread logic in a method
    //    later invoking these method thought threads created using lambdas
    public static void produce(SharedObject sharedObject) {
        for (int i = 1; i <= LIMIT; i++) {
            sharedObject.produce(i);
            System.out.println(Thread.currentThread().getName() + " produces:" + i);
        }
    }

    public static void consume(SharedObject sharedObject) {
        int value;
        for (int i = 1; i <= LIMIT; i++) {
            value = sharedObject.consume();
            System.out.println(Thread.currentThread().getName() + " consume:" + value);
        }
    }

}
