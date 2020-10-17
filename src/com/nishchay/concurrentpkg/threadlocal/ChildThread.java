package com.nishchay.concurrentpkg.threadlocal;

public class ChildThread extends Thread {
    @Override
    public void run() {
        System.out.println("ChildThreadValue - " +  ParentThread.thrdLocal.get());
    }
}