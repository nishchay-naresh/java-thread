package com.nishchay.concurrentpkg.threadlocal;

public class ParentThread extends Thread {

//    public static ThreadLocal<String> thrdLocal = new ThreadLocal<>();
    public static InheritableThreadLocal<String> thrdLocal = new InheritableThreadLocal(){
        protected String childValue(String parentValue) {
            return "child value";
        }
    };


    @Override
    public void run(){
        thrdLocal.set("parent value");
        System.out.println("ParentThreadValue - " + thrdLocal.get());
        ChildThread childThread = new ChildThread();
        childThread.start();
    }

}
