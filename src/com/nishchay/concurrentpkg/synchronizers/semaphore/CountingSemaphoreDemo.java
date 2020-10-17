package com.nishchay.concurrentpkg.synchronizers.semaphore;

import java.util.concurrent.Semaphore;

/*
Counting Semaphore is used here to store no of permits for Printer use
 */
public class CountingSemaphoreDemo {

    public static void main(String[] args) {

        final int NO_OF_PRINTER = 2;

        Semaphore noOfPermits =  new Semaphore(NO_OF_PRINTER);

        new Thread(new PrintJob(noOfPermits), "Thread 1").start();
        new Thread(new PrintJob(noOfPermits), "Thread 2").start();
        new Thread(new PrintJob(noOfPermits), "Thread 3").start();
        new Thread(new PrintJob(noOfPermits), "Thread 4").start();
        new Thread(new PrintJob(noOfPermits), "Thread 5").start();
        new Thread(new PrintJob(noOfPermits), "Thread 6").start();

    }
}

