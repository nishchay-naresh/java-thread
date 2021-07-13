package com.nishchay.concurrentpkg.synchronizers.synchronousq;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueExample {

    public static void main(String[] args) {
        final BlockingQueue<String> syncQueue = new SynchronousQueue<>();

        // start publisher thread
        new Thread(new QueueProducer(syncQueue), "publisher thread").start();

        // start consumer thread
        new Thread(new QueueConsumer(syncQueue), "consumer thread").start();
    }

}

/*
 * --------------Happy path : 2 put() & 2 take(), put() == take()
 * O/P =>
 *	[publisher thread] published event : SYNCHRONOUS_EVENT
 *	[consumer thread] consumed event : SYNCHRONOUS_EVENT
 *	[consumer thread] consumed event : ANOTHER_EVENT
 *	[publisher thread] published event : ANOTHER_EVENT
 *
 * --------------Blocked path : 2 put() & 1 take(), put() != take()
 *  publisher threads gets blocked for indefinite time
 *  [consumer thread] consumed event : SYNCHRONOUS_EVENT
 *  [publisher thread] published event : SYNCHRONOUS_EVENT
 *
 * */
