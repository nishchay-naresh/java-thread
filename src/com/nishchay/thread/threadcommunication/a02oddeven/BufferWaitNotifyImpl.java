package com.nishchay.thread.threadcommunication.a02oddeven;

public class BufferWaitNotifyImpl implements BufferSingle{

    private boolean isOddThreadTurn = true;

    public synchronized void printOdd(int num) {

        while (!isOddThreadTurn) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(Thread.currentThread().getName() + " generating : " + num);
        isOddThreadTurn = false;
        notifyAll();
    }

    public synchronized void printEven(int num) {
        while (isOddThreadTurn) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(Thread.currentThread().getName() + " generating : " + num);
        isOddThreadTurn = true;
        notifyAll();
    }
}