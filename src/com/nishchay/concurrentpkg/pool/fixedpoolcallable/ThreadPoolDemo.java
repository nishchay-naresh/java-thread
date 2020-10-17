package com.nishchay.concurrentpkg.pool.fixedpoolcallable;

import java.util.concurrent.*;

public class ThreadPoolDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService e1 = Executors.newFixedThreadPool(10);
        System.out.println("Before submit  : " + ((ThreadPoolExecutor) e1).getActiveCount());

        for (int i = 0; i < 6; i++) {
            Task task = new Task();
            Future<String> future = e1.submit(task);
            System.out.println("During Execution  : " + ((ThreadPoolExecutor) e1).getActiveCount());
            System.out.println(future.get());
        }

        System.out.println("After submit  : " + ((ThreadPoolExecutor) e1).getActiveCount());
        e1.shutdown();
    }


}


