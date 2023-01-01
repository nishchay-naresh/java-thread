package com.nishchay.thread.threadcommunication.prodcons.arraylist;

public class ConsumerAL implements Runnable {
    private final MessageBuffer queue;

    public ConsumerAL(MessageBuffer queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for(int i=1; i<=10; i++) {
            String message = queue.dequeue();
            System.out.println(Thread.currentThread().getName() + " consumed - " + message);
        }
    }
}