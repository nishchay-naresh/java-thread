package com.nishchay.thread.threadcommunication.foobar;

/*
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
 *
 * TODO
 *
 *	🏁 Summary
 *	Approach			Synchronization Tool		Easy to Implement	Thread-safe			Notes
 *	✅ Semaphore		Semaphore					✅					✅					Clean and readable
 *	Lock + Condition	ReentrantLock, Condition	✅					✅					More flexible
 *	wait/notify			synchronized block			⚠️					✅					Verbose and error-prone
 *
 *	Would you like me to show the synchronized + wait/notify version too (the low-level approach)?
 *
 *
 * */
class FooBar {

    private int n;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
        }
    }
}