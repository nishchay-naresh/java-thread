package com.nishchay.thread.threadcommunication.a02oddeven;

import java.util.concurrent.Semaphore;

/*
 * Create 2 threads to print odd and even numbers and sequence their output
 * Odd Thread   - 1,3,5,7,9,11,13
 * Even Thread  - 2,4,6,8,10,12,14
 *
 * */
public class ZOddEvenSemaphore {

    static final int MAX = 16;

    public static void main(String[] args) {

        Semaphore oddSemaphore = new Semaphore(1);
        Semaphore evenSemaphore = new Semaphore(0);
        Thread evenThread = new Thread(() -> printEven(oddSemaphore, evenSemaphore), "Even Thread");
        Thread oddThread = new Thread(() -> printOdd(oddSemaphore, evenSemaphore), "Odd Thread");

        evenThread.start();
        oddThread.start();
    }

    private static void printOdd(Semaphore oddSemaphore, Semaphore evenSemaphore) {

        for (int i = 1; i < MAX; i = i + 2) {
            try {
                oddSemaphore.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " - " + i);
            evenSemaphore.release();
        }
    }

    private static void printEven(Semaphore oddSemaphore, Semaphore evenSemaphore) {
        for (int i = 2; i < MAX; i = i + 2) {
            try {
                evenSemaphore.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " - " + i);
            oddSemaphore.release();
        }
    }
}




