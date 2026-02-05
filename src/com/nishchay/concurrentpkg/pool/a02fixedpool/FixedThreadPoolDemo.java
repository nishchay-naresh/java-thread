package com.nishchay.concurrentpkg.pool.a02fixedpool;

import java.util.concurrent.*;

import com.nishchay.Utils;

/*
*  future.get() is blocking in nature
*  Its throws checked exceptions - InterruptedException, ExecutionException, So one need to handle them
*
* */
public class FixedThreadPoolDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        runnableInPool();
        callableInPool();
    }

    private static void runnableInPool() {

        Runnable task = () -> System.out.println(Thread.currentThread().getName() + ", I ran!");
        // execution a runnable task in a user thread
        Thread t = new Thread(task);
        t.start();

        // execution a runnable task in thread pool using - execute(Runnable)
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.execute(task);
        executor.shutdown();

        // execution a runnable task in thread pool using - submit(Runnable)
        executor = Executors.newFixedThreadPool(1);
        executor.submit(task);
        executor.shutdown();
    }


    private static void callableInPool() throws ExecutionException, InterruptedException {

        Callable<String> task = () -> {
            System.out.println("I ran!");
            return null;
        };
        ExecutorService executor = Executors.newFixedThreadPool(1);
        // Task will get executed upon submitting to thread-pool, not during the future.get()
        Future<String> future = executor.submit(task);
        Utils.sleep0(2 * 1000);
        System.out.println(future.get());
        executor.shutdown();
    }



}
