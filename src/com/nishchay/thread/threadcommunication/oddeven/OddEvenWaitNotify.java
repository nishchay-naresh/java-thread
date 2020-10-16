package com.nishchay.thread.threadcommunication.oddeven;

public class OddEvenWaitNotify {

    public static void main(String[] args) {

        NumberPrinter numberPrinter = new NumberPrinter();

        new Thread(new OddNumberGenerator(numberPrinter), "Odd Thread").start();
        new Thread(new EvenNumberGenerator(numberPrinter), "Even Thread").start();

    }

}




