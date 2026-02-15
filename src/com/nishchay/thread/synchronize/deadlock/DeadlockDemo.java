package com.nishchay.thread.synchronize.deadlock;

import com.nishchay.Utils;

public class DeadlockDemo {

    public static void main(String[] args) {
        deadlockUsingLambda();
        deadlockUsingClazz();
    }

    private static void deadlockUsingLambda() {
        Object book = new Object();
        Object pen = new Object();

        Thread writer1 = new Thread(() -> {
            // lock order: book -> pen
            synchronized (book) {
                Utils.sleep0(10);
                synchronized (pen) {
                    System.out.println("Writer1 writing");
                }
            }
        });
        writer1.start();

        Thread writer2 = new Thread(() -> {
            // lock order: pen -> book
            synchronized(pen) {
                Utils.sleep0(10);
                synchronized(book) {
                    System.out.println("Writer2 writing");
                }
            }
        });
        writer2.start();

        System.out.println("Main is done");
    }

    private static void deadlockUsingClazz() {
        Object book = new Object();
        Object pen = new Object();

        new Writer1(book, pen).start();
        new Writer2(book, pen).start();

        System.out.println("Main is done");
    }

    static class Writer1 extends Thread {

        private final Object book;
        private final Object pen;

        public Writer1(Object book, Object pen) {
            this.book = book;
            this.pen = pen;
        }

        @Override
        public void run() {
            // order in which locks are acquired is the main problem: book -> pen
            synchronized(book) {
                Utils.sleep0(10);
                synchronized(pen) {
                    System.out.println("Writer1 writing");
                }
            }
        }
    }

    static class Writer2 extends Thread {

        private final Object book;
        private final Object pen;

        public Writer2(Object book, Object pen) {
            this.book = book;
            this.pen = pen;
        }

        @Override
        public void run() {
            // order in which locks are acquired is the main problem: pen -> book
            synchronized(pen) {
                Utils.sleep0(10);
                synchronized(book) {
                    System.out.println("Writer2 writing");
                }
            }
        }
    }

}

/*
* O/P =>
* Main is done
* Both the thread is trying to acquire a lock which is held by another thread and formed a deadlock.
* both threads will run for an indefinite time
*
* */

