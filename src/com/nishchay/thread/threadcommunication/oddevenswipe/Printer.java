package com.nishchay.thread.threadcommunication.oddevenswipe;

public class Printer {

    private int data;


    public synchronized void printData(int prevValue) {

        synchronized(this){
            data = data + 1;
        }


 /*       while (!thread1Turn) {
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
        notify();*/
    }

}
