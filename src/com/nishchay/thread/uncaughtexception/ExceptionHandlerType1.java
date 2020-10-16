package com.nishchay.thread.uncaughtexception;

import java.lang.Thread.UncaughtExceptionHandler;

public class ExceptionHandlerType1 implements UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        String msg = "ExceptionHandlerType1 : Catching exception thrown from run() " + e + " By Thread " + t.getName();
        System.out.println("Error - " + msg);
        throw new RuntimeException(msg, e);
    }
}