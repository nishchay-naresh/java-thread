package com.nishchay.concurrentpkg.pool.fixedpoolrunnable.factory;

import java.lang.Thread.UncaughtExceptionHandler;

public class GenericExceptionHandler implements UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("Uncaught Exception occurred on thread: " + t.getName());
        System.out.println("Exception message: " + e.getMessage());
    }
}