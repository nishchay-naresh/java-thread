package com.nishchay.thread.basic.demon;

/*
    Demon thread demo for JVM exit
    if A background running task is set as demon, JVM will exit as soon as it finishes its own task
    Else JVM will also be running , until demon thread is not getting stopped
 */

public class DaemonThread {

    public static void main(String[] args) {

        Thread thrd = new Thread(new DirectoryWatcherTask());

//        If comment below line JVM will not exit until this threads gets finish, which in turns never
//        Once setting this threads as demon, JVM will exit as soon as it finishes main thread task
        thrd.setDaemon(true);
        thrd.start();

        for (int i = 1; i <= 200; i++) {
            System.out.print(" M ");
        }
    }
}



