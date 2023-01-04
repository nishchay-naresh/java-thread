package com.nishchay.concurrentpkg.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static com.nishchay.Utils.sleep0;

/*
*  ThreadPoolExecutor - Concrete class which actually instantiate the pool
*
*   ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler)
*   If the maxPoolSize and the blockingQueue are both exhausted, the handler throws a runtime RejectedExecutionException upon rejection.
*
* */
public class ThreadPoolExecutorsDemo {

    public static void main(String[] args) {

        basicMethodEx();

    }

    private static void basicMethodEx() {

        ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

        System.out.println("pool thread count - " + threadPool.getPoolSize());//0

        // Both methods(execute, submit) are available for Runnable type
        threadPool.execute(() -> {
            System.out.println("finishing a small task");
        });

        threadPool.submit(() -> {
            sleep0(1000);
        });
        threadPool.submit(() -> {
            sleep0(1000);
        });

        threadPool.submit(() -> {
            sleep0(1000);
        });

        System.out.println("pool thread count - " + threadPool.getPoolSize());//2
        System.out.println("CompletedTaskCount - " + threadPool.getCompletedTaskCount());//1

        System.out.println("thread in execution - " + threadPool.getActiveCount());
        System.out.println("Queue size(task waiting in queue) - " + threadPool.getQueue().size());

        threadPool.shutdown();
    }
}
/*
 * O/P =>
 *
 *	pool thread count - 0
 *	finishing a small task
 *	pool thread count - 2
 *	CompletedTaskCount - 1
 *	thread in execution - 2
 *	Queue size(task waiting in queue) - 1
 * */