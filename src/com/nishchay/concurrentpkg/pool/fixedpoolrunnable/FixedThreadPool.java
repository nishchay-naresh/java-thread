package com.nishchay.concurrentpkg.pool.fixedpoolrunnable;

import java.util.concurrent.*;

import static com.nishchay.Utils.sleep0;

public class FixedThreadPool {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        runnableTaskExecute();
        callableTaskExecute();

    }

    private static void runnableTaskExecute() {

        Runnable task = () -> System.out.println("I ran!");
        // execution the same task in a user thread
        new Thread(task).start();

        // create a task, and pass it to executor
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.submit(task);
        executor.shutdown();

        // there is not much difference in both of the execution
        // only difference - one is using user thread, another one is using thread pool thread
    }

    private static void callableTaskExecute() throws ExecutionException, InterruptedException {

        Callable<String> task = () -> {
            System.out.println("I ran!");
            return null;
        };
        ExecutorService executor = Executors.newFixedThreadPool(1);
        // Task will get executed upon submitting to thread-poll, not during the future.get()
        Future<String> future = executor.submit(task);


        sleep0(5 * 1000);


        System.out.println(future.get());
        executor.shutdown();

    }

}
