package com.nishchay.thread.threadcommunication.nthreads.printarray;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
* Print an int array value using 2 threads of a threadpool one after another
* int[] arr = new int[]{4, 10, 5, 9, 8, 2}
* thread 1 - 4, 5, 8
* thread 2 - 10, 9, 2
* */
public class PrintArrayUsingTwoThreads {

    public static void main(String[] args) {

        printArrayUsingIndividualThreads();
//        printArrayUsingThreadPool();

    }


    private static void printArrayUsingIndividualThreads() {

        int[] arr = new int[]{4, 10, 5, 9, 8, 2};

        SharedObjectBoolean sharedObjectBoolean = new SharedObjectBoolean();

        new Thread(() -> printEvenIndex(sharedObjectBoolean, arr), "EvenIndexThread").start();
        new Thread(() -> printOddIndex(sharedObjectBoolean, arr), "OddIndexThread").start();
    }

    private static void printArrayUsingThreadPool() {

        //        threadPool of 2 thread
        int[] arr = new int[]{4, 10, 5, 9, 8, 2};

        SharedObjectBoolean sharedObjectBoolean = new SharedObjectBoolean();
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.submit(() -> printEvenIndex(sharedObjectBoolean, arr));
        executorService.submit(() -> printOddIndex(sharedObjectBoolean, arr));

        executorService.shutdown();
    }


    private static void printEvenIndex(SharedObjectBoolean object1, int[] intArray) {
        for (int i = 0; i < intArray.length; i = i + 2) {
            object1.printEvenIndex(intArray[i]);
        }
    }

    private static void printOddIndex(SharedObjectBoolean object1, int[] intArray) {

        for (int i = 1; i < intArray.length; i = i + 2) {
            object1.printOddIndex(intArray[i]);
        }

    }


}
