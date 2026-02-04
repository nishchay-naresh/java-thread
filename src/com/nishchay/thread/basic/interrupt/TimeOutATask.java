package com.nishchay.thread.basic.interrupt;

import com.nishchay.Utils;

import java.util.concurrent.*;


/*
 * Classic “timeout a task” pattern - using ExecutorService + Future.
 *  - Wait for its result only for a limited time, and fail if it takes too long.
 *
 * In short:
 * 	-	Task runs in background
 * 	-	Main thread waits max N seconds
 * 	-	If task finishes → get result
 * 	-	If not → timeout exception
 *
 * Future.get(timeout) = wait, but only up to a limit.
 * */
public class TimeOutATask {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        timeOut();

    }

    private static void timeOut() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> resultFuture = executor.submit(() -> {
            System.out.println(Thread.currentThread().getName() + " - executing a task");
            Utils.sleep0(4 * 1000);
            return "done";
        });

        String result;
        try {
            result = resultFuture.get(5, TimeUnit.SECONDS);
            // result = resultFuture.get(2, TimeUnit.SECONDS); // - java.util.concurrent.TimeoutException
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        } finally {
            executor.shutdown();
        }
        System.out.println("result = " + result);
    }
}