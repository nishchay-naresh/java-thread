package com.nishchay;

public class Utils {

    /**
     * A common util method to let wait current thread for the given object lock
     *
     * @param lockObject to keep the method as static , passing this(current objet) reference here
     */
    public static void wait0(Object lockObject){
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

}
