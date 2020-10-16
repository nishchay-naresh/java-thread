package com.nishchay.thread.basic.threadvsrunnable;

public class ThreadVsRunnable {

    public static void main(String args[]) throws Exception {

        //Multiple threads share the same object.
        ImplementsRunnable rc = new ImplementsRunnable();
        Thread t1 = new Thread(rc);
        t1.start();
        Thread t2 = new Thread(rc);
        t2.start();
        Thread t3 = new Thread(rc);
        t3.start();

        //Creating new instance for every thread access.
        ExtendsThread tc1 = new ExtendsThread();
        tc1.start();
        ExtendsThread tc2 = new ExtendsThread();
        tc2.start();
        ExtendsThread tc3 = new ExtendsThread();
        tc3.start();

    }


}
