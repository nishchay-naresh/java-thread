package com.nishchay.thread.synchronize.netpractice;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Pitch {

    // using synchronization keyword
/*
    public void practice(String playerName) {
        synchronized (this) {
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + " Currently practicing - " + playerName);
            }
        }
    }
*/



	public void practice(String name) {
        // using reentrant lock
        Lock lock = new ReentrantLock();

        lock.lock();
        try{
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + " Currently practicing - " + name);
            }
        }finally{
            lock.unlock();
        }
	}

}