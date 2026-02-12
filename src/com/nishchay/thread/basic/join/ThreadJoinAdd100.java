package com.nishchay.thread.basic.join;

import com.nishchay.Utils;

public class ThreadJoinAdd100 {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("----Main started----");
        Thread t1 = new Thread(Utils::sum100);
        t1.start();
        t1.join();
        System.out.println("----Main Ended----");
    }
}

/*
 * O/P =>
 * ----Main started----
 * sum of 100 = 5050
 * ----Main Ended----
 *
 * */
