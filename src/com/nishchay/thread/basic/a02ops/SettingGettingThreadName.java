package com.nishchay.thread.basic.a02ops;

public class SettingGettingThreadName {

    public static void main(String[] args) {

        //Main thread name - main
        System.out.println("Main Thread name - " + Thread.currentThread().getName());

        Runnable task = () -> System.out.println("user thread default name - " + Thread.currentThread().getName());

        // user thread default name  - Thread-0, Thread-1, Thread-2.... so on if not given names
        Thread t0 = new Thread(task);
        t0.start();

        // user thread default name - Thread-1, because Thread-0 is used for last thread
        Thread t1 = new Thread(task);
        t1.start();

        // setting up name - using constructor
        Thread t2 =  new Thread(task,"thread foo");
        t2.start();

        // setting up name -  using setName("") method
        Thread t3 =  new Thread(task);
        t3.setName("thread bar");
        t3.start();
    }
}
