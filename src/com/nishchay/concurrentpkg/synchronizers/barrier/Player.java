package com.nishchay.concurrentpkg.synchronizers.barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Player implements Runnable {

    private String name;
    private CyclicBarrier barrier;

    public Player(String name, CyclicBarrier barrier) {
        this.name = name;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        System.out.printf("thread - %s , Player - %s has active, Waiting for others players to join %n",
                Thread.currentThread().getName(), name);
        try {
            barrier.await();
        } catch (BrokenBarrierException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("thread - %s , Player - %s has started playing game now %n",
                Thread.currentThread().getName(), name);
    }
}