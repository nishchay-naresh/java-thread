package com.nishchay.thread.threadcommunication.a01prodcons.bq;

/*
 * Created two static methods corresponds to producer task & consumer task
 * then spawning four thread(2 prod and 2 consumer) - prodThread1, prodThread2, consThread1, consThread2 using lambda exp
 *
 * */
public class TwoProdConsDriver {

    static final int LOOP_COUNT = 5;

    public static void main(String[] args) {

        final int BUFFER_SIZE = 3;
        MyBlockingQueue<String> queue = new MyBlockingQueue<>(BUFFER_SIZE);

        Thread prodThread1 = new Thread(() -> produceTask(queue, 100), "Producer 1");
        Thread prodThread2 = new Thread(() -> produceTask(queue, 200), "Producer 2");

        Thread consThread1 = new Thread(() -> consumeTask(queue), "Consumer 1");
        Thread consThread2 = new Thread(() -> consumeTask(queue), "Consumer 2");

        prodThread1.start();
        prodThread2.start();
        consThread1.start();
        consThread2.start();
    }


    public static void produceTask(MyBlockingQueue<String> bq, int startValue) {
        for (int i = 1; i <= LOOP_COUNT; i++) {
            String name = Thread.currentThread().getName();
            name = name + " - " + (startValue + i);
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

/*
 *
 * O/P =>
 *	Producer 1 produces: Producer 1 - 101
 *	Producer 1 produces: Producer 1 - 102
 *	Producer 1 produces: Producer 1 - 103
 *	Consumer 1 consumes: Producer 1 - 101
 *	Producer 1 produces: Producer 1 - 104
 *	Consumer 2 consumes: Producer 1 - 102
 *	Producer 2 produces: Producer 2 - 201
 *	Consumer 2 consumes: Producer 2 - 201
 *	Producer 1 produces: Producer 1 - 105
 *	Consumer 1 consumes: Producer 1 - 103
 *	Consumer 1 consumes: Producer 1 - 105
 *	Consumer 1 consumes: Producer 2 - 202
 *	Consumer 2 consumes: Producer 1 - 104
 *	Producer 2 produces: Producer 2 - 202
 *	Producer 2 produces: Producer 2 - 203
 *	Consumer 2 consumes: Producer 2 - 203
 *	Consumer 1 consumes: Producer 2 - 204
 *	Producer 2 produces: Producer 2 - 204
 *	Producer 2 produces: Producer 2 - 205
 *	Consumer 2 consumes: Producer 2 - 205
 *
 * */