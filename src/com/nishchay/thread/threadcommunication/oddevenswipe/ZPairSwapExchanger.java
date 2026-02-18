package com.nishchay.thread.threadcommunication.oddevenswipe;

import java.util.concurrent.Exchanger;

/*
 * MultiThreading In Java: Print below a sequence using two thread thread1 & thread2
 * MultiThreading In Java: Print 1 to 10 using two threads, swipe thread value after a pair print
 *
 * thread1 - 1
 * thread2 - 2
 * .....
 * thread1 - 4
 * thread2 - 3
 * .....
 * thread1 - 5
 * thread2 - 6
 * .....
 * thread1 - 8
 * thread2 - 7
 * .....
 * thread1 - 9
 * thread2 - 10
 * .....
 * so on
 *
 *
 * */
public class ZPairSwapExchanger {
    public static final int LIMIT = 5;

    public static void main(String[] args) {
        Exchanger<Integer> exchanger = new Exchanger<>();

        new Thread(() -> printThread1Task(exchanger, 1), "thread1").start();
        new Thread(() -> printThread2Task(exchanger, 2), "thread2").start();
    }

    public static void printThread1Task(Exchanger<Integer> exchanger, int start) {
        for (int i = 1; i <= LIMIT; i++) {
            try {
                System.out.println(Thread.currentThread().getName() + " - " + start);
                start = start + 2;

                // Pass the number to thread2
                start = exchanger.exchange(start);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void printThread2Task(Exchanger<Integer> exchanger, int start) {
        for (int i = 1; i <= LIMIT; i++) {
            try {
                System.out.println(Thread.currentThread().getName() + " - " + start);
                start = start + 2;

                // Pass the number to thread1
                start = exchanger.exchange(start);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

/*
 * o/p=>
 *	thread1 - 1
 *	thread2 - 2
 *
 *	thread1 - 4
 *	thread2 - 3
 *
 *	thread2 - 6
 *	thread1 - 5
 *
 *	thread2 - 7
 *	thread1 - 8
 *
 *	thread1 - 9
 *	thread2 - 10
 *
 * */