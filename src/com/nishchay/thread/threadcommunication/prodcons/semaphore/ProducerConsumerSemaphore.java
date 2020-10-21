package com.nishchay.thread.threadcommunication.prodcons.semaphore;

public class ProducerConsumerSemaphore {

	public static void main(String[] args) {

		SharedObject q = new SharedObject();

		new Thread(new Producer(q), "Producer Thread -").start();
		new Thread(new Consumer(q), "Consumer Thread -").start();
	}
}