package com.nishchay.thread.basic.interrupt;

import com.nishchay.Utils;

import java.util.concurrent.*;


/*
 * "A Task is running in a separate thread. Stop the task if it exceeds 10 minutes."
 *      1.  We don't have stop() method for thread instance, can't have anything for stop, kill, suspend thread
 *      2.  Using executor.shutdown()/ executor.shutdownNow() wont help much because it never guarantees stoping a thread,
 *              Its documentation says - attempt to stop
 *      3.  Using a thread from thread pool, instead of runnable using a Callable<V>, then calling future.cancel()
 *              Its documentation says - attempt to stop
 *      Conclusion:
 *          Java threads cannot be killed.
 *          They are cooperative.
 *          You need to ask politely.
 *          How to ask politely?
 *              1.  Interrupts
 *              2.  AtomicBoolean / volatile boolean
 *
 * Classic “timeout a task” pattern - using ExecutorService + Future.
 *      Future.get(timeout) = wait, but only up to a limit and fail if it takes too long.
 *
 * In short:
 * 	-	Task runs in background
 * 	-	Main thread waits only up to a time
 * 	-	If a task finishes → get result
 * 	-	If not → timeout exception
 *
 * Using volatile boolean
 *      keep checking this AtomicBoolean / volatile boolean value inside the task in a loop
 *      end the task based on value change in this - AtomicBoolean / volatile boolean
 *
 * Important Caveat-Cannot react to interrupt nor volatile if control not in java. i.e during IO operations!
 *
 *
 *
 * */
public class TimeOutATask {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        // execute below methods one by one, by commenting others to understand the output better
        timeOutAThreadUsingFutureGetMethod();
        timeOutAThreadUsingVolatileBoolean();

    }

    private static void timeOutAThreadUsingFutureGetMethod() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(() -> {
            System.out.println(Thread.currentThread().getName() + " - executing a task");
            Utils.sleep0(4 * 1000);
            return "done";
        });

        String result;
        try {
           // result = future.get(5, TimeUnit.SECONDS); // happy path
           result = future.get(2, TimeUnit.SECONDS); // - java.util.concurrent.TimeoutException
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        } finally {
            executor.shutdown();
        }
        System.out.println("result = " + result);
    }


    private static void timeOutAThreadUsingVolatileBoolean() {
        // 1. creating a task and submitting to a thread
        Task task =  new Task();
        Thread t1 = new Thread(task);
        t1.start();

        // 2. main thread is doing its own work
        Utils.sleep0(4 * 1000);

        // 2. ask task/thread to stop using volatile
         task.keepRunning = false;
    }

    /*
     *
     * The Same thing can be archived using AtomicBoolean, instead of volatile boolean
     *  public AtomicBoolean keepRunning = new AtomicBoolean(true);
     *      to get its value  - keepRunning.get()
     *      to set its value  - keepRunning.set(false)
     *
     * */
    static class Task implements Runnable {

        public volatile boolean keepRunning = true;

        @Override
        public void run() {
            while (keepRunning){
                System.out.println(Thread.currentThread().getName() + " - executing a part of the task");
                Utils.sleep0(1000);
            }
            System.out.println(Thread.currentThread().getName() + " task is been stopped");
        }
    }
}