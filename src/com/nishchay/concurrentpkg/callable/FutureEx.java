package com.nishchay.concurrentpkg.callable;

import java.util.Random;
import java.util.concurrent.*;

public class FutureEx {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService service = Executors.newFixedThreadPool(4);

        // callable task
        Callable<Integer> task = () -> {
            Thread.sleep(3 * 1000);
            return new Random().nextInt(100);
        };

        Future<Integer> future = service.submit(task);

        // the main thread is free, it can perform some unrelated operation
        Integer result = future.get();  // blocking
        System.out.println("result = " + result);

        //  we can extend this to List<Callable<Integer>>
        //  we will get List<Future<Integer>> as a result, for the execution of List<Callable<Integer>>
        // 100 futures, with 100 placeholders, none of them will have any value
        service.shutdown();
    }
}