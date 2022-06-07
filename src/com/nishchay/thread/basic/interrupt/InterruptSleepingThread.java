package com.nishchay.thread.basic.interrupt;

import java.util.concurrent.Callable;

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
public class InterruptSleepingThread {

    public static void main(String[] args) {

        InterruptSleepingThread ref = new InterruptSleepingThread();
        ref.interruptNonBlockingThread();
//        ref.interruptSleepingThread();
//        ref.interruptWaitingThread();

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
    private void interruptNonBlockingThread() {

        Thread t = new Thread(() -> {

            for (int i = 1; i <= 1000; i++) {
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

   static class Integer implements Callable<Integer>{

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

    private void interruptSleepingThread() {

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

    private void interruptWaitingThread() {

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
O/P => interruptNonBlockingThread()
        Counting - 1
        Counting - 2
        Counting - 3
        Counting - 4
        Counting - 5
        Counting - 6
        Counting - 7
        Interrupted status- true
        Counting - 8
        Interrupted status- true
        Counting - 9
        Interrupted status- true
        Counting - 10
        Interrupted status- true
*/
