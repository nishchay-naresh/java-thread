package com.nishchay.thread.uncaughtexception;

import java.lang.Thread.UncaughtExceptionHandler;

public class ExceptionHandlerType2 implements UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        String msg = "ExceptionHandlerType2 : Catching exception thrown from run() " + e + " By Thread " + t.getName();
        System.out.println("Error - " + msg);
        throw new RuntimeException(msg, e);
    }
}