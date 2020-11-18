package com.nishchay.thread.threadcommunication.nthreads.splittask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;


/*
* We need to distribute this task of print numbers between 1-100, among some (say n) threads.
* Write an implementation for this, write code in most generic possible manner, make this noOfThreads as configurable
*
* */
public class ThreadTaskSplit {

    public static void main(String[] args) {

        taskSplitUsingIndividualThreadsSync();

//        taskSplitUsingIndividualThreads();
//        taskSplitUsingThreadPool();

    }

    /*
     * Splitting up the task among multiple threads (here 4), by creating individual thread
     * Need to synchronise the threads to get an ordered output
     */
    private static void taskSplitUsingIndividualThreadsSync() {


        int noOfThreads = 4;

        Semaphore s1 = new Semaphore(1);
        Semaphore s2 = new Semaphore(0);
        Semaphore s3 = new Semaphore(0);
        Semaphore s4 = new Semaphore(0);


        for (int i = 1; i <= noOfThreads; i++) {

            switch (i) {
                case 1:
                    new Thread(new SyncTask(1,25, s1, s2), "Thread" + i).start();
                    break;
                case 2:
                    new Thread(new SyncTask(26,50, s2, s3), "Thread" + i).start();
                    break;
                case 3:
                    new Thread(new SyncTask(51,75, s3, s4), "Thread" + i).start();
                    break;
                case 4:
                    new Thread(new SyncTask(76,100, s4, s1), "Thread" + i).start();
                    break;
            }

        }
    }

/*
 * Splitting up the task among multiple threads (here 4), by creating individual thread
 */
    private static void taskSplitUsingIndividualThreads() {

        int startCount = 1;
        int finalCount = 100;
        final int noOfThreads = 4;

        // Splitting up the task based on no of threads
        int taskSplitWindow = finalCount / noOfThreads;

        for (int i = 1; i <= noOfThreads; i++) {
            Task task = new Task(startCount, startCount + taskSplitWindow);
            new Thread(task, "Thread " + i).start();

            startCount = startCount + taskSplitWindow;
        }
    }

/*
* Splitting up the task among multiple threads (here 4), by thread pool executors
*/
    private static void taskSplitUsingThreadPool() {

        int startCount = 1;
        int finalCount = 100;
        final int noOfThreads = 4;

        // Splitting up the task based on no of threads
        int taskSplitWindow = finalCount / noOfThreads;

        ExecutorService executorService = Executors.newFixedThreadPool(noOfThreads);

        for (int i = 1; i <= noOfThreads; i++) {

            Task task = new Task(startCount, startCount + taskSplitWindow);
            executorService.submit(task);

            startCount = startCount + taskSplitWindow;
        }
        executorService.shutdown();
    }

}
