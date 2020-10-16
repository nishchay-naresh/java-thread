package com.nishchay.thread.basic.join;

public class Add100ThreadJoin {

    public static void main(String[] args) {

        System.out.println("---------------Main started---------------");

        Thread t1 = new Thread(new Sum100Task());
        t1.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("---------------Main Ended---------------");
    }
}


