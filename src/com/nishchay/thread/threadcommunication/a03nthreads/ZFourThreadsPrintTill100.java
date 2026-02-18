package com.nishchay.thread.threadcommunication.a03nthreads;

import java.util.concurrent.Semaphore;


/*
 * Print 1-100 using 4 threads output should be in sequence
 * thread 1 -  1,  2, ... 25
 * thread 2 - 26, 27, ... 50
 * thread 3 - 51, 52, ... 75
 * thread 4 - 76, 77, ... 100
 *
 * */
public class ZFourThreadsPrintTill100 {

    public static final int NO_OF_THREAD = 4;

    public static void main(String[] args) {

        Semaphore s1 = new Semaphore(1);
        Semaphore s2 = new Semaphore(0);
        Semaphore s3 = new Semaphore(0);
        Semaphore s4 = new Semaphore(0);

        Thread t1 = new Thread(() -> printNumberTask(1, 25, s1, s2), "Thread 1");
        Thread t2 = new Thread(() -> printNumberTask(26, 50, s2, s3), "Thread 2");
        Thread t3 = new Thread(() -> printNumberTask(51, 75, s3, s4), "Thread 3");
        Thread t4 = new Thread(() -> printNumberTask(76, 100, s4, s1), "Thread 4");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

    private static void printNumberTask(int start, int end, Semaphore current, Semaphore next) {
        try {
            current.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (int i = start; i <= end; i++) {
            System.out.println(Thread.currentThread().getName() + " - " + i);
        }
        next.release();
    }

}