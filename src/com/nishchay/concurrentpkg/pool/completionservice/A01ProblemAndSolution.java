package com.nishchay.concurrentpkg.pool.completionservice;

import com.nishchay.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class A01ProblemAndSolution {

    public static void main(String[] args) throws InterruptedException {

        List<Callable<String>>  callableTaskList = new ArrayList<>();
        callableTaskList.add( () -> solveInLanguage("Ruby", 5));
        callableTaskList.add( () -> solveInLanguage("Python", 3));
        callableTaskList.add(() -> solveInLanguage("Java", 1));
        callableTaskList.add(() -> solveInLanguage("Go", 3));
        callableTaskList.add(() -> solveInLanguage("Kotlin", 2));
        callableTaskList.add(() -> solveInLanguage("Scala", 3));

        problemEx(callableTaskList);
        solutionEx(callableTaskList);
    }

    // simulator method  - solving a complex problem, taking time to solve the same problem across different programming languages
    private static String solveInLanguage(String langName, int sec) {
        Utils.sleep0(sec * 1000);
        return langName;
    }

    /*
     * ============== Problem with ExecutorService + Future ====================
     * Extracting Result/Future In Order Of Task Submission
     *
     * Problem -
     *     • What we are doing here in below code, we mostly extract the result (future.get()) in order of their submission.
     *     • If the first task is slow, future.get() gets blocked
     *     • Even if other tasks finished earlier, you can’t access their results
     *     • Inefficient and poor responsiveness
     *     • Not the best utilization of multithreading, CPU cycle will be wasted for idle core
     *
    * */
    private static void problemEx(List<Callable<String>> callableTaskList){

        ExecutorService executor = Executors.newFixedThreadPool(6);
        List<Future<String>> futures = new ArrayList<>();

        for (Callable<String> task : callableTaskList) {
            futures.add(executor.submit(task));
        }
        executor.shutdown();

        for (Future<String> future : futures) {
            String name = Utils.extractFuture(future); // BLOCKS!
            System.out.println("name = " + name);
        }
    }
    /*
     * O/P => order of their submission
     * name = Ruby
     * name = Python
     * name = Java
     * name = Go
     * name = Kotlin
     * name = Scala
     *
     * */

    /*
    * ========== ExecutorCompletionService fixes this problem =====================
    * Extracting Result/Future In Order Of Task Completion
    *
    * Solution -
    *     • wraps an ExecutorService, using the same for execution of submitted task
    *     • additionally maintains an internal blocking queue
    *     • when a task finishes → its Future is placed in the queue
    *     • Now once we will extract the result in below manner
    *               Integer result = executorCompletionService.take().get();
    *               Here future.get() is still a blocking call, but since we are extracting the Future now from a Queue of completed future so it won't get blocked.
    *
    * */
    private static void solutionEx(List<Callable<String>> callableTaskList) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(6);
        CompletionService<String> ecs = new ExecutorCompletionService<>(executor);

        for (Callable<String> task : callableTaskList) {
            ecs.submit(task);
        }

        for (int i = 0; i < callableTaskList.size(); i++) {
            Future<String> future = ecs.take(); // Now extracting the Future from blocking queue, take() blocks until ANY task completes
            String name = Utils.extractFuture(future);
            System.out.println("name = " + name);
        }
        executor.shutdown();
    }
    /*
     * O/P => order of their completion
     * name = Java
     * name = Kotlin
     * name = Python
     * name = Scala
     * name = Go
     * name = Ruby
     *
     * */
}
