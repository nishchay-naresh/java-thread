package com.nishchay.thread.threadcommunication.a02oddeven;


/*
 *  Print odd and even numbers by two threads using wait - notify
 *
 * Two threads Uses wait() and notify() and printing in below fashion:
 * Odd thread → prints 1,3,5,7...
 * Even thread → prints 2,4,6,8...
 *
 * */

public class ZOddEvenWaitNotify {

    public static final int LIMIT = 10;

    public static void main(String[] args) {

        BufferSingle commonBuffer = new BufferWaitNotifyImpl();
        new Thread(() -> oddNumGenerateTask(commonBuffer), "Odd Thread").start();
        new Thread(() -> evenNumGenerateTask(commonBuffer), "Even Thread").start();
    }

    /*
     * instead of creating a new class for thread implementation, putting thread logic in a method
     * later invoking these methods thought threads created using lambdas
     * */
    public static void oddNumGenerateTask(BufferSingle buffer) {
        int odd = 1;
        for (int i = 1; i <= LIMIT; i++) {
            buffer.printOdd(odd);
            odd = odd + 2;
        }
    }

    public static void evenNumGenerateTask(BufferSingle buffer) {
        int even = 2;
        for (int i = 1; i <= LIMIT; i++) {
            buffer.printEven(even);
            even = even + 2;
        }
    }
}




