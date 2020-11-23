package com.nishchay.concurrentpkg.condition.pc.doublecondition;

public class Consumer implements Runnable {

    private SharedBuffer sharedBuffer;

    public Consumer(SharedBuffer sharedBuffer) {
        this.sharedBuffer = sharedBuffer;
    }

    @Override
    public void run() {
        int data;
        for (int i = 1; i <= 10; i++) {
            data = sharedBuffer.take();
            System.out.println(Thread.currentThread().getName() + " consumes: " + data);
        }
    }
}