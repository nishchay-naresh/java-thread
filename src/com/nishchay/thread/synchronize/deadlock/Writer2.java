package com.nishchay.thread.synchronize.deadlock;

public class Writer2 extends Thread {

    private Object book;
    private Object pen;

    public Writer2(Object book, Object pen) {
        this.book = book;
        this.pen = pen;
    }

    @Override
    public void run() {
        synchronized(pen) {
            try { Thread.sleep(10); } catch(Exception e) {}
            synchronized(book) {
                System.out.println("Writer2 writing");
            }
        }
    }
}
