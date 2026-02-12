package com.nishchay.thread.jmm;

import com.nishchay.Utils;

/*
 *
 * Two ways we can make this code running:
 *   1. Make this done variable as volatile
 *   2. Or add a sleep(0), under the while loop
 *
 * ======== discussion around this ============
 *  why its working in both of the scenarios:
 *  1. Make this done variable as volatile - done variable will always be read from the main memory, so it is visible
 *  2. Or add a sleep(0), under the while loop - when we are putting sleep in executing thread, it will save the context in main memory
 *  so again done variable will be read from the main memory, so it is visible
 *
 *  Without doing any of the above things: since done variable is a class variable, it will be part of register in thread context.
 *  So when it gets modified by the other process, changes are not reflected to register level.
 *
 *
 * */
public class BarrierConditionExpose {

    private static volatile boolean done;
//    private static boolean done;

    public static void main(String[] args) throws InterruptedException {

        done = false;
        Runnable task = () -> {
            System.out.println("running ....");

            int count = 0;
            while (!done) {
                count++;
                Utils.sleep0(0);
            }
            System.out.println("Exiting thread - " + count);
        };

        new Thread(task).start();

        System.out.println("in main... ");
        Utils.sleep0(2000);

        System.out.println("Setting done to true");
        done = true;
    }

}

/*
 * o/p =>
 *
 *	Stuck in execution:
 *	in main...
 *	running ....
 *	Setting done to true
 *
 *	Completing its execution:
 *	in main...
 *	running ....
 *	Setting done to true
 *	Exiting thread - 20619459
 *
 * */