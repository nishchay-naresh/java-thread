package com.nishchay.concurrentpkg.threadlocal;

public class A01ThreadLocalEx {

    private static ThreadLocal<Integer> threadId = ThreadLocal.withInitial(() -> 0);

    private static ThreadLocal<String> threadLocalStr = new ThreadLocal<>();

    public static void main(String[] args) {

        ex1();
        ex2();
    }

    private static void ex1() {

        Runnable task = () -> {
            String tName = Thread.currentThread().getName();
            threadLocalStr.set(tName);
            System.out.println("Thread: " + tName + " Value: " + threadLocalStr.get());
        };

        new Thread(task, "Thread-A").start();
        new Thread(task, "Thread-B").start();
    }

    private static void ex2() {

        Runnable task = () -> {
            threadId.set(threadId.get() + 1);
            System.out.println(Thread.currentThread().getName() + " -> " + threadId.get()
            );
        };

        for (int i = 1; i <= 4; i++) {
            new Thread(task).start();
        }

    }

}
