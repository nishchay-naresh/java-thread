package com.nishchay.thread.threadcommunication.nthreads.print.sema;

import java.util.concurrent.Semaphore;

/*
 *
 * Print 1-N using some no of threads(here 4 again should be configurable)
 * 	Thread-1 : 1, 1, 1, 1, 1
 * 	Thread-2 : 2, 2, 2, 2, 2
 * 	Thread-3 : 3, 3, 3, 3, 3
 * 	Thread-4 : 4, 4, 4, 4, 4
 *
 * */
public class PrintNThreadsSemaphore {

    public static void main(String[] args) {

        int noOfThreads = 4;

        Semaphore[] lockObjects = new Semaphore[noOfThreads];
        lockObjects[0] = new Semaphore(1);

        for (int i = 1; i < noOfThreads; i++) {
            lockObjects[i] = new Semaphore(0);
        }

        for (int i = 0; i < noOfThreads; i++) {
            NumberThread task = new NumberThread(i + 1, lockObjects[i], lockObjects[(i + 1) % noOfThreads]);
            new Thread(task, "Thread-" + (i + 1)).start();
        }
    }
}


