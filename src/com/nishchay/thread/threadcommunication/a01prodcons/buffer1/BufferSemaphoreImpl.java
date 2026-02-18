package com.nishchay.thread.threadcommunication.a01prodcons.buffer1;

import com.nishchay.Utils;

import java.util.concurrent.Semaphore;

public class BufferSemaphoreImpl implements BufferSingle {

    private int data;
    private final Semaphore producedSemaphore;
    private final Semaphore consumedSemaphore;

    public BufferSemaphoreImpl() {
        // making sure the producer thread should get the first run
        producedSemaphore = new Semaphore(1);
        consumedSemaphore = new Semaphore(0);
    }

    // no need to synchronize this method Semaphore will take care of execution order
    @Override
    public void produce(int item) {
        try {
            producedSemaphore.acquire();
            data = item;
            Utils.sleep0(500);
            consumedSemaphore.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int consume() {
        try {
            consumedSemaphore.acquire();
            Utils.sleep0(500);
            producedSemaphore.release();
            return data;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}