package com.nishchay.thread.threadcommunication.oddevenswipe;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static final int LIMIT = 5;

    public static void main(String[] args) {

        Printer printer = new Printer();

        Thread prodThread = new Thread(() -> thread1(printer), "Thread1");
        prodThread.start();

        Thread consThread = new Thread(() -> thread2(printer), "Thread2");
        consThread.start();
    }

    public static void thread1(Printer printer) {
        for (int i = 1; i <= LIMIT; i++) {
            printer.printData(i);
        }
    }

    public static void thread2(Printer printer) {
        for (int i = 1; i <= LIMIT; i++) {
            printer.printData(i);
        }
    }



}
