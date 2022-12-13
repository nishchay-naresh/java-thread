package com.nishchay.concurrentpkg.lock.rwlock;

/**
 * ReadWriteTest.java
 * Test program is to understand ReentrantReadWriteLock concept
 * Leveraging its two locks (read & write lock ) for get & out propose
 * Making non-thread safe collection ArrayList to ThreadSafe by guarding add() & get() method
 *
 */
public class ReadWriteLockTest {

    private static final int WRITER_THREAD_COUNT = 4;
    private static final int READER_THREAD_COUNT = 10;

    public static void main(String[] args) {

        Integer[] initialElements = {33, 28, 86, 99, 75};
        ReadWriteList<Integer> sharedList = new ReadWriteList<>(initialElements);
 
        for (int i = 0; i < WRITER_THREAD_COUNT; i++) {
            new Writer(sharedList).start();
        }
 
        for (int i = 0; i < READER_THREAD_COUNT; i++) {
            new Reader(sharedList).start();
        }

    }
}