package com.nishchay.thread.threadcommunication.nthreads.printarray;

import java.util.concurrent.Semaphore;

/*
 * Print an int array value using 2 threads of a threadpool one after another
 * int[] arr = new int[]{4, 10, 15, 9, 8, 12, 37, 19, 72, 3, 21, 5}
 * thread 1 - 4, 9, 37, 3
 * thread 2 - 10, 8, 19, 21
 * thread 3 - 15, 12, 72, 5
 * */
public class PrintArrayUsingThreeThreads {

    public static void main(String[] args) {

        int[] intArr = new int[]{4, 10, 15, 9, 8, 12, 37, 19, 72, 3, 21, 5};

        Semaphore s1 = new Semaphore(1);
        Semaphore s2 = new Semaphore(0);
        Semaphore s3 = new Semaphore(0);

        new Thread(new TaskUsingSemaphore(intArr, 0, s1, s2), "Thread 1").start();
        new Thread(new TaskUsingSemaphore(intArr, 1, s2, s3), "Thread 2").start();
        new Thread(new TaskUsingSemaphore(intArr, 2, s3, s1), "Thread 3").start();
    }

}


/*
 * o/p =>
 ** Thread 1 - 4
 * Thread 2 - 10
 * Thread 3 - 15
 * Thread 1 - 9
 * Thread 2 - 8
 * Thread 3 - 12
 * Thread 1 - 37
 * Thread 2 - 19
 * Thread 3 - 72
 * Thread 1 - 3
 * Thread 2 - 21
 * Thread 3 - 5

 * thread 1 - 4, 9, 37, 3
 * thread 2 - 10, 8, 19, 21
 * thread 3 - 15, 12, 72, 5
 * */