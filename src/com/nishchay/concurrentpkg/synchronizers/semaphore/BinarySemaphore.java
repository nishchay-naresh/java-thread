package com.nishchay.concurrentpkg.synchronizers.semaphore;

import java.util.concurrent.Semaphore;

public class BinarySemaphore {

    public static void main(String[] args){

        Semaphore binarySemaphore = new Semaphore(1, true);

        try {
            binarySemaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // assertEquals(0, binarySemaphore.availablePermits());
        if(0 == binarySemaphore.availablePermits()){
            System.out.println("zero permits available");
        }

        binarySemaphore.release();

        // assertEquals(1, binarySemaphore.availablePermits());
        if(1 == binarySemaphore.availablePermits()){
            System.out.println("one permits available");
        }

    }
}
