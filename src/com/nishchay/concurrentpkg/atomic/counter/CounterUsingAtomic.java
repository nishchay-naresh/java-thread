package com.nishchay.concurrentpkg.atomic.counter;

import java.util.concurrent.atomic.AtomicInteger;

class CounterUsingAtomic implements Counter {

    private final AtomicInteger count = new AtomicInteger(0);

    public void increment() {
        count.incrementAndGet();
    }

    public int getCount() {
        return count.get();
    }
}