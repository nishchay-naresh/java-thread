package com.nishchay.concurrentpkg.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadPool {

    public static void main(String[] args) {

        // build an executor and a task
        // and pass the task to executor
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Runnable task = () -> System.out.println("I ran!");
        executor.execute(task);
        executor.shutdown();

        new Thread(task).start();
    }
}
