package com.nishchay.thread.threadcommunication.prodcons.semaphore;

import java.util.concurrent.Semaphore;

public class SharedObject {

    private int item;

    // making sure producer thread should run first
    private Semaphore produced = new Semaphore(1);
    private Semaphore consumed = new Semaphore(0);

    public void put(int value) {
        try {
            produced.acquire();
            item = value;
            System.out.println(Thread.currentThread().getName() + " Produces : " + item);
            consumed.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void get() {
        try {
            consumed.acquire();
            System.out.println(Thread.currentThread().getName() + " Consume : " + item);
            produced.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
