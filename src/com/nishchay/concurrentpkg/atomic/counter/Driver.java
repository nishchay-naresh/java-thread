package com.nishchay.concurrentpkg.atomic.counter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Driver {

    public static void main(String[] args) throws InterruptedException {

        // Race Condition Example -  bcus of atomicity issue
        // ICounter counter = new Counter();
        /*
        *  we will get inconsistent count for multiple run  bcus of atomicity issue
        *   Final count is : 992
        *   Final count is : 994
        *   Final count is : 985
        *   Final count is : 996
        * we will ge the consistent count for rest all kind of counter
        *   Final count is : 1000
        *
        * */

        // fixing atomicity issue - using Synchronization
        // ICounter counter = new SynchronizedCounter();

        // fixing atomicity issue - using Lock
        // ICounter counter = new CounterUsingLock();

        // fixing atomicity issue - using atomic package
        ICounter counter = new AtomicCounter();

        hundredThreadIncrement(counter);
    }


    private static void hundredThreadIncrement(ICounter counter) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 1000; i++) {
            executorService.submit(() -> counter.increment());
        }

        executorService.shutdown();
        executorService.awaitTermination(60, TimeUnit.SECONDS);

        System.out.println("Final count is : " + counter.getCount());
    }

}

/*
 *  O/P =>
 *        Final count is : 992
 *        Final count is : 994
 *        Final count is : 985
 *
 * */