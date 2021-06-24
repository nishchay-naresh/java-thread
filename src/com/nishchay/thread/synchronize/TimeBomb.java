package com.nishchay.thread.synchronize;

public class TimeBomb extends Thread {

	private String[] timeStr = { "Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine" };
	private Object lock;
	public boolean childDone;

	public TimeBomb(Object lock) {
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

	public void countDown() {
		for (int i = 9; i >= 0; i--) {
			try {
				System.out.println(timeStr[i]);
				Thread.sleep(1000);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}

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

}