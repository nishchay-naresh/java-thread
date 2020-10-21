package com.nishchay.thread.threadcommunication.prodcons.semaphore.another;

import java.util.concurrent.Semaphore;

public class ProducerConsumerSemaphore1 {

    public static void main(String[] args) {

        // making sure producer thread should run first
        Semaphore semaphoreProducer = new Semaphore(1);
        Semaphore semaphoreConsumer = new Semaphore(0);

        Producer1 producer = new Producer1(semaphoreProducer, semaphoreConsumer);
        Consumer1 consumer = new Consumer1(semaphoreConsumer, semaphoreProducer);

        Thread producerThread = new Thread(producer, "ProducerThread -");
        Thread consumerThread = new Thread(consumer, "ConsumerThread -");

        producerThread.start();
        consumerThread.start();

    }
}