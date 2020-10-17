package com.nishchay.concurrentpkg.pool.fixedpoolcallable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// fixedThreadPool of Callable tasks
public class PoolCallableTask {

    public static void main(String[] args) {

        List<SumJob> jobs = new ArrayList<>();
        jobs.add(new SumJob(1, 20));
        jobs.add(new SumJob(1, 10));
        jobs.add(new SumJob(50, 75));
        jobs.add(new SumJob(30, 40));
        jobs.add(new SumJob(75, 95));
        jobs.add(new SumJob(105, 125));

        ExecutorService fixedThrdPool = Executors.newFixedThreadPool(8);

        for (SumJob job : jobs) {
            Future<Integer> future = fixedThrdPool.submit(job);

            // fetching out the result in the order of their submission
            Integer res = null;
            try {
                res = future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            System.out.print(" Sum = " + res);
        }

        fixedThrdPool.shutdown();

//      submission of any task after shutdown() will lead to  - java.util.concurrent.RejectedExecutionException
//        Future<Integer> future = fixedThrdPool.submit( new SumJob(2000, 2500));
    }
}
