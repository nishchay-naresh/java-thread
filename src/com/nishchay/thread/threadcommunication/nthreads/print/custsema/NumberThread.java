package com.nishchay.thread.threadcommunication.nthreads.print.custsema;

public class NumberThread implements Runnable {

	private int value;
	private Permission current;
	private Permission next;

	NumberThread(int value, Permission current, Permission next) {
		this.value = value;
		this.current = current;
		this.next = next;
	}

	@Override
	public void run() {

		for (int i = 1; i <= 5; i++) {

			synchronized (current) {
				while (!current.getPermission()) {
					try {
						current.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				current.revokePermission();
			}

			System.out.println(Thread.currentThread().getName() + " : " + value);

			synchronized (next) {
				next.setPermission();
				next.notifyAll();
			}

		}
	}

}