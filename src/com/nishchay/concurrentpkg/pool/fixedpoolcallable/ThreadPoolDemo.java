package com.nishchay.concurrentpkg.pool.fixedpoolcallable;

import java.util.concurrent.*;

import static com.nishchay.Utils.sleep0;

public class ThreadPoolDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        System.out.println("Before submit  : " + ((ThreadPoolExecutor) threadPool).getActiveCount());

        for (int i = 0; i < 6; i++) {
            Future<String> future = threadPool.submit(() -> {
                sleep0(4 * 1000);
                return "Result String returned by - " + Thread.currentThread().getName();
            });
            System.out.println("During Execution  : " + ((ThreadPoolExecutor) threadPool).getActiveCount());
            System.out.println(future.get());
        }

        System.out.println("After submit  : " + ((ThreadPoolExecutor) threadPool).getActiveCount());
        threadPool.shutdown();

    }


}
