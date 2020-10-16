package com.nishchay.thread.basic.threadvsrunnable;

public class ExtendsThread extends Thread {

	private int counter = 0;

	public void run() {
		counter++;
		System.out.println(Thread.currentThread().getName() + " ExtendsThread : Counter : " + counter);
	}
}