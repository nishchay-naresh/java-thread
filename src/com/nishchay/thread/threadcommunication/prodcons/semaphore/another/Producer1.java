package com.nishchay.thread.threadcommunication.prodcons.semaphore.another;

import java.util.concurrent.Semaphore;

/**
 * Producer Class.
 */
public class Producer1 implements Runnable {

    private Semaphore semaphoreProducer;
    private Semaphore semaphoreConsumer;

    public Producer1(Semaphore semaphoreProducer, Semaphore semaphoreConsumer) {
        this.semaphoreProducer = semaphoreProducer;
        this.semaphoreConsumer = semaphoreConsumer;
    }

    public void run() {
        for (int i = 1; i <= 10; i++) {
            try {
                semaphoreProducer.acquire();
                System.out.println(Thread.currentThread().getName() + " Produced : " + i);
                semaphoreConsumer.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}