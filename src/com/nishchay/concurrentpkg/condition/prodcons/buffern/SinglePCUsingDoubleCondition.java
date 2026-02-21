package com.nishchay.concurrentpkg.condition.prodcons.buffern;

public class SinglePCUsingDoubleCondition {
    public static final int LIMIT = 10;

    public static void main(String[] args) {

        final int BUFFER_SIZE = 3;
        BufferWithTwoCondition buffer = new BufferWithTwoCondition(BUFFER_SIZE);

        new Thread(() -> produceTask(buffer), "Producer Thread").start();
        new Thread(() -> consumeTask(buffer), "Consumer Thread").start();
    }

    /*
     * instead of creating a new class for thread implementation, putting thread logic in a method
     * later invoking these methods thought threads created using lambdas
     * */
    public static void produceTask(BufferWithTwoCondition buffer) {
        for (int i = 1; i <= LIMIT; i++) {
            buffer.put(i);
            System.out.println(Thread.currentThread().getName() + " produces:" + i);
        }
    }

    public static void consumeTask(BufferWithTwoCondition buffer) {
        int value;
        for (int i = 1; i <= LIMIT; i++) {
            value = buffer.take();
            System.out.println(Thread.currentThread().getName() + " consume :" + value);
        }
    }
}



