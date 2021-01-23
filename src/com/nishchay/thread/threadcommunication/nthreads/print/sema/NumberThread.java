package com.nishchay.thread.threadcommunication.nthreads.print.sema;

import java.util.concurrent.Semaphore;

public class NumberThread implements Runnable {

	private int value;
	private Semaphore currentLock;
	private Semaphore nextLock;

	NumberThread(int value, Semaphore curr, Semaphore next) {
		this.value = value;
		this.currentLock = curr;
		this.nextLock = next;
	}

	@Override
	public void run() {

		for (int i = 1; i <= 5; i++) {
			try {
				currentLock.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " : " + value);
			nextLock.release();
		}
	}

}