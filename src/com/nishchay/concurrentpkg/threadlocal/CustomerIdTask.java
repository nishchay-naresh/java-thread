package com.nishchay.concurrentpkg.threadlocal;

// maintaining a threadLocal counter for each of the thread
public class CustomerIdTask implements Runnable {

    private Integer custId = 0;
/*    private ThreadLocal<Integer> thrdLocal =  new ThreadLocal(){
        public Integer initialValue(){
            return ++custId;
        }
    };*/

    private ThreadLocal<Integer> thrdLocal = ThreadLocal.withInitial(() -> ++custId);

    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName() +" executing with custId : " + thrdLocal.get());
    }

    public static void main(String[] args) {
        new Thread(new CustomerIdTask(), "Thread -1").start();
        new Thread(new CustomerIdTask(), "Thread -2").start();
        new Thread(new CustomerIdTask(), "Thread -3").start();
        new Thread(new CustomerIdTask(), "Thread -4").start();

    }
}