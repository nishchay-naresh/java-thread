package com.nishchay.concurrentpkg.condition.prodcons.singlecondition;


import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class Producer extends Thread {

    private List<Integer> queue;
    private int BUFFER_SIZE;

    private ReentrantLock lock;
    private Condition condition;

    public Producer(List<Integer> queue, int size, ReentrantLock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
        this.queue = queue;
        this.BUFFER_SIZE = size;
    }

    public void run() {
        for (int i = 1; i <= 10; i++) {
            lock.lock();
            while (queue.size() == BUFFER_SIZE) {
                try {
                    condition.await();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            queue.add(i);
            System.out.println("Produced : " + i);
            condition.signal();
            lock.unlock();
        }
    }

}
