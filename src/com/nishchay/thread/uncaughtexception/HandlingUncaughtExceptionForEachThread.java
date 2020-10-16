package com.nishchay.thread.uncaughtexception;

/*
 *  Here we are setting up a specific exception handler for each of the thread
 * */
public class HandlingUncaughtExceptionForEachThread {

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " throwing exception from run() method");
            throw new RuntimeException();
        });
        t1.setUncaughtExceptionHandler(new ExceptionHandlerType1());
        t1.start();

        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " throwing exception from run() method");
            throw new RuntimeException();
        });
        t2.setUncaughtExceptionHandler(new ExceptionHandlerType2());
        t2.start();
    }
}


