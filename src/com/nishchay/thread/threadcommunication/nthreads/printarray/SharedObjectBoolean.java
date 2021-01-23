package com.nishchay.thread.threadcommunication.nthreads.printarray;

public class SharedObjectBoolean {

    private boolean evenIndexThreadTurn;

    public SharedObjectBoolean() {
        this.evenIndexThreadTurn = true;
    }

    public synchronized void printEvenIndex(int data) {
        while (!evenIndexThreadTurn) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " - " + data);
        evenIndexThreadTurn = false;
        notify();
    }

    public synchronized void printOddIndex(int data) {
        while (evenIndexThreadTurn) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " - " + data);
        evenIndexThreadTurn = true;
        notify();
    }
}
