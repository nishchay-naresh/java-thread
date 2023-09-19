package com.nishchay.thread.basic.interrupt;

import com.nishchay.Utils;

import java.util.concurrent.*;

public class TimeOutATask {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        timeOut();

    }

    private static void timeOut() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> resultFuture = executor.submit(() -> {
            System.out.println(Thread.currentThread().getName() + " - executing a task");
            Utils.sleep0(4000);
            return "done";
        });

        String result;
        try {
            result = resultFuture.get(5, TimeUnit.SECONDS);
            //result = resultFuture.get(2, TimeUnit.SECONDS); // - java.util.concurrent.TimeoutException
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }finally {
            executor.shutdown();
        }
        System.out.println("result = " + result);
    }
}