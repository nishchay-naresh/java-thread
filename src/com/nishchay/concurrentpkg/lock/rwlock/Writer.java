package com.nishchay.concurrentpkg.lock.rwlock;

import java.util.Random;

import static com.nishchay.Utils.sleep0;

/**
 * Writer.java
 * This thread randomly adds an element to a shared data structure
 */
public class Writer extends Thread {
    private final ReadWriteList<Integer> sharedList;
 
    public Writer(ReadWriteList<Integer> sharedList) {
        this.sharedList = sharedList;
    }
 
    public void run() {
        Random random = new Random();
        int number = random.nextInt(100);
        sharedList.add(number);

        sleep0(100);
        System.out.println(getName() + " -> put: " + number);
    }
}