package com.nishchay.thread.synchronize.deadlock;

import static com.nishchay.Utils.sleep0;

public class Writer1 extends Thread {

    private final Object book;
    private final Object pen;

    public Writer1(Object book, Object pen) {
        this.book = book;
        this.pen = pen;
    }

    @Override
    public void run() {
        synchronized(book) {
            sleep0(10);
            synchronized(pen) {
                System.out.println("Writer1 writing");
            }
        }
    }
}
