package com.nishchay.thread.threadcommunication.a01prodcons.buffern;

import com.nishchay.Utils;
import com.nishchay.thread.threadcommunication.a01prodcons.buffer1.BufferSingle;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class BufferSemaphoreImpl {

    Queue<Integer> buffer;
    Semaphore empty, full, mutex;

    public BufferSemaphoreImpl() {
        int capacity = 5;
        buffer = new LinkedList<>();

        empty = new Semaphore(capacity);
        full = new Semaphore(0);
        mutex = new Semaphore(1);
    }

    public void produce(int item) {
        try {
            empty.acquire();   // wait for empty slot
            mutex.acquire();   // enter critical section
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        buffer.offer(item);
        mutex.release();   // exit critical section
        full.release();    // signal item available
    }

    public int consume() {
        try {
            full.acquire();    // wait for item
            mutex.acquire();   // enter critical section
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        int item = buffer.poll();
        mutex.release();   // exit critical section
        empty.release();   // signal space available
        return item;
    }
}