package com.nishchay.thread.basic.setname;

public class SettingGettingThreadName {

    public static void main(String[] args) {

        //Main thread name - main
        System.out.println("Main Thread name - " + Thread.currentThread().getName());

        // user default name  - Thread-0
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
