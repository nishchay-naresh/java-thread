package com.nishchay.thread.uncaughtexception;

public class ExceptionInThread {
    public static void main(String[] args) {

        // Exception in thread "Thread-0" java.lang.ArithmeticException
        Thread t0 = new Thread(() -> {throw new ArithmeticException();});
        t0.start();

        t0.setUncaughtExceptionHandler((t, e) -> {
            String msg = "Catching exception thrown from run() " + e + " By Thread " + t.getName();
            System.out.println("ErrorMsg - " + msg);
            throw new RuntimeException(msg, e);
        });
    }
}
