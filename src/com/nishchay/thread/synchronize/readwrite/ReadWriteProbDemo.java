package com.nishchay.thread.synchronize.readwrite;


import com.nishchay.Utils;

/*
 * Classic read–modify–write concurrency demo in Java
 *
 *	-	One shared object (Shared)
 *	-	Two threads (t1 and t2)
 *	-	Both threads increment the same variable x
 *	-	The code demonstrates how synchronization prevents race conditions.
 *
 * 	With synchronized
 * 		Initial value = 10
 * 		Two increments
 * 		Final value = 12
 *
 * 	Without synchronized
 * 	Possible interleaving:
 * 		t1 reads x = 10
 * 		t2 reads x = 10
 * 		t1 writes x = 11
 * 		t2 writes x = 11
 * 	Final value = 11 (lost update problem), wrong
 *
 * */
public class ReadWriteProbDemo {

    public static void main(String[] args) throws InterruptedException {

        Shared sharedObject = new Shared();
        sharedObject.setX(10);

        /*
         * In this case both the threads t1 and t2 are sharing the same object
         * Both the threads will try to perform the increment() operation simultaneously,
         * which is taken care by making this increment() operation as synchronized
         * */
        Runnable task = () -> sharedObject.increment();
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        // the main thread will wait till the completion of t1, t2
        t1.join();
        t2.join();

        System.out.println(sharedObject.getX()); // 12
    }

    static class Shared {

        private int x;

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        /*
         * This is a read–modify–write operation:
         * 	-	Read x
         * 	-	Increment it
         * 	-	Write it back
         * 	-	This is not atomic unless synchronized.
         * */
        public synchronized void increment() {
            int y = getX();         // READ
            y++;                    // MODIFY
            Utils.sleep0(1);  // force context switch
            setX(y);                // WRITE
        }
    }

}
