package com.nishchay.thread.threadcommunication.nthreads.splittask;

import java.util.concurrent.Semaphore;

public class SyncTask implements Runnable{

    private Semaphore current;
    private Semaphore next;
    private int start;
    private int end;

    public SyncTask(int start, int end, Semaphore current, Semaphore next) {
        this.start = start;
        this.end = end;
        this.current = current;
        this.next = next;
    }


    @Override
    public void run() {

        try {
            current.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        printNumbers(start, end);
        next.release();

    }

    private void printNumbers(int start, int end) {
        System.out.println("------------------------------------------");
        for (int i = start; i <= end; i++) {
            System.out.println(Thread.currentThread().getName() +  " - " + i);
        }

    }
}