package com.nishchay.concurrentpkg.pool.a03factory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ExceptionInPool {

    public static void main(String[] args) {

        withGlobalHandler();

    }



    private static void withGlobalHandler() {

        int threadCount = 4;
        ExecutorService fixedThreadPool = getFixedThreadPoolWithExceptionHandler(threadCount);

        Runnable taskThrowException = () -> {
            System.out.println(Thread.currentThread().getName() + " throwing exception from run() method");
            throw new RuntimeException("Boom");
        };

        for (int i = 1; i <= 8; i++) {
            fixedThreadPool.execute(taskThrowException);
        }
        fixedThreadPool.shutdown();
    }

    /*
     *
     *	Thread.UncaughtExceptionHandler
     *	   ↓
     *	ThreadFactory(handler) -> hook for threadName + exceptionHandler
     *	   ↓
     *	ExecutorService(threadFactory)
     *	---------------------------------
     *	Task (Runnable)
     *	   ↓
     *	ExecutorService/ThreadPool
     *	   ↓
     *	Thread
     *	   ↓
     *	If exception → UncaughtExceptionHandler
     *
     * 1. Thread.UncaughtExceptionHandler -> Have an exceptionHandler which can handle the exception for thread
     * 2. ThreadFactory -> Set this exceptionHandler to thread at ThreadFactory level
     * 3. Executors.newFixedThreadPool() -> pass this ThreadFactory to the newFixedThreadPool() constructor
     *
     * */
    private static ExecutorService getFixedThreadPoolWithExceptionHandler(int threadCount) {
        Thread.UncaughtExceptionHandler genericHandler =
                (thread, ex) -> System.err.println("GenericHandler : Thread " + thread.getName() + " crashed: " + ex.getMessage());

        ThreadFactory threadFactoryExceptionHandler =
                (r) -> {
                    final Thread thread = new Thread(r);
                    // Setting a local UncaughtException Handle to the thread reference, Since doing it at factory level s applicable for all the threads of pool
                    thread.setUncaughtExceptionHandler(genericHandler);
                    return thread;
                };
        return Executors.newFixedThreadPool(threadCount, threadFactoryExceptionHandler);
    }

}
