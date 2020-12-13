package com.nishchay.thread.threadcommunication.prodcons.bq;

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
            waitThread();
        }
        if(isEmpty()){
            this.notify();
        }
        // Append specified element to the end of list.
        dataList.add(element);
    }

    public synchronized T take() {
        while (isEmpty()) {
            waitThread();
        }
        if(isFull()){
            this.notify();
        }
        // picking up the element from start
        return dataList.remove(0);
    }

    public void waitThread(){
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
