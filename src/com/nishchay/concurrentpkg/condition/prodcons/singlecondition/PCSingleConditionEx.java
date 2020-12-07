package com.nishchay.concurrentpkg.condition.prodcons.singlecondition;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PCSingleConditionEx {

    public static void main(String[] args) {

        final int BUFFER_SIZE = 3;
        List<Integer> queue = new ArrayList<>(BUFFER_SIZE);

        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Producer(queue, BUFFER_SIZE, lock, condition).start();
        new Consumer(queue, lock, condition).start();

    }
}



/*
 * O/P =>
 * Produced : 1
 * Produced : 2
 * Produced : 3
 * Consumed : 1
 * Consumed : 2
 * Consumed : 3
 * Produced : 4
 * Produced : 5
 * Produced : 6
 * Consumed : 4
 * Consumed : 5
 * Consumed : 6
 * Produced : 7
 * Produced : 8
 * Produced : 9
 * Consumed : 7
 * Consumed : 8
 * Consumed : 9
 * Produced : 10
 * Consumed : 10
 *
 * */