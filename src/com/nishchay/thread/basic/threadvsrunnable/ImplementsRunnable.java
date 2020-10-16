package com.nishchay.thread.basic.threadvsrunnable;

public class ImplementsRunnable implements Runnable {

	private int counter = 0;

	public void run() {
		counter++;
		System.out.println(Thread.currentThread().getName() + " ImplementsRunnable : Counter : " + counter);
	}
}
