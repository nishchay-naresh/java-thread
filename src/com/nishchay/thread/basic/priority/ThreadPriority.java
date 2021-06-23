package com.nishchay.thread.basic.priority;

public class ThreadPriority {

    /*
     *      setPriority(1-10) argument value can be 1 to 10, else it will throw IllegalArgumentException
     *         if (newPriority > MAX_PRIORITY || newPriority < MIN_PRIORITY) {
     *                throw new IllegalArgumentException();
     *          }
     *
     * */
    public static void main(String[] args) {

        // By default Main thread priority will be Thread.NORM_PRIORITY/5
        Thread mainThrd = Thread.currentThread();
        System.out.println("main Thread priority : " + mainThrd.getPriority());

        // All the threads gets spawn by main threads will inherits the same priority Thread.NORM_PRIORITY/5
        Thread t1 = new Thread();
        System.out.println("Thread(t1) priority : " + t1.getPriority());

        // Change thread t1 priority to minimum
        t1.setPriority(Thread.MIN_PRIORITY);
        System.out.println("Thread(t1) priority changed to : " + t1.getPriority());

        Thread t2 = new Thread();
        t2.setName("SimpleThread");
        t2.setPriority(Thread.NORM_PRIORITY + 2); // 5 + 2 = 7
        System.out.println("Thread(t2) priority : " + t2.getPriority());

        System.out.println(t2);// Thread[SimpleThread,7,main], [threadName,priority,threadGroup]

        mainThrd.setPriority(Thread.MAX_PRIORITY);
        System.out.println("main Thread priority changed to : " + mainThrd.getPriority());


        // Running a thread based on thread priority is not a good practice
    }
}