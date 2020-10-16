package com.nishchay.thread.synchronize.readwrite;

public class ReadWriteProbDemo {

    public static void main(String[] args) {

        Shared obj = new Shared();
        obj.setX(10);

        // In this case both the threads t1 and t2
        // are sharing the same Sample object obj.
        // Both the threads will try to perform the
        // increment operation simultaneously.

        MyThread t1 = new MyThread(obj);
        MyThread t2 = new MyThread(obj);

        t1.start();
        t2.start();

        // Here main thread called the join operation
        // on t1 and t2. join() operations waits for
        // thread to complete before returning.
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println( obj.getX() );

    }

}
