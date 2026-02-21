package com.nishchay.concurrentpkg.condition.prodcons.bq;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


/*
* Basic blocking queue using ReentrantLock + Condition
*
* ================= MyBlockingQ structure =========================
public class MyBlockingQ<E> {

    private final List<E> queue;
    private final int capacity;

    private final ReentrantLock lock = new ReentrantLock();

    public MyBlockingQ(int capacity) {
        queue = new ArrayList<>(capacity);
        this.capacity = capacity;
    }

    public void put(E e){
        lock.lock();
        try {
            queue.addLast(e); <-- block the thread if queue is full
        } finally {
            lock.unlock();
        }
    }

    public E take(){
        lock.lock();
        try {
            E e = queue.removeFirst(); <-- block the thread if queue is empty
            return e;
        } finally {
            lock.unlock();
        }
    }
}
*
* Used while instead of if
*       while (list.size() == capacity)
* ✔ Correct — protects against spurious wake-ups.
*
* Condition name should describe when a thread can continue, not when it blocks.
*
* */
public class MyBlockingQ<E> {

    private final Deque<E> queue;
    private final int capacity;

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public MyBlockingQ(int capacity) {
        queue = new ArrayDeque<>();
        this.capacity = capacity;
    }

    public void put(E e) throws InterruptedException {
        lock.lock();
        try {
            // Read it in English: If queue is full → wait until NOT full.
            while (queue.size() == capacity) {
                notFull.await();  // wait until a list is NOT full
            }
            queue.addLast(e);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public E take() throws InterruptedException {
        lock.lock();
        try {
            // Read it in English: If queue is empty → wait until NOT empty.
            while (queue.isEmpty()) {
                notEmpty.await();  // wait until a list is NOT empty
            }
            E e = queue.removeFirst();
            notFull.signal();
            return e;
        } finally {
            lock.unlock();
        }
    }
}
