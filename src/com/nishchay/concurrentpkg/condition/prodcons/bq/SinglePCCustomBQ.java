package com.nishchay.concurrentpkg.condition.prodcons.bq;

public class SinglePCCustomBQ {

    static final int LOOP_COUNT = 10;

    public static void main(String[] args) {
        final int BUFFER_SIZE = 4;
        MyBlockingQ<String> queue = new MyBlockingQ<>(BUFFER_SIZE);

        Thread prodThread = new Thread(() -> produceTask(queue), "Producer Thread");
        Thread consThread = new Thread(() -> consumeTask(queue), "Consumer Thread");

        consThread.start();
        prodThread.start();
    }

    public static void produceTask(MyBlockingQ<String> bq){
        for (int i = 1; i <= LOOP_COUNT; i++) {
            String data = "hi - " + i;
            try {
                bq.put(data);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " produces: " + data);
        }
    }

    public static void consumeTask(MyBlockingQ<String> bq){
        for (int i = 1; i <= LOOP_COUNT; i++) {
            String data = null;
            try {
                data = bq.take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " consumes: " + data);
        }
    }

}