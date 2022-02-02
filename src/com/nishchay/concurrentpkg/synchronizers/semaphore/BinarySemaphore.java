package com.nishchay.concurrentpkg.synchronizers.semaphore;

import java.util.concurrent.Semaphore;

public class BinarySemaphore {

    public static void main(String[] args){

        binarySemaphoreEx();
        System.out.println("-----------------------------");
        binarySemaphoreEx1();
    }

    private static void binarySemaphoreEx() {

        Semaphore binarySemaphore = new Semaphore(1, true);

        // critical section
        try {
            binarySemaphore.acquire(); // decrease the permit count
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(0 == binarySemaphore.availablePermits()){
            System.out.println("zero permits available");
        }

        binarySemaphore.release(); // increase the permit count

        if(1 == binarySemaphore.availablePermits()){
            System.out.println("one permits available");
        }

    }

    private static void binarySemaphoreEx1() {

        Semaphore semaphoreWithSinglePermit = new Semaphore(1);

        semaphoreWithSinglePermit.acquireUninterruptibly();
        System.out.println("available permits - " + semaphoreWithSinglePermit.availablePermits());

        semaphoreWithSinglePermit.release();
        System.out.println("available permits - " + semaphoreWithSinglePermit.availablePermits());

        semaphoreWithSinglePermit.tryAcquire();
        System.out.println("available permits - " + semaphoreWithSinglePermit.availablePermits());
    }
}
