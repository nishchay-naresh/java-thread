package com.nishchay.thread.threadcommunication.sum100;

import java.util.stream.IntStream;

import static com.nishchay.Utils.sleep0;

public class Sum100Problem {

    public static void main(String[] args) throws InterruptedException {

        helloWorldUsingThread();
        sumUsingJoin();
        System.out.println("####################");
        sumUsingThreadState();
        System.out.println("####################");
        sumUsingObjectLock();
    }

    private static void helloWorldUsingThread() {
        System.out.println("----Main started----");
        Thread thread = new Thread(() -> System.out.println("hello"));
        thread.start();
        System.out.println("----Main Ended----");
    }

    private static void sum100() {
        int sum = IntStream.rangeClosed(1, 100).sum();
        System.out.println("sum of 100 = " + sum);
    }

    /*
    * Approach 1 : Using Thread.join()
    *
    * */
    private static void sumUsingJoin() throws InterruptedException {
        System.out.println("----Main started----");
        Thread thread = new Thread(Sum100Problem::sum100);
        thread.start();
        thread.join();
        System.out.println("----Main Ended----");
    }

    /*
     * Approach 2 : Using Thread.State enum for thread state completion
     *
     * Since we have the thread reference here in main
     * So checking for user thread completion here in main by using Thread.State enum, till then waiting
     *
     * */
    private static void sumUsingThreadState(){
        System.out.println("----Main started----");
        Thread thread = new Thread(Sum100Problem::sum100);
        thread.start();

        while(!thread.getState().equals(Thread.State.TERMINATED)){
            sleep0(1000);
        }
        System.out.println("----Main Ended----");
    }

    /*
     * Approach 3 : Using synchronization, Object locking concept using wait() & notify()
     *
     * Main thread is waiting for the signal from user thread.
     * User thread will send a single upon its completion using isFinished flag
     * till then main will wait, main will resume its execution upon receiving this signal
     * */
    private static void sumUsingObjectLock() throws InterruptedException {

        System.out.println("----Main started----");

        final Object lock = new Object();
        boolean isFinished = false;
        Sum100Thread sumTask = new Sum100Thread(lock, isFinished);
        Thread t1 = new Thread(sumTask);
        t1.start();

        synchronized (lock) {
            while (!sumTask.isFinished()) {
                lock.wait();
            }
        }
        System.out.println("----Main Ended----");
    }


    static class Sum100Thread implements Runnable {

        private final Object lock;
        boolean isFinished;

        // getter is very imp here, else we will always read the local variable value and loop will never terminate
        public boolean isFinished() {
            return isFinished;
        }

        Sum100Thread(Object lock, boolean isFinished) {
            this.lock = lock;
            this.isFinished = isFinished;
        }

        @Override
        public void run() {
            synchronized (lock) {
                sum100(); // inner class can access the outer class method
                isFinished = true;
                lock.notify();
            }
        }
    }

}
/*
 * O/P =>
 *
 *	----Main started----
 *	sum of 100 = 5050
 *	----Main Ended----
 *	####################
 *	----Main started----
 *	sum of 100 = 5050
 *	----Main Ended----
 *	####################
 *	----Main started----
 *	sum of 100 = 5050
 *	----Main Ended----
 *
*/


