package com.nishchay.thread.basic.setname;

public class SettingGettingThreadName {

    public static void main(String[] args) {

        //Main thread name - main
        System.out.println("Main Thread name - " + Thread.currentThread().getName());

        // user thread default name  - Thread-0, Thread-1, Thread-2 ..so on if not given names
        Thread t0 = new Thread(() -> System.out.println("default name of thread - " + Thread.currentThread().getName()));
        t0.start();

        // user thread default name  - Thread-1, bcus Thread-0 is been used for last thread
        new Thread(new NamedTask()).start();

        // setting up name -  using constructor
        Thread t1 =  new Thread(new NamedTask(),"user thread");
        t1.start();

        // setting up name -  using setName("") method
        Thread t2 =  new Thread(new NamedTask());
        t2.setName("user created thread");
        t2.start();


    }
}
