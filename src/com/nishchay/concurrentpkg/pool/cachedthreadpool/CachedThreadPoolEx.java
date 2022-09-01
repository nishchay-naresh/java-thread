package com.nishchay.concurrentpkg.pool.cachedthreadpool;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class CachedThreadPoolEx {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        Callable<String> task = () -> {
            long oneHundredMicroSeconds = 100_000;
            long startedAt = System.nanoTime();
            while (System.nanoTime() - startedAt <= oneHundredMicroSeconds);

            return "Done";
        };

        ExecutorService cachedPool = Executors.newCachedThreadPool();
        List<Callable<String>> tasks = IntStream.rangeClosed(1, 100).mapToObj(i -> task).collect(toList());
        List<Future<String>> result = cachedPool.invokeAll(tasks);

        for(Future<String> curr : result)
            if (curr.isDone())
                System.out.println(curr.get());

    }
}
