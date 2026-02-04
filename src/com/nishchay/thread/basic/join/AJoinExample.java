package com.nishchay.thread.basic.join;

import com.nishchay.Utils;

public class AJoinExample {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main thread started");

        Thread worker = new Thread(() -> {
            System.out.println("Worker thread started");
            Utils.sleep0(2 * 1000);
            System.out.println("Worker thread finished");
        });

        worker.start();

        System.out.println("Main thread waiting...");
        worker.join();   // main thread waits here
        System.out.println("Main thread resumes");
    }

    private static void m1() {
        System.out.println("m1");
    }

}
/*
 * O/P=>
 * Main thread started
 * Main thread waiting...
 * Worker thread started
 * Worker thread finished
 * Main thread resumes
 * */