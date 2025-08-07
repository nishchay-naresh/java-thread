package com.nishchay.thread.threadcommunication.prodcons.waitnotify;


import com.nishchay.Utils;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.nishchay.Utils.wait0;

/*
*   ===========  Busy-Waiting / Busy Spinning ===================
*
* https://www.baeldung.com/java-busy-waiting-alternatives
* */
public class BusyWaitingEx {

    public static void main(String[] args) {
        givenWorkerThread_whenBusyWaiting_thenWaitingThreadExecutedMultipleTimes();
        givenWorkerThread_whenUsingWaitNotify_thenWaitEfficientlyOnce();
    }


    /*
     *  What Is Busy-Waiting?
     *       It happens when a thread actively checks a condition in a loop until the condition becomes true.
     *
     * This keeps the thread “stuck”, continuously using resources without doing much work.
     *
     * */
    private static void givenWorkerThread_whenBusyWaiting_thenWaitingThreadExecutedMultipleTimes() {
        AtomicBoolean taskDone = new AtomicBoolean(false);
        long counter = 0;

        Thread worker = new Thread(() -> {
            Utils.simulateThreadWork();
            taskDone.set(true);
        });

        worker.start();

        // main thread need to wait here - so it is doing a Busy-Waiting here
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
    * In contrast to the busy-waiting, blocking mechanisms allow a thread to pause execution until it’s explicitly resumed.
    *
    *  We’ve seen how busy-waiting can waste valuable CPU resources and why it’s generally not a good synchronization strategy.
    *  By using proper blocking mechanisms, we can avoid busy-waiting and write more efficient, responsive multithreaded code.
    *
    * */
    private static void givenWorkerThread_whenUsingWaitNotify_thenWaitEfficientlyOnce() {
        AtomicBoolean taskDone = new AtomicBoolean(false);
        final Object monitor = new Object();
        long counter = 0;

        Thread worker = new Thread(() -> {
            Utils.simulateThreadWork();
            synchronized (monitor) {
                taskDone.set(true);
                monitor.notify();
            }
        });

        worker.start();

        // main thread need to wait here-after an initial check, simply waiting over the monitor lock
        synchronized (monitor) {
            while (!taskDone.get()) {
                counter++;
                wait0(monitor);
            }
        }

        System.out.println("Counter : "+ counter);
        String result = 1 != counter ? "counter >1" : "counter <1";
        System.out.println(result);
    }

}
