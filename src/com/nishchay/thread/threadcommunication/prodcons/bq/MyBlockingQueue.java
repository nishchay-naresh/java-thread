package com.nishchay.thread.threadcommunication.prodcons.bq;

import java.util.ArrayList;
import java.util.List;

import static com.nishchay.Utils.wait0;

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
            wait0(this);
        }
        // Append specified element to the end of list.
        dataList.add(element);
        this.notifyAll();
    }

    public synchronized T take() {
        while (isEmpty()) {
            wait0(this);
        }
        // picking up the element from start
        this.notifyAll();
        return dataList.remove(0);
    }

}
