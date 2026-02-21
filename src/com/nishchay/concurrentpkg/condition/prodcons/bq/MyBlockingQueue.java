package com.nishchay.concurrentpkg.condition.prodcons.bq;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueue<T> {

    private final Queue<T> queue = new LinkedList<>();
    private final int capacity;

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public MyBlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public void put(T item){
        lock.lock();
        try {
            while (queue.size() == capacity) {
                try {
                    notFull.await();   // wait until space available
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            queue.add(item);
            notEmpty.signal();    // wake up one consumer
        } finally {
            lock.unlock();
        }
    }

    public T take(){
        lock.lock();
        try {
            while (queue.isEmpty()) {
                try {
                    notEmpty.await();  // wait until item available
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            T item = queue.remove();
            notFull.signal();     // wake up one producer
            return item;
        } finally {
            lock.unlock();
        }
    }

}
