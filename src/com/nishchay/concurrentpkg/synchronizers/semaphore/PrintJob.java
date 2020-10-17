package com.nishchay.concurrentpkg.synchronizers.semaphore;

import java.util.concurrent.Semaphore;

public class PrintJob implements Runnable {

    private Semaphore semaphore;

    public PrintJob(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire(); // decrease the permit count
            // critical section
            System.out.printf("Printer - %d, is currently been used by - %s %n", semaphore.availablePermits() + 1, Thread.currentThread().getName());
            Thread.sleep(2 * 1000);
            semaphore.release(); // increase the permit count
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}