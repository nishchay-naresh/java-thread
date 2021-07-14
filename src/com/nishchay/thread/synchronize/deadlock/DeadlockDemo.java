package com.nishchay.thread.synchronize.deadlock;

public class DeadlockDemo {

    public static void main(String[] args) {
        Object book = new Object();
        Object pen = new Object();

        new Writer1(book, pen).start();
        new Writer2(book, pen).start();

        System.out.println("Main is done");
    }

}

/*
* O/P =>
* Main is done
* And process should be still running for indefinite time
*
* */

