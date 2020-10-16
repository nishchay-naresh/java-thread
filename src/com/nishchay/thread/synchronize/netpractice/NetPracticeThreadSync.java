package com.nishchay.thread.synchronize.netpractice;

public class NetPracticeThreadSync {

    public static void main(String[] args) {

        //share same copy of shared object across multiple threads to achieve synchronisation
        Pitch pitch = new Pitch();

        Thread t1 = new Thread(new PitchAllocate(pitch,"Dhoni"), "Thread 1");
        t1.start();

        Thread t2 = new Thread(new PitchAllocate(pitch,"Yuvraj"), "Thread 2");
        t2.start();

        Thread t3 = new Thread(new PitchAllocate(pitch,"Kohli"), "Thread 3");
        t3.start();
    }
}




