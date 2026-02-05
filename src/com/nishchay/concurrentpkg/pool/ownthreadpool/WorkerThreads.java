package com.nishchay.concurrentpkg.pool.ownthreadpool;

import java.util.concurrent.BlockingQueue;

/* 
 * Worker thread: offer two functionality
 * 1. run() : each thread will take a task from BQ,(until its become empty) and get it executed by a worker threads of pool
 * 2. doStop() : stop a particular worker thread based on isStopped flag
 * 					
 */

public class WorkerThreads extends Thread {

	private final BlockingQueue<Runnable> taskQueue;
	private boolean isStopped = false;

	public WorkerThreads(String tName, BlockingQueue<Runnable> queue) {
		this.setName(tName);
		taskQueue = queue;
	}

	public void run() {

		while (!isStopped()) {
			try {
				Runnable runnable = taskQueue.take();
				runnable.run();
			} catch (Exception e) {
				// log or otherwise report exception,
				// but keep pool thread alive.
				System.out.println(this.getName() + " interrupted");
			}
		}
	}

	public synchronized void doStop() {
		isStopped = true;
		this.interrupt(); // break pool thread out of dequeue() call.

	}

	public synchronized boolean isStopped() {
		return isStopped;
	}
}