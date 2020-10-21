package com.nishchay.thread.threadcommunication.prodcons.semaphore;

class Consumer implements Runnable {

	private SharedObject bq;

	public Consumer(SharedObject bq) {
		this.bq = bq;
	}

	public void run() {
		for (int i = 1; i <= 10; i++)
			bq.get();
	}
}