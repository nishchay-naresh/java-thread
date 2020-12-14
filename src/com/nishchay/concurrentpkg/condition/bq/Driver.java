package com.nishchay.concurrentpkg.condition.bq;

/*
 * Created two static methods corresponds to producer task & consumer task
 * then spawning two thread - prodThread, consThread using lambda exp
 *
 * */
public class Driver {

    public static void main(String[] args) {

        final int BUFFER_SIZE = 5;
        BlockingQueue<Integer> queue = new BlockingQueue<>(BUFFER_SIZE);

        Thread prodThread = new Thread(() -> produce(queue), "Producer Thread");
        prodThread.start();

        Thread consThread = new Thread(() -> consume(queue), "Consumer Thread");
        consThread.start();
    }


    public static void produce(BlockingQueue<Integer> bq) {
        for (int i = 1; i <= 10; i++) {
            try {
                bq.put(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " produces: " + i);
        }
    }

    public static void consume(BlockingQueue<Integer> bq) {
        Integer data = 0;
        for (int i = 1; i <= 10; i++) {
            try {
                data = bq.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " consumes: " + data);
        }
    }
}
