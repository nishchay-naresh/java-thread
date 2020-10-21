package com.nishchay.thread.threadcommunication.prodcons.semaphore.another;

import java.util.concurrent.Semaphore;

/**
 * Consumer Class.
 */
class Consumer1 implements Runnable {

	private Semaphore semaphoreConsumer;
	private Semaphore semaphoreProducer;

	public Consumer1(Semaphore semaphoreConsumer, Semaphore semaphoreProducer) {
		this.semaphoreConsumer = semaphoreConsumer;
		this.semaphoreProducer = semaphoreProducer;
	}

	public void run() {

		for (int i = 1; i <= 10; i++) {
			try {
				semaphoreConsumer.acquire();
				System.out.println(Thread.currentThread().getName() + " Consumed : " + i);
				semaphoreProducer.release();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}