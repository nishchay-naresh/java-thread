package com.nishchay.thread.synchronize.sum100;


import java.util.stream.IntStream;

/*
 * This solution is written using wait and notify
 * Locking is based on user defined object Lock (doing a purpose of Mutable Boolean class)
 * Taking care for the spurious wakeup scenario
 * Spurious Wakeup (Thread can wake up without notify)
 *
 * */
public class Sum100SharedObjectLocking {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("----Main started----");

        MyLock myLock = new MyLock(true);
        Runnable task = new Sum100Task(myLock);
        Thread thread = new Thread(task);
        thread.start();

        synchronized (myLock) {
            while (myLock.isChildTurn()) {
                myLock.wait();
            }
        }

        System.out.println("----Main Ended----");
    }

    static class Sum100Task implements Runnable {

        private final MyLock myLock;

        public Sum100Task(MyLock myLock) {
            this.myLock = myLock;
        }

        @Override
        public void run() {

            synchronized (myLock) {
                sum100();
                myLock.setChildTurn(false);
                myLock.notify();
            }
        }

        private void sum100() {
            int sum = IntStream.rangeClosed(1, 100).sum();
            System.out.println("sum of 100 = " + sum);
        }

    }

    // Boolean is an immutable class, so Writing own lock class for Boolean mutability
    static class MyLock {

        boolean childTurn;

        public MyLock(boolean childTurn) {
            this.childTurn = childTurn;
        }

        public boolean isChildTurn() {
            return childTurn;
        }

        public void setChildTurn(boolean childTurn) {
            this.childTurn = childTurn;
        }
    }
}


