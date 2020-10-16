package com.nishchay.thread.threadcommunication.sum100.objectlock;

import java.util.stream.IntStream;

public class Sum100Task implements Runnable {

    private Lock lock;

    public Sum100Task(Lock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {

        synchronized (lock) {
            sum100();
            lock.setChildTurn(false);
            lock.notify();
        }
    }

    private void sum100() {
        int sum = IntStream.rangeClosed(1, 100).sum();
        System.out.println("sum of 100 = " + sum);
    }

}