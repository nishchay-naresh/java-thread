package com.nishchay.concurrentpkg.synchronizers.latch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
CountDownLatch is used here, to pause main method execution
Waiting for all the sub-task to be completed  -  Arrival of all the guest
 */
public class LatchDemoThreadPool {


    public static void main(String[] args) {

        final int NO_OF_GUEST = 4;
        CountDownLatch latch = new CountDownLatch(NO_OF_GUEST);

        System.out.println(Thread.currentThread().getName() + " has started the main exception ----" );
        Guest guest = new Guest(latch);

        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.submit(guest);
        executorService.submit(guest);
        executorService.submit(guest);
        executorService.submit(guest);

        executorService.shutdown();

        System.out.println(Thread.currentThread().getName() + " waiting for all sub-task to finish (which is - all guest to arrive)");
        try {
            latch.await();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " resume once all sub-task finished (which is - all guest have arrived)");
        System.out.println(Thread.currentThread().getName() + " has ended the main exception ----" );

    }
}
