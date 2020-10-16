package com.nishchay.thread.synchronize.deadlock;

public class Writer1 extends Thread {

    Object book;
    Object pen;

    public Writer1(Object book, Object pen) {
        this.book = book;
        this.pen = pen;
    }

    @Override
    public void run() {
        synchronized(book) {
            try { Thread.sleep(10); } catch(Exception e) {}
            synchronized(pen) {
                System.out.println("Writer1 writing");
            }
        }
    }
}
