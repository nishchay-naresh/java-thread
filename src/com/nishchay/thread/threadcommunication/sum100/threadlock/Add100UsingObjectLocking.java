package com.nishchay.thread.threadcommunication.sum100.threadlock;

import java.util.stream.IntStream;

public class Add100UsingObjectLocking {

    public static void main(String[] args) throws InterruptedException {


        System.out.println("---------------Main started---------------");

        Object lock = new Object();
        Sum100Thread t1 = new Sum100Thread(lock);
        t1.start();

        synchronized (lock) {
            lock.wait();
        }
        System.out.println("-----------------Main Ended---------------");

    }


    static class Sum100Thread extends Thread {

        Object lock;

        Sum100Thread(Object lock) {
            this.lock = lock;
        }

        @Override
        public void run() {

            synchronized (lock) {
                sum100();
                lock.notify();
            }
        }

        private void sum100() {
            int sum = IntStream.rangeClosed(1, 100).sum();
            System.out.println("sum of 100 = " + sum);
        }
    }
}



