package com.nishchay.thread.basic.interrupt;

public class InterruptSleepingThread {

    public static void main(String[] args) {

        InterruptSleepingThread ref = new InterruptSleepingThread();
//        ref.interruptSleepingThread();
//        ref.interruptWaitingThread();
        ref.interruptNonBlockingThread();
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

    private void interruptNonBlockingThread() {

        Thread t = new Thread(() -> {

            for (int i = 1; i <= 10; i++) {
                System.out.println("Counting - " + i);
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Interrupted status- " + Thread.currentThread().isInterrupted());
                }
            }

        });

        t.start();

        try {
            Thread.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t.interrupt();
    }


}

/*O/P => interruptNonBlockingThread()
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
