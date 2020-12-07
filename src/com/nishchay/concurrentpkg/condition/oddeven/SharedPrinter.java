package com.nishchay.concurrentpkg.condition.oddeven;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SharedPrinter {

    private boolean oddThreadTurn;
    private int oddThreadValue, evenThreadValue;

    private final Lock lock;
    private final Condition prodThread;
    private final Condition consThread;


    public SharedPrinter(int oddThreadValue, int evenThreadValue, boolean oddThreadTurn) {
        this.oddThreadValue = oddThreadValue;
        this.evenThreadValue = evenThreadValue;
        this.oddThreadTurn = oddThreadTurn;

        // lock and condition variable initialization
        lock = new ReentrantLock();
        prodThread = lock.newCondition();
        consThread = lock.newCondition();
    }

    public void printOdd() {

        lock.lock();
        try {
            while (!oddThreadTurn) {
                try {
                    prodThread.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + " - " + oddThreadValue);
            oddThreadValue = oddThreadValue + 2;
            oddThreadTurn = false;
            consThread.signal();
        } finally {
            lock.unlock();
        }
    }

    public synchronized void printEven() {

        lock.lock();
        try {
            while (oddThreadTurn) {
                try {
                    consThread.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + " - " + evenThreadValue);
            evenThreadValue = evenThreadValue + 2;
            oddThreadTurn = true;
            prodThread.signal();
        } finally {
            lock.unlock();
        }
    }

}
