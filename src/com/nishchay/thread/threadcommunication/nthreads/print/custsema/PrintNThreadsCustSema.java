package com.nishchay.thread.threadcommunication.nthreads.print.custsema;

/*
 *
 * Print 1-N using some no of threads(here 4 again should be configurable)
 * 	Thread-1 : 1, 1, 1, 1, 1
 * 	Thread-2 : 2, 2, 2, 2, 2
 * 	Thread-3 : 3, 3, 3, 3, 3
 * 	Thread-4 : 4, 4, 4, 4, 4
 * 	Thread-5 : 5, 5, 5, 5, 5
 *
 * */
public class PrintNThreadsCustSema {

    public static void main(String[] args) {

        final int noOfThreads = 5;

        Permission[] permissionArray = new Permission[noOfThreads];

        for (int i = 0; i < noOfThreads; i++) {
            permissionArray[i] = new Permission();
        }
        // ensuring that only thread1 should start first
        permissionArray[0].setPermission();


        for (int i = 0; i < noOfThreads; i++) {
            Runnable task = new NumberThread(i + 1, permissionArray[i], permissionArray[(i + 1) % noOfThreads]);
            new Thread(task, "Thread-" + (i + 1)).start();
        }

    }
}


