package com.nishchay.thread.synchronize.readwrite;

public class MyThread extends Thread {

    Shared obj;

    public MyThread(Shared obj) {
        this.obj = obj;
    }

    public void run() {
        obj.incr();
    }
}

