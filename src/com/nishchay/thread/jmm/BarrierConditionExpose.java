package com.nishchay.thread.jmm;

import static com.nishchay.Utils.sleep0;

/*
*
* Two ways we can make this code running :
*   1. Make this done variable as volatile
*   2. Or add a sleep(0), under the while loop
*
* ======== discussion around this ============
*  why its working in both of the scenario :
*  1. Make this done variable as volatile - done variable will always be read from main memory, so it is visible
*  2. Or add a sleep(0), under the while loop - when we are putting sleep in executing thread, it will save the context in main memory
*  so again done variable will be read from main memory, so it is visible
*
*  Without doing any of the above the thing : since done variable is a class variable it will be part of register in thread context.
*  So when it gets modified by the other process, changes are not reflecting to register level.
*
*
* */
public class BarrierConditionExpose {

//    private static volatile boolean done;
    private static boolean done;

    public static void main(String[] args) throws InterruptedException {

        done = false;
        new Thread(new Runnable() {
            public void run() {
                System.out.println("running ....");

                int count = 0;
                while (!done) {
                    count++;
                    sleep0(0);
                }
                System.out.println("Exiting thread - " + count);
            }
        }).start();

        System.out.println("in main... ");
        sleep0(2000);

        System.out.println("Setting done to true");
        done = true;
    }

}

/*
 * o/p =>
 *
 *	stuck in execution :
 *	in main...
 *	running ....
 *	Setting done to true
 *
 *	Completing its execution :
 *	in main...
 *	running ....
 *	Setting done to true
 *	Exiting thread - 5444941
 *
 * */