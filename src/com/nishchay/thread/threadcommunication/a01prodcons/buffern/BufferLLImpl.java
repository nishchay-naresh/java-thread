package com.nishchay.thread.threadcommunication.a01prodcons.buffern;

import java.util.LinkedList;
import java.util.Queue;

/*
 * ArrayList is not thread-safe, but it can be safely used in a shared buffer if all access is guarded by synchronized blocks or methods.
 * For production systems, BlockingQueue is the preferred solution.
 *
 * */
public class BufferLLImpl<T> implements Buffer<T> {
    private final Queue<T> queue = new LinkedList<>();
    private final int capacity;

    public BufferLLImpl(int capacity) {
        this.capacity = capacity;
    }
    @Override
    public synchronized void produce(T item) {
        while (queue.size() == capacity) {
            try {
                this.wait();                // buffer full → producer waits
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        queue.offer(item);
        notify(); // notify consumer
    }


    public synchronized T consume() {
        while (queue.isEmpty()) {
            try {
                this.wait();                // buffer empty → consumer waits
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        T item = queue.poll();
        notify(); // notify producer
        return item;
    }
}