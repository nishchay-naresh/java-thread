package com.nishchay.concurrentpkg.pool.fixedpoolrunnable;

import static com.nishchay.Utils.sleep0;

public class PrintJob implements Runnable{

    private final String name;

    public PrintJob(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + " Job started by thread : " + Thread.currentThread().getName());
        sleep0(2 * 1000);
        System.out.println(name + " Job completed by thread : " + Thread.currentThread().getName());
    }
}
