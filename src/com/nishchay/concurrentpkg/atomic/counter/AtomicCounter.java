package com.nishchay.concurrentpkg.atomic.counter;

import java.util.concurrent.atomic.AtomicInteger;

class AtomicCounter implements ICounter {

    private AtomicInteger count = new AtomicInteger(0);

    public void increment() {
        count.incrementAndGet();
    }

    public int getCount() {
        return count.get();
    }
}