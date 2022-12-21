package com.nishchay.thread.jmm;

import static com.nishchay.Utils.sleep0;

/*
*
* Two ways we can make this code running :
*   1. Make this done as volatile
*   2. Or add a sleep(0), under the while loop
*
* TODO - Need a discussion around this.
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
                System.out.println("Exiting thread");
            }
        }).start();

        System.out.println("in main... ");
        sleep0(2000);

        System.out.println("Setting done to true");
        done = true;
    }

}
