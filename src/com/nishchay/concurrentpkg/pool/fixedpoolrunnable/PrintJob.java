package com.nishchay.concurrentpkg.pool.fixedpoolrunnable;

class PrintJob implements Runnable{

    private String name;

    public PrintJob(String name) {
        this.name = name;
    }

    @Override
    public void run() {

        System.out.println(name + " Job started by thread : " + Thread.currentThread().getName());
        try {
            Thread.sleep(2 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " Job completed by thread : " + Thread.currentThread().getName());
    }
}
