package com.nishchay.thread.threadcommunication.sum100.objectlock;


/*
 * This solution is written using wait & notify
 * Locking is based on user defined object Lock ( doing a purpose of Mutable Boolean class )
 * Taking care for the spurious wakeup scenario
 *
 * */
public class Add100WaitNotify {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("---------------Main started---------------");

        Lock lock = new Lock(true);
        Runnable task = new Sum100Task(lock);
        Thread thrd = new Thread(task);
        thrd.start();

        synchronized (lock) {
            while (lock.isChildTurn()) {
                lock.wait();
            }
        }

        System.out.println("---------------Main Ended---------------");
    }
}


