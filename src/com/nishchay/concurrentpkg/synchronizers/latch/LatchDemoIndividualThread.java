package com.nishchay.concurrentpkg.synchronizers.latch;

import java.util.concurrent.CountDownLatch;

/*
CountDownLatch is used here, to wait for main task - food to be served
Waiting for all the sub-task to be completed  -  Arrival of all the guest
 */
public class LatchDemoIndividualThread {

    public static void main(String[] args) {

        final int NO_OF_GUEST = 4;
        CountDownLatch latch = new CountDownLatch(NO_OF_GUEST);

        Waiter waiter = new Waiter(latch);
        Guest guest = new Guest(latch);

        new Thread(waiter).start();
        new Thread(guest, "Thread 1").start();
        new Thread(guest, "Thread 2").start();
        new Thread(guest, "Thread 3").start();
        new Thread(guest, "Thread 4").start();
    }
}
