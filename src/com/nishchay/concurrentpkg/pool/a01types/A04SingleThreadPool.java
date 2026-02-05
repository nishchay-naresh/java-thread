package com.nishchay.concurrentpkg.pool.a01types;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class A04SingleThreadPool {

    public static void main(String[] args) {

        basicEx();
        counterEx();
    }

    private static void basicEx() {

        // build an executor and a task
        // and pass the task to executor
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Runnable task = () -> System.out.println("I ran!");
        executor.submit(task);
        executor.shutdown();

        new Thread(task).start();
    }

    private static void counterEx() {

        AtomicInteger counter = new AtomicInteger();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> counter.set(1));
        executor.submit(() -> counter.compareAndSet(1, 2));
        executor.submit(() -> counter.compareAndSet(2, 3));
        System.out.println("counter - " + counter.get()); // 3
        executor.shutdown();
    }
}
