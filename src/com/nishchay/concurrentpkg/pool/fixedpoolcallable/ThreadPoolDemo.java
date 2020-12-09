package com.nishchay.concurrentpkg.pool.fixedpoolcallable;

import java.util.concurrent.*;

public class ThreadPoolDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        System.out.println("Before submit  : " + ((ThreadPoolExecutor) threadPool).getActiveCount());

        for (int i = 0; i < 6; i++) {
            Task task = new Task();
            Future<String> future = threadPool.submit(task);
            System.out.println("During Execution  : " + ((ThreadPoolExecutor) threadPool).getActiveCount());
            System.out.println(future.get());
        }

        System.out.println("After submit  : " + ((ThreadPoolExecutor) threadPool).getActiveCount());
        threadPool.shutdown();

    }


}
