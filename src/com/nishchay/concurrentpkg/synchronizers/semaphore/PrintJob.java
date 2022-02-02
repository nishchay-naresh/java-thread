package com.nishchay.concurrentpkg.synchronizers.semaphore;

import java.util.List;
import java.util.concurrent.Semaphore;

public class PrintJob implements Runnable {

    private Semaphore semaphore;
    private List<Printer> availablePrinters;

    public PrintJob(Semaphore semaphore, List<Printer> availablePrinters) {
        this.semaphore = semaphore;
        this.availablePrinters = availablePrinters;
    }


    @Override
    public void run() {

        try {
            //  semaphore.acquireUninterruptibly(); // doesn't throw InterruptedException

            semaphore.acquire();
            // once printer is been allocated to a thread, it's been removed from the ArrayList
            Printer printer = availablePrinters.remove(0);
            Thread.sleep(2 * 1000);
            printer.usePrinter();
            availablePrinters.add(printer);
            // once printer is free again, it's been added again to ArrayList
            semaphore.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}