package com.nishchay.concurrentpkg.atomic.counter;

class SynchronizedCounter  implements  ICounter {
    private int count = 0;

    // Synchronized Method 
    public synchronized void increment() {
        count = count + 1;
    }

    public int getCount() {
        return count;
    }
}