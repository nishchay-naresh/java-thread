package com.nishchay.thread.basic.setname;

public class NamedTask implements Runnable {

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println( Thread.currentThread().getName() + " is counting - " + i);

        }
    }

    // Exception in thread "User Thread" java.lang.ArithmeticException: / by zero
/*    public void run() {
        System.out.println(10/0);
    }
*/

}