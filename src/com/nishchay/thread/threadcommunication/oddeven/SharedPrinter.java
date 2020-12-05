package com.nishchay.thread.threadcommunication.oddeven;

public class SharedPrinter {

    private int oddThreadValue, evenThreadValue;
    private boolean oddThreadTurn;

    public SharedPrinter(int oddThreadValue, int evenThreadValue, boolean oddThreadTurn) {
        this.oddThreadValue = oddThreadValue;
        this.evenThreadValue = evenThreadValue;
        this.oddThreadTurn = oddThreadTurn;
    }

    public synchronized void printFirst() {

        while (!oddThreadTurn) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " - " + oddThreadValue);
        oddThreadValue = oddThreadValue + 2;
        oddThreadTurn = false;
        notify();
    }

    public synchronized void printSecond() {

        while (oddThreadTurn) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(Thread.currentThread().getName() + " - " + evenThreadValue);
        evenThreadValue = evenThreadValue + 2;
        oddThreadTurn = true;
        notify();
    }

}
