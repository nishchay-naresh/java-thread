package com.nishchay.thread.threadcommunication.prodcons.semaphore;

public class Producer implements Runnable {

	private SharedObject bq;

	public Producer(SharedObject bq) {
		this.bq = bq;
	}

	public void run() {
		for (int i = 1; i <= 10; i++)
			bq.put(i);
	}
}