package com.nishchay.concurrentpkg.lock.rwlock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockEx {

    public static void main(String[] args) {
        ReentrantReadWriteLockEx object = new ReentrantReadWriteLockEx();
        Thread t1 = new Thread(() -> object.readResource());
        t1.start();
        Thread t2 = new Thread(() -> object.readResource());
        t2.start();
        Thread t3 = new Thread(() -> object.writeResource());
        t3.start();
        Thread t4 = new Thread(() -> object.writeResource());
        t4.start();

        // Both t1(reader), t2(reader) thread, will get the ReadLock and execute simultaneously
        // where as all reader threads, t3(writer), t4(writer) will execute one after another


    }

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    private ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    private void readResource() {
        readLock.lock();
        // view the resource
        System.out.println("read the resource");
        readLock.unlock();
    }

    private void writeResource() {
        writeLock.lock();
        // update the resource
        System.out.println("write the resource");
        writeLock.unlock();
    }

}
