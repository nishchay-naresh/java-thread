package com.nishchay.concurrentpkg.synchronizers.latch;

import com.nishchay.Utils;

import java.util.concurrent.CountDownLatch;

public class Guest implements Runnable {

    private CountDownLatch latch = null;

    public Guest(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {
        Utils.sleep0(2000);
        this.latch.countDown();
        System.out.printf("Guest named - %s has arrived waiting for further %d more guest to come %n",
                Thread.currentThread().getName(), latch.getCount());
    }
}
