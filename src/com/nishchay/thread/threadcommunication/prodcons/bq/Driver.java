package com.nishchay.thread.threadcommunication.prodcons.bq;

/**
 * Created two static methods corresponds to producer task & consumer task
 * then spawning two thread - prodThread, consThread using lambda exp
 *
 * */

public class Driver {

    static final int PROD_LOOP_COUNT = 5;
    static final int CONS_LOOP_COUNT = 5 * 2;

    public static void main(String[] args) {

        final int BUFFER_SIZE = 4;
        BlockingQueue<String> queue = new BlockingQueue<>(BUFFER_SIZE);

        Thread prodThread1 = new Thread(() -> produceTask(queue), "Producer 1");
        Thread prodThread2 = new Thread(() -> produceTask(queue), "Producer 2");

        Thread consThread = new Thread(() -> consumeTask(queue), "Consumer 1");

        consThread.start();
        prodThread1.start();
        prodThread2.start();

    }


    public static void produceTask(BlockingQueue<String> bq) {
        for (int i = 1; i <= PROD_LOOP_COUNT; i++) {
            String name = Thread.currentThread().getName();
            name = name +" - "+ i;
            bq.put(name );
            System.out.println(Thread.currentThread().getName() + " produces: " + name);
        }
    }

    public static void consumeTask(BlockingQueue<String> bq) {
        for (int i = 1; i <= CONS_LOOP_COUNT; i++) {
            String data = bq.take();
            System.out.println(Thread.currentThread().getName() + " consumes: " + data);
        }
    }

}

/*
 *
 * O/P =>
 *    Producer Thread produces: 1
 *    Producer Thread produces: 2
 *    Producer Thread produces: 3
 *    Consumer Thread consumes: 1
 *    Consumer Thread consumes: 2
 *    Consumer Thread consumes: 3
 *    Producer Thread produces: 4
 *    Producer Thread produces: 5
 *    Producer Thread produces: 6
 *    Consumer Thread consumes: 4
 *    Consumer Thread consumes: 5
 *    Consumer Thread consumes: 6
 *    Producer Thread produces: 7
 *    Producer Thread produces: 8
 *    Producer Thread produces: 9
 *    Consumer Thread consumes: 7
 *    Consumer Thread consumes: 8
 *    Consumer Thread consumes: 9
 *    Producer Thread produces: 10
 *    Consumer Thread consumes: 10
 *
* */