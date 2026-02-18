package com.nishchay.thread.threadcommunication.a01prodcons.bq;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

/*
 * Created two static methods corresponds to producer task and consumer task
 * then spawning two thread - prodThread, consThread using lambda exp
 *
 * */
public class SingleProdConsBQ {

    static final int LOOP_COUNT = 10;

    public static void main(String[] args) {
//        singleProducerConsumer();
        manyProducerConsumer();
    }

    private static void singleProducerConsumer() {
        final int BUFF_SIZE = 5;
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(BUFF_SIZE);

        Thread tp = new Thread(() -> produceTask(queue), "Producer Thread");
        Thread tc = new Thread(() -> consumeTask(queue), "Consumer Thread");

        tp.start();
        tc.start();
    }

    private static void manyProducerConsumer() {
        final int BUFF_SIZE = 5;
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(BUFF_SIZE);

        new Thread(() -> produceTask(queue), "Producer Thread 1").start();
        new Thread(() -> produceTask(queue), "Producer Thread 2").start();
        new Thread(() -> consumeTask(queue), "Consumer Thread 1").start();
        new Thread(() -> consumeTask(queue), "Consumer Thread 2").start();

    }

    public static void produceTask(BlockingQueue<Integer> queue) {
        for (int i = 1; i <= LOOP_COUNT; i++) {
            try {
                queue.put(i);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " produces: " + i);
        }
    }

    public static void consumeTask(BlockingQueue<Integer> queue) {
        for (int i = 1; i <= LOOP_COUNT; i++) {
            Integer data = null;
            try {
                data = queue.take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " consumes: " + data);
        }
    }

}
