package com.nishchay.concurrentpkg.pool.a01types;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

import java.util.stream.Collectors;


/*
 *
 *	-	Light-weight asychronous tasks, short-lived task
 *	-	Threads are automatically created if no thread is available for a task
 *	-	Idle threads are re-used and will terminate after 60 seconds of inactivity
 *
 * */
public class A02CachedThreadPool {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        Callable<String> task = () -> {
            long oneHundredMicroSeconds = 100_000;
            long startedAt = System.nanoTime();
            while (System.nanoTime() - startedAt <= oneHundredMicroSeconds);

            return "Done";
        };

        ExecutorService cachedPool = Executors.newCachedThreadPool();
        List<Callable<String>> tasks = IntStream.rangeClosed(1, 100).mapToObj(i -> task).collect(Collectors.toList());
        List<Future<String>> result = cachedPool.invokeAll(tasks);

        for(Future<String> curr : result)
            if (curr.isDone())
                System.out.println(curr.get());

    }
}
