package com.nishchay.concurrentpkg.lock.rwlock;

import java.util.Random;

import static com.nishchay.Utils.sleep0;

/**
 * Reader.java
 * This thread randomly read an element from a shared data structure
 */
public class Reader extends Thread {
    private final ReadWriteList<Integer> sharedList;
 
    public Reader(ReadWriteList<Integer> sharedList) {
        this.sharedList = sharedList;
    }
 
    public void run() {
        Random random = new Random();
        int index = random.nextInt(sharedList.size());
        Integer number = sharedList.get(index);
 
        System.out.println(getName() + " -> get: " + number);

        sleep0(100);
    }
}