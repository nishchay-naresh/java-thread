package com.nishchay.thread.threadcommunication.prodcons.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProdConsBlockingQueue {

	public static void main(String[] args) {

		final int BUFF_SIZE = 5;
		BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(BUFF_SIZE);

		Thread tp = new Thread(new ProducerBQ(blockingQueue), "Producer Thread");
		Thread tc = new Thread(new ConsumerBQ(blockingQueue), "Consumer Thread");

		tp.start();
		tc.start();

	}
}
