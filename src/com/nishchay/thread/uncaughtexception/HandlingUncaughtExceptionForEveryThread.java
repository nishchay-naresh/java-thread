package com.nishchay.thread.uncaughtexception;

/*
 *  Here we are setting up a generic exception handler for all of the thread
 *  It will handle all UncaughtException in entire application for all the threads
 * */
public class HandlingUncaughtExceptionForEveryThread {


    public static void main(String[] args) {

        // setting up a generic exception handler to handle UncaughtException in entire application
        Thread.setDefaultUncaughtExceptionHandler(new GenericExceptionHandler());

        Thread thread;

        for (int i = 1; i <= 4; i++) {
            thread = new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " throwing exception from run() method");
                throw new RuntimeException();
            });
            thread.start();
        }
    }

}
