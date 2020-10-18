package com.nishchay.concurrentpkg.synchronizers.semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/*
Counting Semaphore is used here to store no of permits for Printer use
 */
public class CountingSemaphoreDemo {

    public static void main(String[] args) {

        final int NO_OF_PRINTER = 2;

        List<Printer> printers =  new ArrayList<>(NO_OF_PRINTER);
        for (int i = 0; i < NO_OF_PRINTER ; i++) {
            int name = i + 1;
            printers.add(new Printer("Printer - " + name));
        }

        Semaphore noOfPermits =  new Semaphore(NO_OF_PRINTER);

        new Thread(new PrintJob(noOfPermits, printers), "Thread 1").start();
        new Thread(new PrintJob(noOfPermits, printers), "Thread 2").start();
        new Thread(new PrintJob(noOfPermits, printers), "Thread 3").start();
        new Thread(new PrintJob(noOfPermits, printers), "Thread 4").start();
        new Thread(new PrintJob(noOfPermits, printers), "Thread 5").start();
        new Thread(new PrintJob(noOfPermits, printers), "Thread 6").start();

    }
}

