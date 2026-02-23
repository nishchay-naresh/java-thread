package com.nishchay.thread.jmm;

import com.nishchay.Utils;

public class A01VolatileFixingVisibility {


    //    private static boolean running = true;
    private static volatile boolean running = true;

    public static void main(String[] args) throws Exception {
        // Execute below methods one by one, by commenting others to understand the output better
        // Never run them together, because both of the methods sharing the same flag might get inconsistent output
        visibilityBetweenMainAndChildThread();
        visibilityBetweenTwoThreads();
    }


    private static void visibilityBetweenMainAndChildThread(){
        Thread t = new Thread(() -> {
            while (running) {
                System.out.println(Thread.currentThread().getName() + " - executing a part of the task");
                Utils.sleep0(1000);
            }
            System.out.println(Thread.currentThread().getName() + " task is been stopped");
        });

        t.start();

        Utils.sleep0(3 * 1000);
        running = false; // visible immediately to all threads
        System.out.println("Main thread updated running=false");
    }

    private static void visibilityBetweenTwoThreads() {

        Thread writer = new Thread(() -> {
            Utils.sleep0(3000);

            System.out.println("[Writer] Setting running = false");
            running = false;
        });

        Thread reader = new Thread(() -> {
            System.out.println("[Reader] Started...");
            while (running) {
                System.out.println("[Reader] executing a part of the task");
                Utils.sleep0(1000);
            }
            System.out.println("[Reader] Detected stop! Exiting...");
        });

        reader.start();
        writer.start();
    }
}

/*
 * o/p =>
 * Thread-0 - executing a part of the task
 * Thread-0 - executing a part of the task
 * Thread-0 - executing a part of the task
 * Main thread updated running=false
 * Thread-0 task is been stopped
 * ------------------------------------------
 * [Reader] Started...
 * [Reader] executing a part of the task
 * [Reader] executing a part of the task
 * [Reader] executing a part of the task
 * [Writer] Setting running = false
 * [Reader] Detected stop! Exiting...
 *
 * */
