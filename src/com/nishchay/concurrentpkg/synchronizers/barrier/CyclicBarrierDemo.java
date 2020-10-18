package com.nishchay.concurrentpkg.synchronizers.barrier;

import java.util.concurrent.CyclicBarrier;

/*
 await() - Waits until all  parties have invoked await() on this barrier.
 Here using CyclicBarrier for waiting all the players to until no of payers need to start the game should arrive
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {


        // execute this task once all threads reaching to the common point
        Thread taskAfterReachingCommonPoint = new Thread(() -> System.out.println("Game has been started now ...!!!!"));

        final int NO_OF_PLAYERS = 4;
        CyclicBarrier barrier  = new CyclicBarrier(NO_OF_PLAYERS, taskAfterReachingCommonPoint);

        new Thread(new Player("Player 1",barrier ), "Thread 1").start();
        new Thread(new Player("Player 2",barrier ), "Thread 2").start();
        new Thread(new Player("Player 3",barrier ), "Thread 3").start();
        new Thread(new Player("Player 4",barrier ), "Thread 4").start();

    }
}
