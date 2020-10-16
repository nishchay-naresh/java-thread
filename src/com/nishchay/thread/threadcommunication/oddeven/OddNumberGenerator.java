package com.nishchay.thread.threadcommunication.oddeven;

public class OddNumberGenerator implements Runnable {

    private NumberPrinter printer;

    public OddNumberGenerator(NumberPrinter printer) {
        this.printer = printer;
    }

    public void run() {
        for(int i = 1; i <= 20 ; i = i + 2)
            printer.printOdd(i);
    }
}