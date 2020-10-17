package com.nishchay.concurrentpkg.synchronizers.latch;

import java.util.concurrent.CountDownLatch;

public class Guest implements Runnable {

    private CountDownLatch latch = null;

    public Guest(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {
        try {
            Thread.sleep(2000);
            this.latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Guest named - %s has arrived waiting for further %d more guest to come %n",
                Thread.currentThread().getName(), latch.getCount());
    }
}
