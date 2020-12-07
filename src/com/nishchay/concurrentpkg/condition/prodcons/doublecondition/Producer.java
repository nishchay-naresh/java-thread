package com.nishchay.concurrentpkg.condition.prodcons.doublecondition;

public class Producer implements Runnable {

    private SharedBuffer sharedBuffer;

    public Producer(SharedBuffer sharedBuffer) {
        this.sharedBuffer = sharedBuffer;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            sharedBuffer.put(i);
            System.out.println(Thread.currentThread().getName() + " produces: " + i);
        }
    }
}