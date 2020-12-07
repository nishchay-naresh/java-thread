package com.nishchay.concurrentpkg.condition.prodcons.awaitsignal;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SharedObject {

    private int data;
    private boolean isProdTurn;

    private Lock lock;
    private Condition prodThread;
    private Condition consThread;

    public SharedObject(boolean isProdTurn) {

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
                    e.printStackTrace();
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
                    e.printStackTrace();
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