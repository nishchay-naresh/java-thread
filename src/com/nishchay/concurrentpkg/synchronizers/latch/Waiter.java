package com.nishchay.concurrentpkg.synchronizers.latch;

import java.util.concurrent.CountDownLatch;

public class Waiter implements Runnable {

    private CountDownLatch latch = null;

    public Waiter(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {
        System.out.println("Waiter waiting for all guest to arrive");
        try {
            latch.await();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Waiter has started serving the food ...!!!");
    }
}
