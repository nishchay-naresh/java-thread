package com.nishchay.concurrentpkg.pool.a03factory;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import com.nishchay.Utils;

/*
*  ThreadPoolExecutor - Concrete class which actually instantiate the pool
*
*   ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler)
*   If the maxPoolSize and the blockingQueue are both exhausted, the handler throws a runtime RejectedExecutionException upon rejection.
*
* */
public class ThreadPoolExecutorMethods {

    public static void main(String[] args) {

        basicMethodEx();

    }

    /*
    * getPoolSize() - Returns the number of threads in the pool.
    * getActiveCount() - Returns the number of actively executing threads.
    *
    * */
    private static void basicMethodEx() {

        ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

        System.out.println("pool thread count - " + threadPool.getPoolSize());              //0

        // Both methods(execute, submit) are available for Runnable type
        threadPool.execute(() -> System.out.println("finishing a small task"));
        threadPool.submit(() -> Utils.sleep0(2000));
        threadPool.submit(() -> Utils.sleep0(2000));
        threadPool.submit(() -> Utils.sleep0(2000));
        threadPool.submit(() -> Utils.sleep0(2000));
        System.out.println("Queue size(task waiting in queue) - " + threadPool.getQueue().size());  //2

        System.out.println("pool thread count - " + threadPool.getPoolSize());              //2
        System.out.println("CompletedTaskCount - " + threadPool.getCompletedTaskCount());   //1

        System.out.println("thread in execution - " + threadPool.getActiveCount());
        threadPool.shutdown();
    }
}
/*
 * O/P =>
 *
 * pool thread count - 0
 * finishing a small task
 * Queue size (task waiting in queue) - 2
 * pool thread count - 2
 * CompletedTaskCount - 1
 * thread in execution - 2
 * */