package com.nishchay.thread.threadcommunication.oddeven;

public class EvenNumberGenerator implements Runnable {

    private NumberPrinter printer;

    public EvenNumberGenerator(NumberPrinter printer) {
        this.printer = printer;
    }

    public void run() {
        for(int i = 2; i <= 20 ; i = i + 2)
            printer.printEven(i);
    }
}