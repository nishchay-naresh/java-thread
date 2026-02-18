package com.nishchay.thread.threadcommunication.a02oddeven;

import java.util.concurrent.Semaphore;

/*
 *
 *
 *	Suppose you are given the following code:
 *
 *	class FooBar {
 *	  public void foo() {
 *	    for (int i = 0; i < n; i++) {
 *	      print("foo");
 *	    }
 *	  }
 *
 *	  public void bar() {
 *	    for (int i = 0; i < n; i++) {
 *	      print("bar");
 *	    }
 *	  }
 *	}
 *	The same instance of FooBar will be passed to two different threads:
 *
 *	thread A will call foo(), while
 *	thread B will call bar().
 *	Modify the given program to output "foobar" n times.
 *
 *
 * https://leetcode.com/problems/print-foobar-alternately/description/
 *
 * Printing "foo" and "bar" text by two threads using semaphore
 *
 * Two threads Uses wait() and notify() and printing in below fashion:
 * Foo Thread → print foo, foo, foo ... 10 times
 * Bar Thread → print bar, bar, bar ... 10 times
 *
 * */
public class ZFooBarSemaphore {

    public static final int LIMIT = 10;

    public static void main(String[] args) {
        Semaphore fooSemaphore = new Semaphore(1);
        Semaphore barSemaphore = new Semaphore(0);
        new Thread(() -> printFooTask(fooSemaphore, barSemaphore), "Foo Thread").start();
        new Thread(() -> printBarTask(barSemaphore, fooSemaphore), "Bar Thread").start();
    }

    public static void printFooTask(Semaphore fooSemaphore, Semaphore barSemaphore) {
        for (int i = 1; i <= LIMIT; i++) {
            try {
                fooSemaphore.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " - " + "foo");
            barSemaphore.release();
        }
    }

    public static void printBarTask(Semaphore barSemaphore, Semaphore fooSemaphore) {
        for (int i = 1; i <= LIMIT; i++) {
            try {
                barSemaphore.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " - " + "bar");
            fooSemaphore.release();
        }
    }
}