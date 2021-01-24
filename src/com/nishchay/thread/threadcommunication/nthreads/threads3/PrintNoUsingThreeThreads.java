package com.nishchay.thread.threadcommunication.nthreads.threads3;

import java.util.concurrent.Semaphore;


/*
 * Print 1-N using 3 threads one after another
 * int[] arr = new int[]{4, 10, 15, 9, 8, 12, 37, 19, 72, 3, 21, 5}
 * thread 1 - 1, 4, 7, 10, 13
 * thread 2 - 2, 5, 8, 11, 14
 * thread 3 - 3, 6, 9, 12, 15
 * */
public class PrintNoUsingThreeThreads {

    public static void main(String[] args) {

        Semaphore s1 = new Semaphore(1);
        Semaphore s2 = new Semaphore(0);
        Semaphore s3 = new Semaphore(0);


        new Thread(new TaskUsingSemaphore(1, s1, s2), "Thread 1").start();
        new Thread(new TaskUsingSemaphore(2, s2, s3), "Thread 2").start();
        new Thread(new TaskUsingSemaphore(3, s3, s1), "Thread 3").start();

    }

}
