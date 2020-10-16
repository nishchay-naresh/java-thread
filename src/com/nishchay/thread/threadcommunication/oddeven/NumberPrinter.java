package com.nishchay.thread.threadcommunication.oddeven;

public class NumberPrinter {

    private boolean isOddThrdTurn = true;

    public synchronized void printOdd(int num) {

        while (!isOddThrdTurn) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " generating : " + num);
        isOddThrdTurn = false;
        notifyAll();
    }

    public synchronized void printEven(int num) {

        while (isOddThrdTurn) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " generating : " + num);
        isOddThrdTurn = true;
        notifyAll();
    }
}