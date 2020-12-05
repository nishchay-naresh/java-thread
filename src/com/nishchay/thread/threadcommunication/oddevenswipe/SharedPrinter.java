package com.nishchay.thread.threadcommunication.oddevenswipe;

public class SharedPrinter {

    private int thread1Value, thread2Value;
    private boolean thread1Turn;

    private boolean thread1Done, thread2Done;

    public SharedPrinter(int thread1Value, int thread2Value, boolean thread1Turn) {
        this.thread1Value = thread1Value;
        this.thread2Value = thread2Value;
        this.thread1Turn = thread1Turn;
        thread1Done = thread2Done = false;
    }


    public synchronized void printFirst() {

        while (!thread1Turn) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (thread1Done) {
            System.out.println(Thread.currentThread().getName() + " - " + thread2Value);
            thread2Value = thread2Value + 2;
            thread1Done = false;
        } else {
            System.out.println(Thread.currentThread().getName() + " - " + thread1Value);
            thread1Value = thread1Value + 2;
            thread1Done = true;
        }

        thread1Turn = false;
        notify();
    }


    public synchronized void printSecond() {

        while (thread1Turn) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (thread2Done) {
            System.out.println(Thread.currentThread().getName() + " - " + thread1Value);
            thread1Value = thread1Value + 2;
            thread2Done = false;
        } else {
            System.out.println(Thread.currentThread().getName() + " - " + thread2Value);
            thread2Value = thread2Value + 2;
            thread2Done = true;
        }

        thread1Turn = true;
        notify();
    }

}
