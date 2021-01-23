package com.nishchay.thread.threadcommunication.nthreads.threads3;


import java.util.concurrent.Semaphore;

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
