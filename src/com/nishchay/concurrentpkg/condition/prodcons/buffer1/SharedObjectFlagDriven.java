package com.nishchay.concurrentpkg.condition.prodcons.buffer1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// this clas sis same as - BufferWithTwoCondition.java / MyBlockingQueue.java
// Only difference, the switching condition is not based on array size, instead switching based on a boolean flag here
public class SharedObjectFlagDriven {

    private int data;
    private boolean isProdTurn;

    private final Lock lock;
    private final Condition prodThread;
    private final Condition consThread;

    public SharedObjectFlagDriven(boolean isProdTurn) {

        this.isProdTurn = isProdTurn;

        // lock and condition variable initialization
        lock = new ReentrantLock();
        prodThread = lock.newCondition();
        consThread = lock.newCondition();
    }


    public void produce(int value) {
        lock.lock();
        try {
            while (!isProdTurn) {
                try {
                    prodThread.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            data = value;
            isProdTurn = false;
            consThread.signal();
        } finally {
            lock.unlock();
        }
    }

    public int consume() {

        lock.lock();
        try {
            while (isProdTurn) {
                try {
                    consThread.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            isProdTurn = true;
            prodThread.signal();
            return data;
        } finally {
            lock.unlock();
        }
    }

}