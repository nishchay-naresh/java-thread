package com.nishchay.thread.threadcommunication.a01prodcons.bq;

import java.util.ArrayList;
import java.util.List;

/*
 * Designing a BlockingQueue using Synchronisation & wait-notify
 * Designed it for the generic data type
 * Underlying data structure is ArrayList
 *
 * */
public class MyBlockingQueue<E> {

    private final List<E> dataList;
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

    public synchronized void put(E element) {
        while (isFull()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        // Append a specified element to the end of the list.
        dataList.add(element);
        this.notifyAll();
    }

    public synchronized E take() {
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
