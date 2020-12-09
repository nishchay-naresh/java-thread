package com.nishchay.concurrentpkg.pool.fixedpoolrunnable.factory;

import java.util.concurrent.ThreadFactory;

public class ThreadFactoryExceptionHandler implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        final Thread thread = new Thread(r);
        thread.setUncaughtExceptionHandler(new GenericExceptionHandler());
        return thread;
    }

}

