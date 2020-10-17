package com.nishchay.concurrentpkg.synchronizers.synchronousq;

import java.util.concurrent.BlockingQueue;

public class QueueProducer implements Runnable {

    private BlockingQueue<String> queue;

    public QueueProducer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        String event = "SYNCHRONOUS_EVENT";
        String another_event = "ANOTHER_EVENT";
        try {
            queue.put(event);
            System.out.printf("[%s] published event : %s %n", Thread
                    .currentThread().getName(), event);

            queue.put(another_event);
            System.out.printf("[%s] published event : %s %n", Thread
                    .currentThread().getName(), another_event);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
