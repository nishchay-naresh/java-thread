package com.nishchay.thread.threadcommunication.nthreads.splittask;

public class SyncTaskBoolean implements Runnable {

    private int start, end;
    Permission current;
    Permission next;

    public SyncTaskBoolean(int start, int end, Permission current, Permission next) {
        this.start = start;
        this.end = end;
        this.current = current;
        this.next = next;
    }

    @Override
    public void run() {

        synchronized (current) {
            while (!current.getPermission()) {
                try {
                    current.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            current.revokePermission();
        }

        printNumbers(start, end);

        synchronized (next) {
            next.setPermission();
            next.notifyAll();
        }

    }

    private void printNumbers(int start, int end) {
        for (int i = start; i < end; i++) {
            System.out.println(Thread.currentThread().getName() + " - " + i);
        }
    }
}
