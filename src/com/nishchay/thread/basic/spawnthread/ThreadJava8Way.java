package com.nishchay.thread.basic.spawnthread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadJava8Way {

    public static void main(String[] args) {
        runnableUsingLambda();
        callableUsingLambda();
    }

    /*
     *
     * Basic difference in both the approach
     * In java method is the 2nd class citizen, A method can't exist as independent, it must be wrapped around an Object
     *
     * 1. anonymous inner class - We are passing the behaviour, by wrapping it under a class
     * 2. lambda expression - passing the behaviour as en independent entity, which has been pointed by lambda
     *
     * */
    private static void runnableUsingLambda() {

        /*
         * 1. Providing Runnable interface implementation using anonymous inner class
         * Two class will be generated -  RunnableUsingLambda.class, RunnableUsingLambda$1.class(main running class)
         * */
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " - Creating a thread using anonymous inner class");
            }
        });
        t1.start();

        /*
         * 2. Providing Runnable interface implementation using lambda expression
         * Only one class will be generated -  RunnableUsingLambda.class(main running class)
         * */
        Thread t2 = new Thread(() -> System.out.println(Thread.currentThread().getName() + " - Creating a thread using lambda expression"));
        t2.start();

    }

    /*
     * Java program to illustrate Callable and Future Task
     * Callable instance --> FutureTask instance --> Thread class instance
     * */
    private static void callableUsingLambda() {

        FutureTask<String> futureTask = new FutureTask<>(() -> {
            System.out.println(Thread.currentThread().getName() + " - Creating a thread using callable");
            return "java";
        });

        Thread t1 = new Thread(futureTask);
        t1.start();

        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

    }

}
