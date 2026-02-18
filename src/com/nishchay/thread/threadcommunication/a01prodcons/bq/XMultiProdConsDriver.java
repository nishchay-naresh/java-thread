package com.nishchay.thread.threadcommunication.a01prodcons.bq;

/*
 * Created two static methods corresponds to producer task & consumer task
 * then spawning six thread(4 prod and 2 consumer) - prodThread1-4, consThread1-2 using lambda exp
 *
 * */
public class XMultiProdConsDriver {

    static final int PROD_LOOP_COUNT = 5;
    static final int CONS_LOOP_COUNT = 5 * 2;

    public static void main(String[] args) {

        final int BUFFER_SIZE = 3;
        MyBlockingQueue<String> queue = new MyBlockingQueue<>(BUFFER_SIZE);

        Thread prodThread1 = new Thread(() -> produceTask(queue, 100), "Producer 1");
        Thread prodThread2 = new Thread(() -> produceTask(queue, 200), "Producer 2");
        Thread prodThread3 = new Thread(() -> produceTask(queue, 300), "Producer 3");
        Thread prodThread4 = new Thread(() -> produceTask(queue, 400), "Producer 4");

        Thread consThread1 = new Thread(() -> consumeTask(queue), "Consumer 1");
        Thread consThread2 = new Thread(() -> consumeTask(queue), "Consumer 2");

        prodThread1.start();
        prodThread2.start();
        consThread1.start();
        prodThread3.start();
        prodThread4.start();
        consThread2.start();

    }


    public static void produceTask(MyBlockingQueue<String> bq, int startValue) {
        for (int i = 1; i <= PROD_LOOP_COUNT; i++) {
            String name = Thread.currentThread().getName();
            name = name + " - " + (startValue + i);
            bq.put(name);
            System.out.println(Thread.currentThread().getName() + " produces: " + name);
        }
    }

    public static void consumeTask(MyBlockingQueue<String> bq) {
        for (int i = 1; i <= CONS_LOOP_COUNT; i++) {
            String data = bq.take();
            System.out.println(Thread.currentThread().getName() + " consumes: " + data);
        }
    }

}

/*
 *
 * O/P =>
 *	Producer 1 produces: Producer 1 - 101
 *	Consumer 1 consumes: Producer 1 - 101
 *	Producer 2 produces: Producer 2 - 201
 *	Consumer 1 consumes: Producer 2 - 201
 *	Producer 2 produces: Producer 2 - 202
 *	Consumer 1 consumes: Producer 2 - 202
 *	Consumer 1 consumes: Producer 2 - 203
 *	Producer 2 produces: Producer 2 - 203
 *	Producer 2 produces: Producer 2 - 204
 *	Producer 2 produces: Producer 2 - 205
 *	Consumer 1 consumes: Producer 2 - 204
 *	Producer 1 produces: Producer 1 - 102
 *	Consumer 1 consumes: Producer 2 - 205
 *	Producer 1 produces: Producer 1 - 103
 *	Producer 1 produces: Producer 1 - 104
 *	Producer 1 produces: Producer 1 - 105
 *	Consumer 1 consumes: Producer 1 - 102
 *	Consumer 1 consumes: Producer 1 - 103
 *	Consumer 1 consumes: Producer 1 - 104
 *	Consumer 1 consumes: Producer 1 - 105
 *
 *  -----------------------------------------------
 *
 *	Producer 1 produces: Producer 1 - 101
 *	Producer 1 produces: Producer 1 - 102
 *	Producer 1 produces: Producer 1 - 103
 *	Consumer 1 consumes: Producer 1 - 101
 *	Producer 2 produces: Producer 2 - 201
 *	Producer 1 produces: Producer 1 - 104
 *	Consumer 1 consumes: Producer 1 - 102
 *	Consumer 1 consumes: Producer 1 - 103
 *	Producer 3 produces: Producer 3 - 301
 *	Producer 4 produces: Producer 4 - 401
 *	Consumer 1 consumes: Producer 2 - 201
 *	Consumer 1 consumes: Producer 1 - 104
 *	Consumer 1 consumes: Producer 3 - 301
 *	Producer 1 produces: Producer 1 - 105
 *	Producer 4 produces: Producer 4 - 402
 *	Producer 2 produces: Producer 2 - 202
 *	Consumer 1 consumes: Producer 4 - 401
 *	Consumer 1 consumes: Producer 1 - 105
 *	Consumer 1 consumes: Producer 2 - 202
 *	Consumer 2 consumes: Producer 4 - 402
 *	Producer 3 produces: Producer 3 - 302
 *	Producer 4 produces: Producer 4 - 403
 *	Producer 4 produces: Producer 4 - 404
 *	Producer 2 produces: Producer 2 - 203
 *	Producer 3 produces: Producer 3 - 303
 *	Consumer 1 consumes: Producer 4 - 403
 *	Consumer 2 consumes: Producer 2 - 203
 *	Consumer 2 consumes: Producer 3 - 302
 *	Producer 3 produces: Producer 3 - 304
 *	Producer 2 produces: Producer 2 - 204
 *	Consumer 2 consumes: Producer 3 - 303
 *	Consumer 2 consumes: Producer 4 - 404
 *	Consumer 2 consumes: Producer 3 - 304
 *	Consumer 2 consumes: Producer 2 - 204
 *	Consumer 2 consumes: Producer 2 - 205
 *	Producer 2 produces: Producer 2 - 205
 *	Producer 3 produces: Producer 3 - 305
 *	Consumer 2 consumes: Producer 3 - 305
 *	Producer 4 produces: Producer 4 - 405
 *	Consumer 2 consumes: Producer 4 - 405
 * */