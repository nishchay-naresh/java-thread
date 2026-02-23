package com.nishchay.thread.jmm;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 *   x = 42
 *   x = 42, y = 43, z = 44
 *		creating a barrier. (All the things prior to this are happens before)
 *   v = 1;		        // volatile write, 	-> write barrier
 *   int r0 = v; 		// volatile read, 	-> read barrier
 *   r1 = 42, r2 = 43, r3 = 44
 *
 *   Volatile solves below 3 issues of java concurrency:
 *      1. Visibility                   <-- by ensuring visibility by read/write from main memory
 *    								    <-- Ensuring these two by creating barrier
 *	    2. Happens Before
 *      3. Instruction Reordering
 *
 * */
public class A02HappensBeforeRelationship {

    public static void main(String[] args) {
        volatileEx();
        synchronizedEx();
        lockEx();
    }


    private static void volatileEx() {
        VolatileFieldVisibility ref = new VolatileFieldVisibility();
        new Thread(ref::writer).start();
        new Thread(ref::reader).start();
    }

    private static void synchronizedEx() {
        SynchronizedFieldVisibility ref = new SynchronizedFieldVisibility();
        new Thread(ref::writer).start();
        new Thread(ref::reader).start();
    }

    private static void lockEx() {
        LockFieldVisibility ref = new LockFieldVisibility();
        new Thread(ref::writer).start();
        new Thread(ref::reader).start();
    }


    static class VolatileFieldVisibility {
        int x = 0, y = 0, z = 0;
        volatile int v;

        public void writer() {
            x = 42;
            y = 43;
            z = 44;
            v = 1;      // volatile write
        }

        public void reader() {
            int r1, r2, r3;

            int r0 = v; // volatile read
            // guarantee to see 42, because x can be cached as well
            r1 = x;
            r2 = y;
            r3 = z;
            System.out.printf("\nr0 - %d \t r1 - %d \t r2 - %d \t r3 - %d", r0, r1, r2, r3);
        }
    }

    static class SynchronizedFieldVisibility {
        int x = 0, y = 0, z = 0;

        public void writer() {
            synchronized (this) {
                x = 42;
                y = 43;
                z = 44;
            }
        }

        // Will get the ame behaviors as above with Locks
        public void reader() {
            int r1, r2, r3;
            synchronized (this) {
                r1 = x;
                r2 = y;
                r3 = z;
                System.out.printf("\nr1 - %d \t r2 - %d \t r3 - %d", r1, r2, r3);
            }
        }
    }

    static class LockFieldVisibility {
        int x = 0, y = 0, z = 0;
        Lock lock = new ReentrantLock();

        public void writer() {
            lock.lock();
            x = 42;
            y = 43;
            z = 44;
            lock.unlock();
        }

        // Will get the ame behaviors as above with Locks
        public void reader() {
            int r1, r2, r3;
            lock.lock();
            r1 = x;
            r2 = y;
            r3 = z;
            System.out.printf("\nr1 - %d \t r2 - %d \t r3 - %d", r1, r2, r3);
            lock.unlock();
        }
    }
}