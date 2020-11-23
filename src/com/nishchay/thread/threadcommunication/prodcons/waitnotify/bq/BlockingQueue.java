package com.nishchay.thread.threadcommunication.prodcons.waitnotify.bq;

import java.util.ArrayList;
import java.util.List;

/*
 * Designing a BlockingQueue using Synchronisation & wait-notify
 * Designed it for the generic data type
 * Underlying data structure is ArrayList
 *
 * */
public class BlockingQueue<T> {

    private List<T> dataList;
    private int limit;

    public BlockingQueue(int size) {
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
                e.printStackTrace();
            }
        }
        // Append specified element to the end of list.
        dataList.add(element);
        this.notify();
    }

    public synchronized T take() {

        while (isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // picking up the element from start
        T message = dataList.remove(0);
        this.notify();
        return message;
    }

}
