package com.nishchay.thread.synchronize.deadlock;

import static com.nishchay.Utils.sleep0;

public class Writer2 extends Thread {

    private final Object book;
    private final Object pen;

    public Writer2(Object book, Object pen) {
        this.book = book;
        this.pen = pen;
    }

    @Override
    public void run() {
        synchronized(pen) {
            sleep0(10);
            synchronized(book) {
                System.out.println("Writer2 writing");
            }
        }
    }
}
