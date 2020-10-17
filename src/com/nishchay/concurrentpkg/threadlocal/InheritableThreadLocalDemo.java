package com.nishchay.concurrentpkg.threadlocal;

public class InheritableThreadLocalDemo {

    public static void main(String[] args) {
        ParentThread parentThread =  new ParentThread();
        parentThread.start();
    }
}
