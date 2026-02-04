package com.nishchay.thread.basic.a01spawn;

public class AThreadSpawn {

    public static void main(String[] args) {
        spawnAndRunNewThread();
        startingThreadTwice();
    }

    /*
     * To spawn a new Thread and get it executed in a new path of execution:
     * One need 4 things:
     *      1. Task - in the form of Runnable implementation
     *      2. Worker - Thread class object
     *      3. Assigning this task to the Worker - Thread(Runnable r)
     *      4. call start() to execute it in a new path of execution
     * */
    private static void spawnAndRunNewThread() {
        Task task = new Task();
        Thread userThread = new Thread(task, "User Thread");
        userThread.start();

        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName() + " is counting - " + i);
        }
    }

    /*
    * A Java Thread cannot be started twice
    * Why can’t a thread be started twice?
    *       NEW → RUNNABLE → RUNNING → TERMINATED
    * Runnable can be reused, Thread cannot.
    * */
    private static void startingThreadTwice() {

        Thread t = new Thread(new Task());
        t.start(); // first time – okay
        t.start(); // second time, Error - java.lang.IllegalThreadStateException

    }

    static class Task implements Runnable {

        @Override
        public void run() {
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + " is counting - " + i);
            }
        }
    }

}
