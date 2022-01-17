package com.nishchay.thread.synchronize;

public class TimeBomb extends Thread {

	public static void main(String[] s) throws InterruptedException {

		System.out.println("Starting 10 second count down... ");

		Object lock = new Object();
		TimeBomb timer = new TimeBomb(lock);
		timer.start();

		synchronized (lock) {
			while (!timer.childDone) {
				lock.wait();
			}
		}
		System.out.println("Boom!!!");

	}

	private String[] timeStr = { "Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine" };
	private final Object lock;
	private boolean childDone;

	private TimeBomb(Object lock) {
		super();
		this.lock = lock;
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
		for (int i = 9; i >= 0; i--) {
			try {
				System.out.println(timeStr[i]);
				Thread.sleep(1000);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}

}