package com.nishchay.thread.basic.join;

import com.nishchay.Utils;

public class JoinExample {

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(JoinExample::threadTask, "thread 1");
        Thread t2 = new Thread(JoinExample::threadTask, "thread 2");
        Thread t3 = new Thread(JoinExample::threadTask, "thread 3");

        t1.start();
        t1.join();

        t2.start();
        t2.join();

        t3.start();
        t3.join();
    }

    private static void threadTask() {
        System.out.println("Thread started :- " + Thread.currentThread().getName());
        Utils.sleep0(1000);
        System.out.println("  Thread ended :- " + Thread.currentThread().getName());
    }
}
/*
 * O/P=>
 *	Thread started :- thread 1
 *	  Thread ended :- thread 1
 *	Thread started :- thread 2
 *	  Thread ended :- thread 2
 *	Thread started :- thread 3
 *	  Thread ended :- thread 3
 * */