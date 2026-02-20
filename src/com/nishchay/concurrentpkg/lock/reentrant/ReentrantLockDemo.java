package com.nishchay.concurrentpkg.lock.reentrant;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

    public static void main(String[] args) {

//        reentrantLockAPIMethods();
        counterUsingLock();
    }

    private static void reentrantLockAPIMethods() {
        ReentrantLock lock =  new ReentrantLock();
        System.out.println("lock.isLocked() = " + lock.isLocked());                             // false

        lock.lock();
        System.out.println("lock.isLocked() = " + lock.isLocked());                             // true
        System.out.println("lock.isHeldByCurrentThread() = " + lock.isHeldByCurrentThread());   // true
        System.out.println("lock.isFair() = " + lock.isFair());                                 // false
        System.out.println("lock.getQueueLength() = " + lock.getQueueLength());                 // 0
        System.out.println("lock.getHoldCount() = " + lock.getHoldCount());                     // 1

        lock.lock();
        System.out.println("lock.getHoldCount() = " + lock.getHoldCount());                     // 2

        System.out.println("------ after unlock() ---------");
        lock.unlock();
        System.out.println("lock.getHoldCount() = " + lock.getHoldCount());                     // 1
        System.out.println("lock.isLocked() = " + lock.isLocked());                             // true

        lock.unlock();
        System.out.println("lock.getHoldCount() = " + lock.getHoldCount());                     // 0
        System.out.println("lock.isLocked() = " + lock.isLocked());                             // false
    }


    private static void counterUsingLock() {

        Counter counter = new Counter();

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Final Count: " + counter.getCount());
    }

    static class Counter {

        private int count = 0;
        private final Lock lock = new ReentrantLock();

        public void increment() {
            lock.lock();
            try {
                count++;
            } finally {
                lock.unlock();   // Always unlock in finally!
            }
        }

        public int getCount() {
            return count;
        }
    }

}
