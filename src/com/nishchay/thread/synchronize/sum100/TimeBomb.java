package com.nishchay.thread.synchronize.sum100;

import com.nishchay.Utils;


/*
 * Here we are trying to synchronize the execution of 2 threads
 * 	1. Main thread,
 * 	2. A user thread, spawn by main only
 *
 * This solution is written using wait and notify
 * Locking is based on user defined object Lock, singling is happening based on boolean flag
 * Taking care of the spurious wakeup scenario
 *
 * Imp qns
 * 	1. Why boolean[] childDone? Why not boolean childDone?
 * 			Lambdas require effective final variables.
 * 			This won’t compile:
 * 				boolean childDone = false;
 * 				childDone = true;
 *
 * 			Workarounds:
 * 				-	boolean[]
 * 				-	AtomicBoolean
 * 				- set and get it using setter and getter methods after placing this attribute in another class
 *
 * 2. Why while, not if?
 *
 *			while (!childDone[0]) {
 *	    		lock.wait();
 *			}
 *		-	Handles Spurious Wakeup (Thread can wake up without notify)
 *		-	Correct wait/notify pattern
 *		-	if is wrong in real multithreaded code.
 *
 * */
public class TimeBomb {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Starting 10 second count down... ");

        final boolean[] childDone = {false}; // mutable holder
        Object lock = new Object();

        Runnable countDownTask = () -> {
            synchronized (lock) {
                while (!childDone[0]) {
                    countDown();
                    childDone[0] = true;
                    lock.notify();
                }
            }
        };
        Thread t1 = new Thread(countDownTask);
        t1.start();


        synchronized (lock) {
            while (!childDone[0]) {
                lock.wait();
            }
        }
        System.out.println("Boom!!!");
    }

    private static void countDown() {
        String[] timeStr = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"};

        for (int i = 10; i >= 0; i--) {
            System.out.println(timeStr[i]);
            Utils.sleep0(1000);
        }
    }

}