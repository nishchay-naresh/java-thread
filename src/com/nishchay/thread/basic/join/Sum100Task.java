package com.nishchay.thread.basic.join;

import java.util.stream.IntStream;

public class Sum100Task implements Runnable {

    @Override
    public void run() {
        sum100();
    }

    private void sum100() {
        int sum = IntStream.rangeClosed(1, 100).sum();
        System.out.println("sum of 100 = " + sum);
    }

}