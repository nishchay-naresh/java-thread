package com.nishchay.thread.threadcommunication.prodcons.bq;

/**
 * Created two static methods corresponds to producer task & consumer task
 * then spawning two thread - prodThread, consThread using lambda exp
 *
 * */

public class Driver {

    public static void main(String[] args) {

        final int BUFFER_SIZE = 3;
        BlockingQueue<Integer> queue = new BlockingQueue<>(BUFFER_SIZE);

        Thread prodThread = new Thread(() -> produce(queue), "Producer Thread");
        prodThread.start();

        Thread consThread = new Thread(() -> consume(queue), "Consumer Thread");
        consThread.start();
    }


    public static void produce(BlockingQueue<Integer> bq) {
        for (int i = 1; i <= 10; i++) {
            bq.put(i);
            System.out.println(Thread.currentThread().getName() + " produces: " + i);
        }
    }

    public static void consume(BlockingQueue<Integer> bq) {
        Integer data;
        for (int i = 1; i <= 10; i++) {
            data = bq.take();
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