package com.nishchay.concurrentpkg.pool.fixedpoolrunnable.factory;

import com.nishchay.concurrentpkg.pool.fixedpoolrunnable.PrintJob;

import java.util.concurrent.*;

public class ThreadFactoryDemo {

    public static void main(String[] args) {

        fixedThreadPoolWithCustomThreadNamesDemo();
        threadPoolWithExceptionHandler();

    }

    private static void fixedThreadPoolWithCustomThreadNamesDemo() {

        ExecutorService fixedThreadPool = Executors.newCachedThreadPool(new NamedThreadFactory());
//        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(6, new NamedThreadFactory());

        PrintJob[] jobs = {
                new PrintJob("Tom"),
                new PrintJob("Linda"),
                new PrintJob("Sam"),
                new PrintJob("Patric"),
                new PrintJob("Jenny"),
                new PrintJob("Smith")
        };

        for (PrintJob job : jobs) {
            fixedThreadPool.execute(job);
        }
        fixedThreadPool.shutdown();

    }

    private static void threadPoolWithExceptionHandler() {

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4, new ThreadFactoryExceptionHandler());

        fixedThreadPool.execute(() -> {
            System.out.println("Executing task 1");
            throw new RuntimeException("Exception from task 1");
        });

        fixedThreadPool.execute(() -> {
            System.out.println("Executing task 2");
            throw new RuntimeException("Exception from task 2");
        });

        fixedThreadPool.execute(() -> {
            System.out.println("Executing task 3");
            throw new RuntimeException("Exception from task 3");
        });

        fixedThreadPool.execute(() -> {
            System.out.println("Executing task 4");
            throw new RuntimeException("Exception from task 4");
        });

        fixedThreadPool.execute(() -> {
            System.out.println("Executing task 5");
            throw new RuntimeException("Exception from task 5");
        });

        fixedThreadPool.shutdown();
    }

}
