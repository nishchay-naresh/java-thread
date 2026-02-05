package com.nishchay.concurrentpkg.pool.a03factory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorEx {

    public static void main(String[] args) {
        ex1();
    }

    /*
     *  Executors factory methods hide unbounded queues or threads, which can cause OOM or CPU exhaustion.
     *  ThreadPoolExecutor gives explicit control and predictable behavior, so it’s preferred in production.
     *
     * What happens under a heavy load?
     * 	-	First 4 tasks → core threads
     * 	-	Next 100 tasks → queue
     * 	-	Next tasks → extra threads up to 8
     * 	-	Beyond that → caller executes a task (a system slows instead of crashing)
     * 	-	That’s back-pressure, which is what production systems need.
     *
     * */
    private static void ex1() {

        ExecutorService executor =
                new ThreadPoolExecutor(
                        4,                      		// corePoolSize
                        8,                      		            // maximumPoolSize
                        60,                     		            // keepAliveTime
                        TimeUnit.SECONDS,
                        new ArrayBlockingQueue<>(100),      // bounded queue
                        new CustomThreadNameInPool.NamedThreadFactory("order-worker"),
                        new ThreadPoolExecutor.CallerRunsPolicy()
                );

        for (int i = 1; i <= 20; i++) {
            int taskId = i;
            executor.execute(() ->task(taskId) );
        }
        executor.shutdown();
    }

    private static void task(int taskId) {
        System.out.println("Task " + taskId + " executed by " + Thread.currentThread().getName());
    }
}
