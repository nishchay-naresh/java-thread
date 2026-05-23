package com.nishchay.thread.threadcommunication.a01prodcons.buffern;


/*
 *
 * Solving a Producer–Consumer problem for n buffer size using semaphore
 * */
public class ZProdConsSemaphore {

    public static final int LIMIT = 10;

    public static void main(String[] args) {

        BufferSemaphoreImpl sharedObject = new BufferSemaphoreImpl();
        new Thread(() -> produceTask(sharedObject), "Producer Thread").start();
        new Thread(() -> consumeTask(sharedObject), "Consumer Thread").start();
    }

    /*
     * instead of creating a new class for thread implementation, putting thread logic in a method
     * later invoking these methods thought threads created using lambdas
     * */
    public static void produceTask(BufferSemaphoreImpl sharedObject) {
        for (int i = 1; i <= LIMIT; i++) {
            sharedObject.produce(i);
            System.out.println(Thread.currentThread().getName() + " produces:" + i);
        }
    }

    public static void consumeTask(BufferSemaphoreImpl sharedObject) {
        int value;
        for (int i = 1; i <= LIMIT; i++) {
            value = sharedObject.consume();
            System.out.println(Thread.currentThread().getName() + " consume :" + value);
        }
    }
}