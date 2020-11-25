package com.nishchay.concurrentpkg.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/*
 * FutureTask is a concrete class that implements both Runnable and Future
 * bcus of FutureTask only able to submit a Callable instance to an Individual thread (Thread class instance)
 * Callable instance --> FutureTask instance --> Thread class instance
 *
 * */

public class CallableWithoutExecutor {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        Callable<Integer> integerCallable = () -> {
            Thread.sleep(2 * 1000);
			return 100;
        };

        // FutureTask is a concrete class that implements both Runnable and Future
        FutureTask<Integer> futureTask = new FutureTask<>(integerCallable);

        Thread t = new Thread(futureTask);
        t.start();

        System.out.println(futureTask.get());
    }
}
