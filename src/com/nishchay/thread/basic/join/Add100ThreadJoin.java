package com.nishchay.thread.basic.join;

import java.util.stream.IntStream;

public class Add100ThreadJoin {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("----Main started----");
        Thread t1 = new Thread(() -> sum100());
        t1.start();
        t1.join();
        System.out.println("----Main Ended----");
    }

    private static void sum100() {
        int sum = IntStream.rangeClosed(1, 100).sum();
        System.out.println("sum of 100 = " + sum);
    }
}

/*
 * O/P =>
 * ----Main started----
 * sum of 100 = 5050
 * ----Main Ended----
 *
 * */
