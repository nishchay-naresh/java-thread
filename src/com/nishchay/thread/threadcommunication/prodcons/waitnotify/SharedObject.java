package com.nishchay.thread.threadcommunication.prodcons.waitnotify;

public class SharedObject {

    private int data;
    private boolean isProdTurn = true;

    public synchronized void produce(int value) {
        // why did we use while(busy == false) instead of if(busy == false)
        // because of spurious wake ups
        while (!isProdTurn) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        data = value;
        isProdTurn = false;
        notify();
    }

    public synchronized int consume() {
        while (isProdTurn) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isProdTurn = true;
        notify();
        return data;
    }
}