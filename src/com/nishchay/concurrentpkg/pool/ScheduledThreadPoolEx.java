package com.nishchay.concurrentpkg.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.time.LocalTime;

import static com.nishchay.Utils.sleep0;

public class ScheduledThreadPoolEx {

    public static void main(String[] args) {
        // Create a scheduled thread pool with 3 threads.
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);

        System.out.println("Current Time: " + LocalTime.now());

        // 1. Schedule a one-time task
        scheduler.schedule(() -> {
            System.out.println("One-time task executed at: " + LocalTime.now());
        }, 5, TimeUnit.SECONDS);

        // 2. Schedule a periodic task with a fixed rate
        // This task will run every 2 seconds, starting 3 seconds from now.
        scheduler.scheduleAtFixedRate(() -> {
            System.out.println("Fixed-rate task executed at: " + LocalTime.now());
        }, 3, 2, TimeUnit.SECONDS);

        // 3. Schedule a periodic task with a fixed delay
        // This task will wait 2 seconds after the previous task finishes before starting.
        scheduler.scheduleWithFixedDelay(() -> {
            System.out.println("Fixed-delay task executed at: " + LocalTime.now());
            // Simulate a long-running task
            sleep0(1500);
        }, 3, 2, TimeUnit.SECONDS);

        // Shut down the scheduler after 15 seconds.
        // It's important to shut down the executor, or the application may not terminate.
        scheduler.schedule(() -> {
            scheduler.shutdown();
            System.out.println("ScheduledExecutorService shutdown initiated.");
        }, 15, TimeUnit.SECONDS);
    }
}
