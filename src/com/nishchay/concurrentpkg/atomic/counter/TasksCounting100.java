package com.nishchay.concurrentpkg.atomic.counter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TasksCounting100 {

    public static void main(String[] args) throws InterruptedException {

        // execute below methods one by one, by commenting others to understand the concept better
        theWrongCount();
        fixBySynchronisedCounter();
        fixByCounterUsingLock();
        fixByCounterUsingAtomic();
    }

    // Race Condition Example -  because of atomicity issue
    private static void theWrongCount() {
        Counter counter = new CounterImpl();
        hundredThreadIncrement(counter);
    }
    /*
     * o/p =>
     *  we will get inconsistent count for multiple run because of atomicity issue
     *        Final count is: 992
     *        Final count is: 994
     *        Final count is: 985
     *        Final count is: 995
     *
     * We will keep getting wrong count all different implementations of counter
     * */

    // fixing atomicity issue - using Synchronization
    private static void fixBySynchronisedCounter() {
        Counter counter = new CounterImplSynchronized();
        hundredThreadIncrement(counter);
    }

    // fixing atomicity issue - using Lock
    private static void fixByCounterUsingLock() {
        Counter counter =  new CounterUsingLock();
        hundredThreadIncrement(counter);
    }

    // fixing atomicity issue - using atomic package
    private static void fixByCounterUsingAtomic() {
        Counter counter =  new CounterUsingAtomic();
        hundredThreadIncrement(counter);
    }

    private static void hundredThreadIncrement(Counter counter){
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 1000; i++) {
            executorService.submit(() -> counter.increment());
        }
        executorService.shutdown();
        try {
            boolean ignore = executorService.awaitTermination(20, TimeUnit.SECONDS);
            // Blocks until all tasks have completed execution after a shutdown request, or the timeout occurs
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Final count is : " + counter.getCount());
    }
}