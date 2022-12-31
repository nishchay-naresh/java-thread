package com.nishchay.thread.synchronize;

import static com.nishchay.Utils.sleep0;

public class TimeBomb extends Thread {

	private static final Object lock = new Object();

	private boolean childDone;

	private TimeBomb() {
		super();
		this.childDone = false;
	}

	public void run() {
		synchronized (lock) {
			countDown();
			this.childDone=true;
			lock.notify();
		}
	}

	private void countDown() {
		String[] timeStr = { "Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine" };

		for (int i = 9; i >= 0; i--) {
			System.out.println(timeStr[i]);
			sleep0(1000);
		}
	}

	public static void main(String[] s) throws InterruptedException {

		System.out.println("Starting 10 second count down... ");

		TimeBomb timer = new TimeBomb();
		timer.start();

		synchronized (lock) {
			while (!timer.childDone) {
				lock.wait();
			}
		}
		System.out.println("Boom!!!");

	}

}