package com.nishchay.concurrentpkg.pool.a02fixedpool;

import com.nishchay.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class PoolOfCallable {

    public static void main(String[] args) throws InterruptedException, ExecutionException{
        ex1();
        ex2();
    }

    // getActiveCount() - Returns the approximate number of threads that are actively executing tasks.
    private static void ex1() throws InterruptedException, ExecutionException {
        Callable<String> callableTask = () -> {
            Utils.sleep0(2 * 1000);
            return "Result String returned by - " + Thread.currentThread().getName();
        };

        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        System.out.println("Before submit  : " + ((ThreadPoolExecutor) threadPool).getActiveCount());

        List<Future<String>> results = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Future<String> future = threadPool.submit(callableTask);
            System.out.println("During Execution  : " + ((ThreadPoolExecutor) threadPool).getActiveCount());
            results.add(future);

        }
        System.out.println("After submit  : " + ((ThreadPoolExecutor) threadPool).getActiveCount());
        threadPool.shutdown();

        for (Future<String> future : results) {
            System.out.println(future.get());
        }
    }

    private static void ex2() {
        List<Callable<Integer>> callableTaskList = new ArrayList<>();
        callableTaskList.add(() -> sumRange(1, 20));
        callableTaskList.add(() -> sumRange(1, 10));
        callableTaskList.add(() -> sumRange(50, 75));
        callableTaskList.add(() -> sumRange(30, 40));
        callableTaskList.add(() -> sumRange(75, 95));
        callableTaskList.add(() -> sumRange(105, 125));

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(8);

        for (Callable<Integer> callableTask : callableTaskList) {
            Future<Integer> future = fixedThreadPool.submit(callableTask);

            // fetching out the result in the order of their submission
            Integer sum = 0;
            try {
                sum = future.get();
            } catch (InterruptedException | ExecutionException e) {
                System.err.println("Error" + e);
            }
            System.out.print(" Sum = " + sum);
        }

        fixedThreadPool.shutdown();

        // submission of any task after shutdown() will lead to  - java.util.concurrent.RejectedExecutionException
        // Future<Integer> future = fixedThreadPool.submit( new SumJob(2000, 2500));
    }


    // Sum of integers from a to b inclusive
    private static int sumRange(int a, int b) {
        if (a > b) {
            throw new IllegalArgumentException("a must be <= b");
        }
        return IntStream.rangeClosed(1, 100).sum();
    }

}
