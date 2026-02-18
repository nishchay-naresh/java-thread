package com.nishchay.thread.threadcommunication.a01prodcons.buffern;

import java.util.ArrayList;
import java.util.List;

/*
 * ArrayList is not thread-safe, but it can be safely used in a shared buffer if all access is guarded by synchronized blocks or methods.
 * For production systems, BlockingQueue is the preferred solution.
 *
 * */
public class BufferALImpl<T> implements Buffer<T> {
    private final List<T> list = new ArrayList<>();
    private final int capacity;

    public BufferALImpl(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public synchronized void produce(T item) {
        while (list.size() == capacity) {
            try {
                this.wait(); // buffer full → producer waits
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        // putting the element at the end
        list.add(item);
        notify(); // notify consumer
    }

    public synchronized T consume() {
        while (list.isEmpty()) {
            try {
                this.wait();  // buffer empty → consumer waits
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        // picking up the element from start
        T item = list.remove(0);
        notify(); // notify producer
        return item;
    }
}