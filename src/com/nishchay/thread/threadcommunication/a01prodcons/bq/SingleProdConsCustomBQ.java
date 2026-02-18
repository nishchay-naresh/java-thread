package com.nishchay.thread.threadcommunication.a01prodcons.bq;

/*
 * Created two static methods corresponds to producer task & consumer task
 * then spawning two thread - prodThread, consThread using lambda exp
 *
 * */
public class SingleProdConsCustomBQ {

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
