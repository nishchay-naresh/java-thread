package com.nishchay.concurrentpkg.condition.prodcons.buffern;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BufferWithTwoCondition {

    private final int capacity;
    private final List<Integer> bufferList;

    private final Lock lock;
    private final Condition prodThread;
    private final Condition consThread;

    public BufferWithTwoCondition(int size) {
        this.capacity = size;
        this.bufferList = new ArrayList<>(size);

        // lock and condition variable initialization
        lock = new ReentrantLock();
        prodThread = lock.newCondition();
        consThread = lock.newCondition();
    }

    public boolean isFull() {
        return bufferList.size() == capacity;
    }

    public boolean isEmpty() {
        return bufferList.size() == 0;
    }
    
    public void put(int data) {
        lock.lock();
        try {
            while (isFull()) {
                try {
                    prodThread.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            // putting the element at the end
            bufferList.add(data);
            consThread.signal();
        } finally {
            lock.unlock();
        }
    }

    public int take() {
        lock.lock();
        try {
            while (isEmpty()) {
                try {
                    consThread.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            // picking up the element from start
            int data = bufferList.remove(0);
            prodThread.signal();
            return data;
        } finally {
            lock.unlock();
        }
    }

}
