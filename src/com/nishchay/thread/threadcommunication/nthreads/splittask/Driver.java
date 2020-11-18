package com.nishchay.thread.threadcommunication.nthreads.splittask;


public class Driver {

    public static void main(String[] args) {


        /*
         * Splitting up the task among multiple threads (here 4), by creating individual thread
         * Need to synchronise the threads to get an ordered output, using boolean flag and wait-notify mechanism
         */

        int startCount = 1;
        int finalCount = 100;
        final int noOfThreads = 5;

        Permission[] permissionArray =  new Permission[noOfThreads];

        for (int i = 0; i < noOfThreads; i++) {
            permissionArray[i] = new Permission();
        }
        // ensuring that only thread1 should start first
        permissionArray[0].setPermission();

        // Splitting up the task based on no of threads
        int taskSplitWindow = finalCount / noOfThreads;

        int curr, next;
        for (int i = 1; i <= noOfThreads; i++) {

            curr = i -1;
            next = i == noOfThreads? 0 : i;
            Runnable task = new SyncTaskBoolean(startCount, startCount + taskSplitWindow, permissionArray[curr], permissionArray[next]);
            new Thread(task, "Thread " + i).start();

            startCount = startCount + taskSplitWindow;
        }
    }
}
