package com.nishchay.concurrentpkg.pool.fixedpoolrunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PoolRunnableTask {

    public static void main(String[] args) {

        PrintJob[] jobs = {
                new PrintJob("Tom"),
                new PrintJob("Linda"),
                new PrintJob("Sam"),
                new PrintJob("Patric"),
                new PrintJob("Jenny"),
                new PrintJob("Smith")
        };

        ExecutorService fixedThrdPool = Executors.newFixedThreadPool(3);

        for (PrintJob job : jobs) {
            fixedThrdPool.submit(job);
        }
        fixedThrdPool.shutdown();

    }
}
