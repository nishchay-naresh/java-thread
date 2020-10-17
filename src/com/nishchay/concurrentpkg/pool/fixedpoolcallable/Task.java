package com.nishchay.concurrentpkg.pool.fixedpoolcallable;

import java.util.concurrent.Callable;

class Task implements Callable<String> {
   
    @Override
    public String call() throws Exception {
        Thread.sleep(4 * 1000);
        return "Result String returned by - " + Thread.currentThread().getName();
    }
}