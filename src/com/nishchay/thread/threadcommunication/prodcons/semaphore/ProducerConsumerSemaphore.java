package com.nishchay.thread.threadcommunication.prodcons.semaphore;

public class ProducerConsumerSemaphore {

	public static void main(String[] args) {

		SharedObject sharedObject = new SharedObject();

		new Thread(new Producer(sharedObject), "Producer Thread -").start();
		new Thread(new Consumer(sharedObject), "Consumer Thread -").start();

	}
}