package com.nishchay.thread.threadcommunication.nthreads.splittask;

public class Task implements Runnable {

    private int start;
    private int end;

    public Task(int start, int end) {
        this.start = start;
        this.end = end;
    }


    @Override
    public void run() {
        printNumbers(start, end);
    }

    private void printNumbers(int start, int end) {
        for (int i = start; i < end; i++) {
            System.out.println(Thread.currentThread().getName() + " - " + i);
        }
    }
}