package com.nishchay.thread.threadcommunication.a03nthreads;

import java.util.concurrent.Semaphore;


/*
 * print A, B, C in sequence using 3 threads
 *
 * thread 1 - A, A, A, A, A
 * thread 2 - B, B, B, B, B
 * thread 3 - C, C, C, C, C
 * */
public class ThreeThreadsABC {

    public static final int LIMIT = 10;

    public static void main(String[] args) {
        Semaphore s1 = new Semaphore(1);
        Semaphore s2 = new Semaphore(0);
        Semaphore s3 = new Semaphore(0);

        Thread t1 = new Thread(() -> printAlphaTask('A', s1, s2), "Thread 1");
        Thread t2 = new Thread(() -> printAlphaTask('B', s2, s3), "Thread 2");
        Thread t3 = new Thread(() -> printAlphaTask('C', s3, s1), "Thread 3");

        t1.start();
        t2.start();
        t3.start();
    }

    private static void printAlphaTask(char c, Semaphore current, Semaphore next) {

        for (int i = 0; i < LIMIT; i++) {
            try {
                current.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " - " + c);
            next.release();
        }
    }


}