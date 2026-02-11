package com.nishchay.thread.synchronize;


import com.nishchay.Utils;

import java.util.concurrent.atomic.AtomicBoolean;

/*
 *   ================================ Busy Waiting vs Wait/Notify   =======================================
 *
 * 	This class compares two ways a thread can wait for another thread to finish:
 * 		1.	Busy-waiting (CPU-wasting)
 * 		2.	wait/notify (efficient, blocking)
 *
 *  Same problem in both methods:
 * 		-   Main thread waits for worker thread to finish
 * 		-   Two different waiting strategies
 * 		-   Output shows how many times the waiting logic executed
 *
 * By using proper blocking mechanisms, we can avoid busy-waiting and write more efficient, responsive multithreaded code.
 *
 * https://www.baeldung.com/java-busy-waiting-alternatives
 * */
public class BusyWaitingEx {

    public static void main(String[] args) {
        waitingForWorkerThreadToFinishUsingBusyWaiting();
        System.out.println("---------------------------------");
        waitingForWorkerThreadToFinishUsingWaitNotify();
    }

    /*
     *  What Is Busy-Waiting / Busy Spinning?
     *       It happens when a thread actively checks a condition in a loop until the condition becomes true.
     *
     * This keeps the thread “stuck,” continuously using resources without doing much work.
     *
     * */
    private static void waitingForWorkerThreadToFinishUsingBusyWaiting() {
        AtomicBoolean taskDone = new AtomicBoolean(false);
        long counter = 0;

        Thread worker = new Thread(() -> {
            Utils.sum100();
            taskDone.set(true);
        });

        worker.start();

        // the main thread needs to wait here - so it is doing a Busy-Waiting here
        while (!taskDone.get()) {
            counter++;
        }

        System.out.println("Counter : "+ counter);
        String result = 1 != counter ? "counter >1" : "counter <1";
        System.out.println(result);
    }

    /*
    * How to Avoid Busy-Waiting?
    * Now that we’ve seen the busy-waiting in action, we’ll consider more efficient approaches using blocking mechanisms.
    * In contrast to the busy-waiting, blocking mechanisms allow a thread to pause execution until it’s explicitly resumed(notified).
    *
    * */
    private static void waitingForWorkerThreadToFinishUsingWaitNotify() {
        AtomicBoolean taskDone = new AtomicBoolean(false);
        final Object monitor = new Object();
        long counter = 0;

        Thread worker = new Thread(() -> {
            Utils.sum100();
            synchronized (monitor) {
                taskDone.set(true); // signal completion
                monitor.notify();   // notify other threads waiting on the same lock
            }
        });

        worker.start();

        //the main thread needs to wait here - after an initial check, simply waiting over the monitor lock
        synchronized (monitor) {
            while (!taskDone.get()) {
                counter++;
                Utils.wait0(monitor);
            }
        }

        System.out.println("Counter : "+ counter);
        String result = 1 != counter ? "counter >1" : "counter <1";
        System.out.println(result);
    }
}

/*
* o/p =>
 * sum of 100 = 5050
 * Counter : 740879
 * counter >1
 * ---------------------------------
 * sum of 100 = 5050
 * Counter : 1
 * counter <1
* */
