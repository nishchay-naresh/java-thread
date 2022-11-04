package com.nishchay.thread.jmm;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class VolatileArrayEx {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        test_issue();
        test_issue_fix();

    }

    public static void test_issue_fix() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            AlignedCounters foo = new AlignedCountersB();

            long end = System.nanoTime() + TimeUnit.SECONDS.toNanos(10);

            Future<?> incrementer = executorService.submit(() -> {
                while (System.nanoTime() < end) {
                    foo.increment();
                }
            });

            Thread.sleep(3000);

            foo.clear();

            incrementer.get();

            System.out.println(foo.get());

        } finally {
            executorService.shutdown();
        }
    }

    public static void test_issue() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            AlignedCounters foo = new AlignedCountersA();

            long end = System.nanoTime() + TimeUnit.SECONDS.toNanos(10);

            Future<?> incrementer = executorService.submit(() -> {
                while (System.nanoTime() < end) {
                    foo.increment();
                }
            });

            Thread.sleep(3000);

            foo.clear();

            incrementer.get();

            System.out.println(foo.get());

        } finally {
            executorService.shutdown();
        }
    }

    interface AlignedCounters {

        long get();

        void increment();

        void clear();
    }

    // Simple implementation using individual atomic member
    static class AlignedCountersA implements AlignedCounters {
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

    // implementation using volatile array
    static class AlignedCountersB implements AlignedCounters {

        volatile AtomicLong[] counters;

        AlignedCountersB() {
            // write
            counters = new AtomicLong[]{
                    new AtomicLong(0),
                    new AtomicLong(0)
            };
        }

        @Override
        public long get() {
            // fetch/read
            AtomicLong[] c = counters;
            long valA = c[0].get();
            long valB = c[1].get();
            if (valA == valB) {
                return valA;
            } else {
                throw new IllegalStateException((valA + " != " + valB));
            }
        }

        @Override
        public void increment() {
            // fetch/read
            AtomicLong[] c = counters;
            c[0].incrementAndGet();
            c[1].incrementAndGet();
            // write has been taken care by Atomic variable
            // for primitive array, need to another assignment of local variable array ref to attribute array ref
        }

        @Override
        public void clear() {
            // write
            counters = new AtomicLong[]{
                    new AtomicLong(0),
                    new AtomicLong(0)
            };
        }
    }

}
