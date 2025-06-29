package com.nishchay.thread.basic.demon;



/*
 *
 *	Daemon threads are low-priority threads that conduct supporting tasks, whereas
 *	Non-Daemon threads are high-priority threads that handle particular tasks
 *
 *	the JVM always waits for non-daemon threads to complete their tasks, but not for demon threads
 *
 * if A background running task is set as demon, JVM will exit as soon as it finishes its own task
 * Else JVM will also be running, until demon thread is not getting stopped
 *
 * */

import com.nishchay.Utils;

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

class DirectoryWatcherTask implements Runnable {

    @Override
    public void run() {

        while (true) {
            System.out.println("checking directory for file update ...");
            // Utils.wait0(1 * 1000);
        }
    }
}


