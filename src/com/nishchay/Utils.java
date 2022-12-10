package com.nishchay;

public class Utils {

    public static void wait0(Object lockObject){
        try {
            lockObject.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleep0(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
