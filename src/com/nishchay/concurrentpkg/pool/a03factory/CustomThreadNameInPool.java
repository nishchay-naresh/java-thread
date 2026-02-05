package com.nishchay.concurrentpkg.pool.a03factory;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/*
 *
 *	1. Why do we need custom thread names in a ThreadPool?
 *	Default thread names look like:
 *			pool-1-thread-3
 *			pool-2-thread-4
 *
 *	Problems:
 *			Hard to debug
 *			Logs are unclear
 *			Monitoring tools are noisy
 *
 *	Custom names make logs readable:
 *			order-processor-1
 *			order-processor-2
 *
 *	2. How ThreadPool creates threads
 *	A ThreadPool never creates threads directly.
 *	Instead, It asks a ThreadFactory to create threads.
 *
 *	So if you want below customization for threads,
 *		custom thread name
 *		thread priority
 *		daemon flag
 *		exception handler
 *	ThreadFactory is the only correct place.
 *
 * */
public class CustomThreadNameInPool {

    public static void main(String[] args) {
        NamedThreadFactory namedThreadFactory = new NamedThreadFactory("payment-worker");
        ExecutorService executor = Executors.newFixedThreadPool(4, namedThreadFactory);

        Runnable task = () -> System.out.println(Thread.currentThread().getName() + ", payment processed");
        executor.execute(task);
        executor.execute(task);

        executor.shutdown();
    }

    static class NamedThreadFactory implements ThreadFactory {
        // using AtomicInteger instead of int, because AtomicInteger is thread safe
        private final AtomicInteger count = new AtomicInteger(1);
        private final String namePrefix;

        public NamedThreadFactory(String namePrefix) {
            this.namePrefix = namePrefix;
        }

        @Override
        public Thread newThread(Runnable r) {
            final Thread t = new Thread(r);
            t.setName(namePrefix + "-" + count.getAndIncrement());
            return t;
        }
    }
}
