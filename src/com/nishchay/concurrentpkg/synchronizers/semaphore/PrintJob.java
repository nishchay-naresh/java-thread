package com.nishchay.concurrentpkg.synchronizers.semaphore;

import java.util.List;
import java.util.concurrent.Semaphore;

public class PrintJob implements Runnable {

    private Semaphore semaphore;
    private List<Printer> printers;

    public PrintJob(Semaphore semaphore, List<Printer> printers) {
        this.semaphore = semaphore;
        this.printers = printers;
    }


    @Override
    public void run() {
        int index;
        try {
            //            semaphore.acquireUninterruptibly(); // doesn't throw InterruptedException
            semaphore.acquire(); // decrease the permit count

            // below synchronization is required , bcus ArrayList is not threadsafe
            // possible that two threads can acquire the same printer object after passing the permit test
            synchronized (this){
                index = semaphore.availablePermits();
                // critical section
                Printer printer = printers.get(index);
                printer.usePrinter();
            }

            Thread.sleep(2 * 1000);
            semaphore.release(); // increase the permit count
       } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}