package com.nishchay.concurrentpkg.condition.pc.singlecondition;

import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class Consumer extends Thread {

    private List<Integer> queue;

    private ReentrantLock lock;
    private Condition condition;

    public Consumer(List<Integer> queue, ReentrantLock lock, Condition condition) {
        this.queue = queue;
        this.lock = lock;
        this.condition = condition;
    }

    public void run() {
        for (int i = 1; i <= 10; i++) {
            lock.lock();
            while (queue.size() == 0) {
                try {
                    condition.await();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            System.out.println("Consumed : " + queue.remove(0));
            condition.signal();
            lock.unlock();
        }
    }
}