package com.nishchay.concurrentpkg.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

    public static void main(String[] args) {

        ReentrantLock lock =  new ReentrantLock();

        lock.lock();
        lock.lock();
        System.out.println("lock.isLocked() = " + lock.isLocked()); // true
        System.out.println("lock.isHeldByCurrentThread() = " + lock.isHeldByCurrentThread());//true
        System.out.println("lock.getQueueLength() = " + lock.getQueueLength());//0

        lock.unlock();
        System.out.println("lock.getHoldCount() = " + lock.getHoldCount()); // 1
        System.out.println("lock.isLocked() = " + lock.isLocked());// true

        lock.unlock();
        System.out.println("lock.isLocked() = " + lock.isLocked());// false
        System.out.println("lock.isFair() = " + lock.isFair());// false
    }
}
