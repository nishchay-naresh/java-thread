package com.nishchay.concurrentpkg.synchronizers.synchronousq;

import java.util.concurrent.BlockingQueue;

public class QueueConsumer implements Runnable {

    private BlockingQueue<String> queue;

    public QueueConsumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            String event = queue.take();
            // thread will block here
            System.out.printf("[%s] consumed event : %s %n", Thread.currentThread().getName(), event);

/*            event = queue.take();
            // thread will block here
            System.out.printf("[%s] consumed event : %s %n", Thread.currentThread().getName(), event);*/
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}