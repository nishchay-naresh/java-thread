package com.nishchay.thread.threadcommunication.a03nthreads;

import java.util.concurrent.Semaphore;


/*
 * Print 1-N using 3 threads one after another
 * thread 1 - 1, 4, 7, 10, 13
 * thread 2 - 2, 5, 8, 11, 14
 * thread 3 - 3, 6, 9, 12, 15
 *
 *
 * */
public class ThreeThreadsPrintNo {

    public static final int LIMIT = 5;

    public static void main(String[] args) {
        Semaphore s1 = new Semaphore(1);
        Semaphore s2 = new Semaphore(0);
        Semaphore s3 = new Semaphore(0);

        Thread t1 = new Thread(() -> printNumberTask(1, s1, s2), "Thread 1");
        Thread t2 = new Thread(() -> printNumberTask(2, s2, s3), "Thread 2");
        Thread t3 = new Thread(() -> printNumberTask(3, s3, s1), "Thread 3");

        t1.start();
        t2.start();
        t3.start();
    }

    private static void printNumberTask(int start, Semaphore current, Semaphore next) {
        for (int i = 0; i < LIMIT; i++) {
            try {
                current.acquire();
                System.out.println(Thread.currentThread().getName() + " - " + start);
                start = start + 3;
                next.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}