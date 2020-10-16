package com.nishchay.thread.threadcommunication.sum100.threadlock;

/*
* This solution is written using wait & notify
* Locking is based on the Thread class object itself
* Not taking care for the spurious wakeup scenario
*
* */
public class Add100Thread {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("---------------Main started---------------");

        Sum100Thread threadObject = new Sum100Thread();
        threadObject.start();

        synchronized (threadObject){
            System.out.println("main thread going for wait");
            threadObject.wait();
            System.out.println("main thread got notification");
        }
        System.out.println("-----------------Main Ended---------------");

    }
}
