package com.nishchay.thread.threadcommunication.a01prodcons.bq;

import java.util.ArrayList;
import java.util.List;

/*
 * Designing a BlockingQueue using Synchronisation & wait-notify
 * Designed it for the generic data type
 * Underlying data structure is ArrayList
 *
 * */
public class MyBlockingQueue<T> {

    private final List<T> dataList;
    private final int limit;

    public MyBlockingQueue(int size) {
        this.limit = size;
        dataList = new ArrayList<>(limit);
    }

    public boolean isFull() {
        return dataList.size() == limit;
    }

    public boolean isEmpty() {
        return dataList.size() == 0;
    }

    public synchronized void put(T element) {
        while (isFull()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        // Append specified element to the end of list.
        dataList.add(element);
        this.notifyAll();
    }

    public synchronized T take() {
        while (isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        // picking up the element from start
        this.notifyAll();
        return dataList.remove(0);
    }


}
