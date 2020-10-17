package com.nishchay.concurrentpkg.pool.fixedpoolcallable;

import java.util.concurrent.Callable;

public class SumJob implements Callable<Integer> {

    private int start;
    private int end;

    public SumJob(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Integer call() throws Exception {

        System.out.printf("%n thread - %s , Added the numbers between %d and %d,", Thread.currentThread().getName(), start, end);
        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum += i;
        }
        return sum;
    }
}
