package com.nishchay.thread.basic.a02ops;

public class ThreadState {

    public static void main(String[] args) {

        for(Thread.State state : Thread.State.values()){
            System.out.println(state);
        }

    }
}
