package com.nishchay.thread.threadcommunication.oddevenswipe;

/*
 *
 * MultiThreading In Java: : Print 1 to 10 using 2 thread, swipe thread value after a pair print
 *
 * T1-1
 * T2-2
 * ---------
 * T1-4
 * T2-3
 * ---------
 * T1-5
 * T2-6
 * ---------
 * T1-8
 * T2-7
 * ---------
 * T1-9
 * T2-10
 * ---------
 * so on
 *
 *
 * */

public class Driver {

    public static final int LIMIT = 5;

    public static void main(String[] args) {

        SharedPrinter sharedPrinter = new SharedPrinter(1, 2, true);

        Thread prodThread = new Thread(() -> thread1(sharedPrinter), "Thread1");
        prodThread.start();

        Thread consThread = new Thread(() -> thread2(sharedPrinter), "Thread2");
        consThread.start();

    }

    /*
     *   instead of creating a new class for thread implementation, putting thread logic in a method
     *   later invoking these method thought lambdas
     *
     * */
    public static void thread1(SharedPrinter sharedPrinter) {
        for (int i = 1; i <= LIMIT; i++) {
            sharedPrinter.printFirst();
        }
    }

    public static void thread2(SharedPrinter sharedPrinter) {
        for (int i = 1; i <= LIMIT; i++) {
            sharedPrinter.printSecond();
        }
    }

}
/*
 *
 *  O/P =>
 *        Thread1 - 1
 *        Thread2 - 2
 *        Thread1 - 4
 *        Thread2 - 3
 *        Thread1 - 5
 *        Thread2 - 6
 *        Thread1 - 8
 *        Thread2 - 7
 *        Thread1 - 9
 *        Thread2 - 10
 *
 */