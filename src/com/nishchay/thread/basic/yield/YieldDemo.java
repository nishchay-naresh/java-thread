package com.nishchay.thread.basic.yield;

/*
* Thread.yield();  -  only method who doesnt throw checked exception - InterruptedException
* stopping current execution thread and giving a chance to other threads to get the cpu
*/
public class YieldDemo {

    public static void main(String[] args) {

        Thread t =  new Thread(
                () -> {
                    System.out.println("start");
                    Thread.yield();
                    System.out.println("end");
                });

        t.start();

    }

}
