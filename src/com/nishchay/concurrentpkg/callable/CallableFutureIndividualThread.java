package com.nishchay.concurrentpkg.callable;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class CallableFutureIndividualThread {

    public static void main(String[] args) {

//        callableExWithThread();
        callableExWithThreadPool();

    }



    /*
    * Java program to illustrate Callable and Future Task for random number generation
    * Callable instance --> FutureTask instance --> Thread class instance
    * */
    private static void callableExWithThread() {
        // FutureTask is a concrete class that
        // implements both Runnable and Future
        FutureTask[] randomNumberTasks = new FutureTask[5];

        for (int i = 0; i < 5; i++) {
            Callable callable = new CallableTask();

            // Create the FutureTask with Callable
            randomNumberTasks[i] = new FutureTask(callable);

            // As it implements Runnable, create Thread with FutureTask
            Thread t = new Thread(randomNumberTasks[i]);
            t.start();
        }

        for (int i = 0; i < 5; i++) {
            // As it implements Future, we can call get()
            try {
                System.out.println(randomNumberTasks[i].get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
            // This method blocks till the result is obtained
            // The get method can throw checked exceptions
            // like when it is interrupted.
        }
    }

    private static void callableExWithThreadPool() {

        ExecutorService threadPool = Executors.newCachedThreadPool();
        List<Callable<Object>> callableList =  Collections.nCopies(5, () -> new Random().nextInt(100));

        List<Integer> integerList;

        try {
            List<Future<Object>> futureList = threadPool.invokeAll(callableList);

            integerList = futureList.stream()
                    .map(CallableFutureIndividualThread::getFutureValue)
                    .filter(Objects::nonNull)
                    .map(e-> (Integer)e)
                    .collect(Collectors.toList());

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            threadPool.shutdown();
        }

        System.out.println("integerList = " + integerList);
    }

    private static Object getFutureValue(Future<Object> f) {
        try {
            return f.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}


