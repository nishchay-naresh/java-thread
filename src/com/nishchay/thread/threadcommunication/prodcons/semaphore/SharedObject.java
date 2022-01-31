package com.nishchay.thread.threadcommunication.prodcons.semaphore;

import java.util.concurrent.Semaphore;

public class SharedObject {

    private int item;
    private Semaphore produced;
    private Semaphore consumed;

    public SharedObject() {
        // making sure producer thread should run first
        produced = new Semaphore(1);
        consumed = new Semaphore(0);
    }

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
