package com.nishchay.thread.exception;

public class ExceptionInThread {

    public static void main(String[] args) {

        Runnable taskThrowException = () -> {
            System.out.println(Thread.currentThread().getName() + " throwing exception from run() method");
            throw new RuntimeException("Boom");
        };

        // execute below methods one by one, by commenting others to understand the output better
        noHandler(taskThrowException);
        withLocalHandler(taskThrowException);
        withLocalHandler2Threads(taskThrowException);
        withGlobalHandler(taskThrowException);
    }

    /*
     * Thread.UncaughtExceptionHandler is a JVM-level safety net that lets us capture and react to uncaught runtime exceptions before a thread terminates,
     * which is critical for observability in production systems.
     *
     *	How it works (Flow)
     *		1.	A thread throws exception
     *		2.	No try-catch handles it
     *		3.	JVM looks for:
     *			-	thread-specific handler
     *			-	else thread-group handler
     *			-	else default handler
     *		4.	Handler’s uncaughtException(Thread t, Throwable e) is invoked
     *		5.	Thread dies
     *
     *
     * O/P =>
     * Thread-0 throwing exception from run() method
     * Exception in thread "Thread-0" java.lang.RuntimeException
     *
     * */
    private static void noHandler(Runnable r) {
        Thread t = new Thread(r);
        t.start();
    }

    /*
     * One can set the exception handler at these 2 levels:
     *      1. Setting a Global (Default) Handler - At Thread class level applicable for all threads
     *      2. Setting a Local Handler to a Thread - to the thread reference, come in to play in case of exception
     *
     *
     * Setting a Local Handler to a Thread
     *
     * O/P =>
     * Thread-0 throwing exception from run() method
     * Thread Thread-0 crashed: Boom
     *
     * */
    private static void withLocalHandler(Runnable r) {
        Thread.UncaughtExceptionHandler handler =
                (thread, ex) -> System.err.println("Thread " + thread.getName() + " crashed: " + ex.getMessage());

        Thread t = new Thread(r);
        // setting up a handler in case of exception
        t.setUncaughtExceptionHandler(handler);
        t.start();
    }

    /*
     *
     * O/P =>
     * Thread-0 throwing exception from run() method
     * Thread-1 throwing exception from run() method
     * Handler1: Thread Thread-0 crashed: Boom
     * Handler2: Thread Thread-1 crashed: Boom
     *
     * */
    public static void withLocalHandler2Threads(Runnable r) {
        Thread.UncaughtExceptionHandler handler1 =
                (thread, ex) -> System.err.println("Handler1 : Thread " + thread.getName() + " crashed: " + ex.getMessage());

        Thread.UncaughtExceptionHandler handler2 =
                (thread, ex) -> System.err.println("Handler2 : Thread " + thread.getName() + " crashed: " + ex.getMessage());

        Thread t1 = new Thread(r);
        t1.setUncaughtExceptionHandler(handler1);
        t1.start();

        Thread t2 = new Thread(r);
        t2.setUncaughtExceptionHandler(handler2);
        t2.start();
    }

    /*
     *  Here we are setting up a Global (Default) Handler - At Thread class level applicable for all threads
     *  It will handle all UncaughtException for all the threads
     *
     * O/P =>
     * Thread-0 throwing exception from run() method
     * Thread-3 throwing exception from run() method
     * Thread-2 throwing exception from run() method
     * Thread-1 throwing exception from run() method
     * Thread-4 throwing exception from run() method
     * GenericHandler : Thread Thread-0 crashed: Boom
     * GenericHandler : Thread Thread-3 crashed: Boom
     * GenericHandler : Thread Thread-2 crashed: Boom
     * GenericHandler : Thread Thread-1 crashed: Boom
     * GenericHandler : Thread Thread-4 crashed: Boom
     *
     * */
    private static void withGlobalHandler(Runnable r) {

        Thread.UncaughtExceptionHandler genericHandler =
                (thread, ex) -> System.err.println("GenericHandler : Thread " + thread.getName() + " crashed: " + ex.getMessage());
        // Setting a global UncaughtException Handler, At Thread class level
        Thread.setDefaultUncaughtExceptionHandler(genericHandler);

        for (int i = 1; i <= 5; i++) {
            Thread thread = new Thread(r);
            thread.start();
        }
    }
}
