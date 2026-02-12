package com.nishchay.thread.basic.demon;


import com.nishchay.Utils;

/*
 *  A daemon thread is a low-priority background thread that runs only as long as at least one user thread is alive.
 *
 *  A daemon thread in Java is a background thread that supports user threads.
 *  The JVM does not wait for daemon threads to complete execution.
 *  When all user threads finish, the JVM terminates daemon threads automatically.
 *
 * Main thread is a user thread - not daemon
 *
 * Use cases for a daemon thread:
 * 	-	Logging
 * 	-	Monitoring
 * 	-	Cleanup tasks
 * 	-	Cache eviction
 * 	-	Heartbeat checks
 *
 * */
public class DaemonThread {

    public static void main(String[] args) {

        demonThreadEx();
        // execute below methods one by one, by commenting others to understand the output better
        jvmWaitsForUserThread();
        jvmExitForDemonThread();
    }

    /*
     * ❌ You cannot make a thread daemon after it starts
     * t.start();
     * t.setDaemon(true); // throws - java.lang.IllegalThreadStateException
     *
     * ✅ Correct order
     * t.setDaemon(true);
     * t.start();
    *
    * */
    private static void demonThreadEx() {
        Thread t1 = new Thread(() -> System.out.println("java"));
        t1.start();
        // t1.setDaemon(true); // throws - java.lang.IllegalThreadStateException
    }

    /*
    * JVM will keep running forever, because a user thread is alive
    * making it finite - breaking an infinite loop after 15 sec
    * */
    private static void jvmWaitsForUserThread() {
        Runnable infiniteTask = () -> {
            long start = System.currentTimeMillis();
            while (true) {
                System.out.println("User thread running");
                Utils.sleep0(1000);
                // making it finite - breaking an infinite loop after 15 sec
                long secs = (System.currentTimeMillis() - start) / 1000;
                if (secs > 10) {
                    break;
                }
            }
        };

        Thread t = new Thread(infiniteTask);
        t.start();
    }

    /*
     * JVM exits immediately after main thread ends
     * Daemon thread is killed automatically
     *
     * op =>
     * Daemon thread running
     * Daemon thread running
     * Daemon thread running
     * Main thread exiting
     *
     * */
    private static void jvmExitForDemonThread() {
        Runnable infiniteTask = () -> {
            while (true) {
                System.out.println("Daemon thread running");
                Utils.sleep0(1000);
            }
        };

        Thread thread = new Thread(infiniteTask);
        thread.setDaemon(true);
        thread.start();

        Utils.sleep0(3 * 1000);
        System.out.println("Main thread exiting");
    }
}


