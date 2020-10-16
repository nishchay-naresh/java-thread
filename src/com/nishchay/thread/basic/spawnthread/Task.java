package com.nishchay.thread.basic.spawnthread;

public class Task implements Runnable {

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("User Thread is counting - " + i);
        }
    }

}