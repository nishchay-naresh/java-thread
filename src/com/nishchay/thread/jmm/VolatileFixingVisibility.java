package com.nishchay.thread.jmm;

import com.nishchay.Utils;

public class VolatileFixingVisibility {


//    private static boolean running = true;
    private static volatile boolean running = true;

    public static void main(String[] args) throws Exception {
//        ex1();
        ex2();
    }


    private static void ex1() throws InterruptedException {
        Thread t = new Thread(() -> {
            while (running) { }
            System.out.println("Thread stopped!");
        });

        t.start();

        Thread.sleep(1000);
        running = false; // visible immediately to all threads

        System.out.println("Main thread updated running=false");
    }

    private static void ex2() {

        Thread writer = new Thread(() -> {
            Utils.sleep0(1000);

            System.out.println("[Writer] Setting running = false");
            running = false;        // Writer thread updates the flag
        });

        Thread reader = new Thread(() -> {
            System.out.println("[Reader] Started...");
            while (running) {
                // Busy loop – but may NEVER see change
            }
            System.out.println("[Reader] Detected stop! Exiting...");
        });

        reader.start();
        writer.start();
    }


}
