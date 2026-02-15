package com.nishchay;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class Utils {

    /**
     * A common util method to let wait current thread for the given object lock
     *
     * @param lockObject to keep the method as static, passing this(current objet) reference here
     */
    public static synchronized void wait0(Object lockObject){
        try {
            lockObject.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * A common util method to let current thread for given duration of millis
     *
     * @param millis int in the form of millisecond
     */
    public static void sleep0(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * A common util method to compute a sum of 1 to 100
     */
    public static void sum100() {
        int sum = IntStream.rangeClosed(1, 100).sum();
        System.out.println("sum of 100 = " + sum);
    }

    /**
     * A common util method to extract the result from Future
     *
     * @param future Future<V>
     */
    public static <V> V extractFuture(Future<V> future) {
        try {
            return future.get();
        } catch (InterruptedException e) {
            // Restore interrupt status and rethrow as runtime exception
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread interrupted while waiting for future result", e);
        } catch (ExecutionException e) {
            // Wrap the cause in a runtime exception
            throw new RuntimeException("Exception while executing future task", e.getCause());
        }
    }

}
