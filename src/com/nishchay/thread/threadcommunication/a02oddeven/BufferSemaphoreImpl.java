package com.nishchay.thread.threadcommunication.a02oddeven;

import com.nishchay.Utils;

import java.util.concurrent.Semaphore;

public class BufferSemaphoreImpl implements BufferSingle {

    private final Semaphore oddSemaphore;
    private final Semaphore evenSemaphore;

    public BufferSemaphoreImpl() {
        // making sure the odd thread should get the first run
        oddSemaphore = new Semaphore(1);
        evenSemaphore = new Semaphore(0);
    }

    public void printOdd(int num) {
        try {
            oddSemaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " generating : " + num);
            Utils.sleep0(500);
            evenSemaphore.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void printEven(int num) {
        try {
            evenSemaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " generating : " + num);
            Utils.sleep0(500);
            oddSemaphore.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}