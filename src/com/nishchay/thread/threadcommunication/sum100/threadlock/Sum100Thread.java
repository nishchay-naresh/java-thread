package com.nishchay.thread.threadcommunication.sum100.threadlock;

import java.util.stream.IntStream;

public class Sum100Thread extends Thread {

    @Override
    public void run() {

        synchronized (this){
            System.out.println("child thread execution started");
            sum100();
            System.out.println("child thread notifying main thread");
            this.notify();
        }
    }

    private void sum100() {
        int sum = IntStream.rangeClosed(1, 100).sum();
        System.out.println("sum of 100 = " + sum);
    }

}
