package com.nishchay.concurrentpkg.atomic.counter;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class CounterUsingLock  implements  ICounter{

    private ReadWriteLock lock = new ReentrantReadWriteLock();

    private int count = 0;

    public void increment() {
        lock.writeLock().lock();
        try {
            count = count + 1;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public int getCount() {
        lock.readLock().lock();
        try {
            return count;
        } finally {
            lock.readLock().unlock();
        }
    }
}