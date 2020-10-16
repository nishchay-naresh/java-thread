package com.nishchay.thread.basic.state;

public class ThreadState {

    public static void main(String[] args) {

//        One can all be seen by below code  :
        for(Thread.State state : Thread.State.values()){
            System.out.println(state);
        }

    }
}
