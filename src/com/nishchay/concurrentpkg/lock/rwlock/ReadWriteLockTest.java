package com.nishchay.concurrentpkg.lock.rwlock;

/**
 * ReadWriteTest.java
 * Test program is to understanding ReentrantReadWriteLock concept
 * Leveraging its two locks (read & write lock ) for get & out propose
 * Making non-thread safe collection ArrayList to ThreadSafe by guarding add() & get() method
 *
 */
public class ReadWriteLockTest {

    static final int READER_SIZE = 10;
    static final int WRITER_SIZE = 4;
 
    public static void main(String[] args) {
        Integer[] initialElements = {33, 28, 86, 99, 75};
 
        ReadWriteList<Integer> sharedList = new ReadWriteList<>(initialElements);
 
        for (int i = 0; i < WRITER_SIZE; i++) {
            new Writer(sharedList).start();
        }
 
        for (int i = 0; i < READER_SIZE; i++) {
            new Reader(sharedList).start();
        }
 
    }
}