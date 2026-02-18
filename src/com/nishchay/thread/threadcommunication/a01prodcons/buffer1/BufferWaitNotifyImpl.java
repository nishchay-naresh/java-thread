package com.nishchay.thread.threadcommunication.a01prodcons.buffer1;

import com.nishchay.Utils;

/*
 * Producer - Consumer problem with single buffer size = 1 using wait() and notify().
 * Strict alternation (Producer → Consumer → Producer → Consumer).
 *
 *  The buffer uses a boolean flag to control turn-based execution.
 *  Both produce and consume methods are synchronized, ensuring mutual exclusion.
 *  Threads wait using wait() when it is not their turn and notify the other thread after finishing.
 *
 *
 * Why This Works Without volatile
 * Because of synchronized. Since both methods  - produce(), consume() are synchronized
 *
 *  What synchronized Guarantee?, synchronized gives two things:
 *  -   Mutual Exclusion (only one thread inside at a time)
 *  -   Memory Visibility (happens-before guarantee)
 *
 * */
public class BufferWaitNotifyImpl implements BufferSingle {

    private int data;
    private boolean isProdTurn;

    public BufferWaitNotifyImpl() {
        this.data = -1;
        this.isProdTurn = true;
    }

    public synchronized void produce(int item) {
        // why did we use while(busy == false) instead of if(busy == false) ?
        // because of Spurious Wake-ups (Thread can wake up without notify)
        while (!isProdTurn) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        data = item;
        Utils.sleep0(500);
        isProdTurn = false;
        notify();
    }

    public synchronized int consume() {
        while (isProdTurn) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        Utils.sleep0(500);
        isProdTurn = true;
        notify();
        return data;
    }
}