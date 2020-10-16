package com.nishchay.thread.threadcommunication.prodcons.queue;

import java.util.concurrent.BlockingQueue;

public class ConsumerBQ implements Runnable {

    private BlockingQueue<Integer> queue;

    public ConsumerBQ(BlockingQueue<Integer> bq) {
        this.queue = bq;
    }

    public void run() {
        for (int i = 1; i <= 10; i++)
            try {
                System.out.println(Thread.currentThread().getName() + "\t Consume : " + queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}
