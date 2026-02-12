package com.nishchay.thread.basic.a02ops;

public class ThreadPriority {

    /*
     *  thread priority - can be a number between 1-10
     *     public final static int MIN_PRIORITY = 1;
     *     public final static int NORM_PRIORITY = 5;
     *     public final static int MAX_PRIORITY = 10;
     *
     *
     * setPriority(1-10) argument value can be 1 to 10, else it will throw IllegalArgumentException
     *         if (newPriority > MAX_PRIORITY || newPriority < MIN_PRIORITY) {
     *                throw new IllegalArgumentException();
     *          }
     *
     * Running a thread based on thread priority is not a good practice
     * */
    public static void main(String[] args) {

        // By default, Main thread priority will be Thread.NORM_PRIORITY/5
        Thread mainThread = Thread.currentThread();
        System.out.println("main Thread priority : " + mainThread.getPriority());

        // All the threads gets spawn by main threads will inherit the same priority Thread.NORM_PRIORITY/5
        Thread t1 = new Thread(() -> System.out.println("java"));
        System.out.println("Thread(t1) priority : " + t1.getPriority());

        // Change thread t1 priority to a minimum
        t1.setPriority(Thread.MIN_PRIORITY);
        System.out.println("Thread(t1) priority changed to : " + t1.getPriority());

        Thread t2 = new Thread(() -> System.out.println("java"), "SimpleThread");
        t2.setPriority(Thread.NORM_PRIORITY + 2); // 5 + 2 = 7
        System.out.println("Thread(t2) priority : " + t2.getPriority());

        System.out.println(t2);// Thread[SimpleThread,7,main], [threadName,priority,threadGroup]

        mainThread.setPriority(Thread.MAX_PRIORITY);
        System.out.println("main Thread priority changed to : " + mainThread.getPriority());
    }
}