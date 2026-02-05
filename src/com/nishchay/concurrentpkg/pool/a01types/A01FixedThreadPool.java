package com.nishchay.concurrentpkg.pool.a01types;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 *
 *	-	Heavy, long-running use, CPU intensive task
 *	-	A fixed number Of threads is constantly maintained regardless of workload
 *	-	Threads will be kept active until they are explicitly shut down
 *
 * */
public class A01FixedThreadPool {

    public static void main(String[] args) {
        Runnable task = () -> {
            // some CPU intensive operation
            System.out.println(Thread.currentThread().getName() + ", I ran!");
        };
        // execution a runnable task in thread pool using - execute(Runnable)
        int coreCount = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(coreCount);
        for (int i = 0; i < 50; i++) {
            executor.execute(task);
        }
        executor.shutdown();
    }
}
