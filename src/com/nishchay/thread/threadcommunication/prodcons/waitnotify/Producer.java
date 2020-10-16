package com.nishchay.thread.threadcommunication.prodcons.waitnotify;

public class Producer implements Runnable {
    private SharedObject sharedObject;

    public Producer(SharedObject sharedObject) {
        this.sharedObject = sharedObject;
    }

    public void run() {
        for (int i = 1; i <= 10; i++) {
            sharedObject.put(i);
            System.out.println(Thread.currentThread().getName() + " produces:" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}