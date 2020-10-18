package com.nishchay.concurrentpkg.synchronizers.semaphore;

public class Printer {

    private String name;

    public Printer(String name) {
        this.name = name;
    }

    public void usePrinter(){
        System.out.printf("%s is currently been used - %s %n", name, Thread.currentThread().getName());
    }
}
