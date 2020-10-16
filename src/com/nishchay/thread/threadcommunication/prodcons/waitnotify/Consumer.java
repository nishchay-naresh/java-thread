package com.nishchay.thread.threadcommunication.prodcons.waitnotify;

public class Consumer implements Runnable {
    private SharedObject sharedObject;

    public Consumer(SharedObject sharedObject) {
        this.sharedObject = sharedObject;
    }

    public void run() {
        int value = 0;
        for (int i = 1; i <= 10; i++) {
            value = sharedObject.get();
            System.out.println(Thread.currentThread().getName() + " consume:" + value);
        }
    }
}