package com.nishchay.thread.threadcommunication.nthreads.threads3;

import java.util.concurrent.Semaphore;

public class TaskUsingSemaphore implements Runnable {

    private Semaphore current;
    private Semaphore next;
    private int value;

    public TaskUsingSemaphore(int value, Semaphore current, Semaphore next) {
        this.value = value;
        this.current = current;
        this.next = next;
    }


    @Override
    public void run() {

        for (int i = 1; i <= 5; i++) {
            printNumber(value);
            value = value + 3;
        }

    }

    public void printNumber(int num) {
        try {
            current.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " - " + num);
        next.release();
    }
}