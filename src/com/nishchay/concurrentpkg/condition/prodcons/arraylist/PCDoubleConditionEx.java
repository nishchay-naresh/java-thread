package com.nishchay.concurrentpkg.condition.prodcons.arraylist;

public class PCDoubleConditionEx {

    public static void main(String[] args) {

        final int BUFFER_SIZE = 3;
        SharedBuffer buffer = new SharedBuffer(BUFFER_SIZE);

        new Thread(new Producer(buffer), "Producer Thread").start();
        new Thread(new Consumer(buffer), "Consumer Thread").start();
    }
}



