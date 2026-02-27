package com.nishchay.concurrentpkg.callable;

import com.nishchay.Utils;

import java.util.concurrent.*;

/*
 *
 * A cancellable asynchronous computation that can return a result in the future.
 * FutureTask<V> is a concrete implementation of both Runnable and Future<V>
 * So it can:
 *     • Be executed by a thread
 *     • Return a result
 *     • Be canceled
 *     • Tell whether it's done
 *
 * Why does Java need FutureTask?
 *	1. Runnable r = () -> System.out.println("Hello");
 *	Problem:
 *		• Runnable does NOT return a value
 *		• No way to know when it finishes - isDone()
 *		• No way to cancel cleanly - cancel()
 *
 *	2. Future<V> →  provides a base implementation of Future
 *  FutureTask solve these problems
 *
 *
 *  ==================== Key Methods ==================
 * 	get()			Waits and returns result
 * 	cancel()		Cancels execution
 * 	isDone()		Check if completed
 * 	isCancelled()	Check if cancelled
 *
 * */
public class FutureTaskEx {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        wrapCallableInAFutureTask();
        futureTaskOfRunnable();
    }

    /*
     * FutureTask of Callable -> get it executed by user thread and executor
     *
     * Way to run a Callable<T> instance in a user thread:
     * 	Callable instance --> FutureTask instance --> Thread class instance
     *
     * - Can run without Executor
     *       FutureTask implements Runnable, can pass it to Thread constructor
     * - Can retrieve result
     *       futureTask.get(), blocking in nature like future.get()
     * - Can block until done
     *
     * */
    private static void wrapCallableInAFutureTask() throws ExecutionException, InterruptedException {

        Callable<String> callable = () -> {
            Utils.sleep0(2000);
            return "Task Completed";
        };

        FutureTask<String> futureTask = new FutureTask<>(callable);
        // executing FutureTask in bare thread
        Thread t = new Thread(futureTask);
        t.start();
        System.out.println(futureTask.get()); // blocks

        ExecutorService executor = Executors.newFixedThreadPool(2);
        FutureTask<String> futureTaskString = new FutureTask<>(() -> "Done");
        executor.execute(futureTaskString);
        executor.shutdown();
        System.out.println(futureTaskString.get());
    }

    /*
     * FutureTask of Runnable -> get it executed by user thread and executor.
     *
     * Runnable task gets executed, and some Result is also returned
     *      Utilizing this constructor - public FutureTask(Runnable runnable, V result)
     *
     * - Can run in both by user thread and executor
     * - Can retrieve a result using futureTask.get(), blocking in nature like future.get()
     *
     * */
    private static void futureTaskOfRunnable() throws ExecutionException, InterruptedException {

        Runnable task = () -> {
            Utils.sleep0(1000);
            System.out.println("Executing Runnable task for Integer");
        };

        FutureTask<Integer> futureTaskInteger = new FutureTask<>(task, 99);
        // Run manually
        Thread t = new Thread(futureTaskInteger);
        t.start();
        System.out.println(futureTaskInteger.get());

        task = () -> {
            Utils.sleep0(1000);
            System.out.println("Executing Runnable task for String");
        };

        ExecutorService executor = Executors.newFixedThreadPool(2);
        FutureTask<String> futureTaskString = new FutureTask<>(task, "TASK_COMPLETED");
        executor.execute(futureTaskString);
        executor.shutdown();
        System.out.println(futureTaskString.get());
    }
}

/*
 * o/p =>
 *	42
 *	Done
 *	Executing Runnable task for Integer
 *	99
 *	Executing Runnable task for String
 *	TASK_COMPLETED
 * */
