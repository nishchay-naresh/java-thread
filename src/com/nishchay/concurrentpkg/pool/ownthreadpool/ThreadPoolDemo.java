package com.nishchay.concurrentpkg.pool.ownthreadpool;

import com.nishchay.Utils;

public class ThreadPoolDemo {

    public static void main(String[] args) throws Exception {

        ThreadPool pool = new ThreadPool(10, 10);

        // now lets submit task
        pool.submitTask(() -> {
            System.out.println("Task 1 executed by " + Thread.currentThread().getName());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("Task1 interrupted by " + Thread.currentThread().getName());
            }
            System.out.println("Task 1 Completed....");
        });

        pool.submitTask(() -> {
            System.out.println("Task 2 executed by " + Thread.currentThread().getName());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("Task 2 interrupted by " + Thread.currentThread().getName());
            }
            System.out.println("Task 2 Completed....");
        });

        Utils.sleep0(5000);
        pool.shutDown();
    }
}
