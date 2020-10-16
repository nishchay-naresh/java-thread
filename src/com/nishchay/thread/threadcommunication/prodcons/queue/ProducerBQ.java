package com.nishchay.thread.threadcommunication.prodcons.queue;

import java.util.concurrent.BlockingQueue;

public class ProducerBQ implements Runnable {

    private BlockingQueue<Integer> queue;

    public ProducerBQ(BlockingQueue<Integer> bq) {
        this.queue = bq;
    }

    public void run() {
        for (int i = 1; i <= 10; i++)
            try {
                System.out.println(Thread.currentThread().getName() + "\t Produce : " + i);
                queue.put(i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}
