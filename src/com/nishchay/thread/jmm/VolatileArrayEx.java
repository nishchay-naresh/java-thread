package com.nishchay.thread.jmm;

import java.util.concurrent.atomic.AtomicLong;

public class VolatileArrayEx {

    public static void main(String[] args) {
//        test_issue();
//        test_issue_fix();
    }

    interface AlignedCounters {

        long get();

        void increment();

        void clear();
    }

    // Simple implementation using individual member
    static class AlignedCountersA implements AlignmentTest.AlignedCounters {
        AtomicLong a = new AtomicLong(0);
        AtomicLong b = new AtomicLong(0);

        @Override
        public long get() {
            long valA = a.get();
            long valB = b.get();

            if (valA == valB) {
                return valA;
            } else {
                throw new IllegalStateException((valA + " != " + valB));
            }
        }

        @Override
        public void increment() {
            a.incrementAndGet();
            b.incrementAndGet();
        }

        @Override
        public void clear() {
            a.set(0L);
            b.set(0L);
        }

    }

}
