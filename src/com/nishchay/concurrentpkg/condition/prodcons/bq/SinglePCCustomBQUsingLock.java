package com.nishchay.concurrentpkg.condition.prodcons.bq;

public class SinglePCCustomBQUsingLock {

    static final int LOOP_COUNT = 10;

    public static void main(String[] args) {
        final int BUFFER_SIZE = 4;
        MyBlockingQueue<String> queue = new MyBlockingQueue<>(BUFFER_SIZE);

        Thread prodThread = new Thread(() -> produceTask(queue), "Producer Thread");
        Thread consThread = new Thread(() -> consumeTask(queue), "Consumer Thread");

        consThread.start();
        prodThread.start();
    }

    public static void produceTask(MyBlockingQueue<String> bq) {
        for (int i = 1; i <= LOOP_COUNT; i++) {
            String data = "hi - " + i;
            bq.put(data);
            System.out.println(Thread.currentThread().getName() + " produces: " + data);
        }
    }

    public static void consumeTask(MyBlockingQueue<String> bq) {
        for (int i = 1; i <= LOOP_COUNT; i++) {
            String data = bq.take();
            System.out.println(Thread.currentThread().getName() + " consumes: " + data);
        }
    }

}