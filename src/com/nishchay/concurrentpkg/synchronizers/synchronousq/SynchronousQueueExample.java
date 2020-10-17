package com.nishchay.concurrentpkg.synchronizers.synchronousq;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueExample {

    public static void main(String args[]) {
        final BlockingQueue<String> syncQueue = new SynchronousQueue<>();

        // start publisher thread
        new Thread(new QueueProducer(syncQueue), "publisher thread").start();

        // start consumer thread
        new Thread(new QueueConsumer(syncQueue), "consumer thread").start();
    }

}


