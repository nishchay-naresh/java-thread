package com.nishchay.thread.threadcommunication.prodcons.bq;

/*
 * Created two static methods corresponds to producer task & consumer task
 * then spawning two thread - prodThread, consThread using lambda exp
 *
 * */
public class SingleProdConsDriver {

    static final int LOOP_COUNT = 10;

    public static void main(String[] args) {

        final int BUFFER_SIZE = 4;
        MyBlockingQueue<String> queue = new MyBlockingQueue<>(BUFFER_SIZE);

        Thread prodThread1 = new Thread(() -> produceTask(queue), "Producer 1");
        Thread consThread = new Thread(() -> consumeTask(queue), "Consumer 1");

        consThread.start();
        prodThread1.start();

    }


    public static void produceTask(MyBlockingQueue<String> bq) {
        for (int i = 1; i <= LOOP_COUNT; i++) {
            String name = Thread.currentThread().getName();
            name = name + " - " + i;
            bq.put(name);
            System.out.println(Thread.currentThread().getName() + " produces: " + name);
        }
    }

    public static void consumeTask(MyBlockingQueue<String> bq) {
        for (int i = 1; i <= LOOP_COUNT; i++) {
            String data = bq.take();
            System.out.println(Thread.currentThread().getName() + " consumes: " + data);
        }
    }

}
