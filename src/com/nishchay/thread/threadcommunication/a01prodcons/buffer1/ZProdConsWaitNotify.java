package com.nishchay.thread.threadcommunication.a01prodcons.buffer1;


public class ZProdConsWaitNotify {

    public static final int LIMIT = 10;

    public static void main(String[] args) {

        BufferSingle sharedObject = new BufferWaitNotifyImpl();
        new Thread(() -> produceTask(sharedObject), "Producer Thread").start();
        new Thread(() -> consumeTask(sharedObject), "Consumer Thread").start();
    }

    /*
     * instead of creating a new class for thread implementation, putting thread logic in a method
     * later invoking these methods thought threads created using lambdas
     * */
    public static void produceTask(BufferSingle buffer) {
        for (int i = 1; i <= LIMIT; i++) {
            buffer.produce(i);
            System.out.println(Thread.currentThread().getName() + " produces:" + i);
        }
    }

    public static void consumeTask(BufferSingle buffer) {
        int value;
        for (int i = 1; i <= LIMIT; i++) {
            value = buffer.consume();
            System.out.println(Thread.currentThread().getName() + " consume :" + value);
        }
    }
}