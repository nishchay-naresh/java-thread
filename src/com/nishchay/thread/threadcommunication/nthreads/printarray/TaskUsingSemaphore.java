package com.nishchay.thread.threadcommunication.nthreads.printarray;

import java.util.concurrent.Semaphore;

public class TaskUsingSemaphore implements Runnable {

    private Semaphore current;
    private Semaphore next;
    private int[] intArr;
    private int startIndex;

    public TaskUsingSemaphore(int[] intArr, int startIndex, Semaphore current, Semaphore next) {
        this.intArr = intArr;
        this.startIndex = startIndex;
        this.current = current;
        this.next = next;
    }


    @Override
    public void run() {

        for (int i = startIndex; i < intArr.length; i = i + 3) {
            printNumber(intArr[i]);
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