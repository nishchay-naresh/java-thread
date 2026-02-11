package com.nishchay.thread.synchronize.sum100;

import com.nishchay.Utils;

/*
* This solution is written using wait & notify
* Locking is based on the Thread class object itself
* Not taking care for the spurious wakeup scenario
*
* */
public class Sum100ThreadObjectLocking {

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

    static class Sum100Thread extends Thread {

        @Override
        public void run() {
            synchronized (this) {
                System.out.println("child thread execution started");
                Utils.sum100();
                System.out.println("child thread notifying main thread");
                this.notify();
            }
        }

    }
}
