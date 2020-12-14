package com.nishchay.concurrentpkg.atomic.counter;

public class Counter implements  ICounter{

    private int count = 0;

    public void increment() {
        count = count + 1;
    }

    public int getCount() {
        return count;
    }
}