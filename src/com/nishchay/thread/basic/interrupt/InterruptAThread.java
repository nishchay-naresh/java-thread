package com.nishchay.thread.basic.interrupt;

import java.util.concurrent.*;

/*
* ==================== how to stop a thread ======================
*
* There is no way, we can stop a thread. No method as such, stop()/cancel()
* Co-operative mechanism, we can’t force a thread to suspend
* Interrupts are co-operative mechanism for indicating stop signal to a thread
*
*
* https://www.youtube.com/watch?v=-7ZB-jpaPPo&ab_channel=DefogTech
* */
public class InterruptAThread {

    public static void main(String[] args) {

        interruptNonBlockingThreadEx();
//        interruptSleepingThread();
//        interruptWaitingThread();

    }

    private static void interruptNonBlockingThreadEx(){
//        interruptNonBlockingThreadRunnable();
        interruptNonBlockingThreadCallable();
    }

    /*
    * Here in this approach , problem is that
    * You caller thread will never know that , how your worker thread is being finished or returned
    *  - it's been completed successfully
    *  - or it's been interrupted
    * So we need to single back the main thread for the interruption. That signal is nothing but the - InterruptedException
    * Since InterruptedException is checked one, so we can't do this with Runnable, we have to move to Callable interface.
    *
    * */
    private static void interruptNonBlockingThreadRunnable() {

        Thread t = new Thread(() -> {

            for (int i = 1; i <= 500; i++) {
                System.out.println("Counting - " + i);
                // polling for interrupt
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Interrupted status- " + Thread.currentThread().isInterrupted());
                    // resetting the status
                    Thread.interrupted();
                    return;
                }
            }

        });

        t.start();

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t.interrupt();
    }

    private static void interruptNonBlockingThreadCallable() {

        // FutureTask is a concrete class that implements both Runnable and Future
        FutureTask<Integer> futureTask = new FutureTask<>(new CountTask());

        Thread t = new Thread(futureTask);
        t.start();

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t.interrupt();

        try {
            futureTask.get();
            System.out.println("thread completed");
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("thread is been interrupted");
            throw new RuntimeException("Thread interrupted..." + e);
        }
    }

   static class CountTask implements Callable<Integer>{

       @Override
       public Integer call() throws Exception {

           for (int i = 1; i <= 1000; i++) {
               System.out.println("Counting - " + i);

               // polling for interrupt
               if (Thread.currentThread().isInterrupted()) {
                   throw  new InterruptedException();

               }
           }
           return null;
       }
   }

    private static void interruptSleepingThread() {

        Thread t = new Thread(() -> {
            System.out.println("started");
            try {
                Thread.sleep(3 * 1000);
                System.out.println("sleeping");
            } catch (InterruptedException e) { // java.lang.InterruptedException: sleep interrupted
                System.out.println(e.toString());
                throw new RuntimeException("Thread interrupted..." + e);
            }
            System.out.println("finished");
        });

        t.start();
        t.interrupt();

    }

    private static void interruptWaitingThread() {

        Object lock = new Object();

        Thread t = new Thread(() -> {
            System.out.println("started");
            try {
                synchronized (lock) {
                    lock.wait();
                }
                System.out.println("waiting");
            } catch (InterruptedException e) { // java.lang.InterruptedException
                System.out.println(e.toString());
                throw new RuntimeException("Thread interrupted..." + e);
            }
            System.out.println("finished");
        });

        t.start();
        t.interrupt();

    }

}

/*
O/P =>

Counting - 1,2...50+..73
Counting - 72
thread is been interrupted
Exception in thread "main" java.lang.RuntimeException: Thread interrupted...java.util.concurrent.ExecutionException: java.lang.InterruptedException
	at com.nishchay.thread.basic.interrupt.InterruptAThread.interruptNonBlockingThreadCallable(InterruptAThread.java:88)
	at com.nishchay.thread.basic.interrupt.InterruptAThread.interruptNonBlockingThreadEx(InterruptAThread.java:27)
	at com.nishchay.thread.basic.interrupt.InterruptAThread.main(InterruptAThread.java:19)

*/
