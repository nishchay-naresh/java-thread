package com.nishchay.thread.basic.a01spawn;


/*
 *
 * Problem
 * You have a method like:
 * void process() throws IOException
 *
 * And you want to run it in a thread:
 * new Thread(() -> process()).start(); // ❌ Compilation error
 *
 * Why?
 * Because:
 *      Lambda is targeting Runnable
 *      Runnable.run() does NOT declare checked exceptions
 *
 * */
public class HandleExceptionInLambda {

    public static void main(String[] args) {
        new Thread(wrap(() -> process())).start();

    }


    static void process() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("Process Called");
    }

    static Runnable wrap(ThrowingRunnable r) {
        return () -> {
            try {
                r.run();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    // Create a custom functional interface (Clean approach)
    @FunctionalInterface
    interface ThrowingRunnable {
        void run() throws Exception;
    }

}
