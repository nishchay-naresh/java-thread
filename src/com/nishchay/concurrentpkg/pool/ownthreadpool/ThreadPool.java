package com.nishchay.concurrentpkg.pool.ownthreadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/* 
 *  LIFECYCLE: Construction -> submitTask (for execution) - >  shutDown
 *		t1[]		t2[]		t3[]		pool of size : 3	
 * 			\		|		/
 * 			[][][][][][] BlockingQueue
 * 	sharing same BQ across all running threads
 * 
 * Construction:
 * 	-	instantiating a BQ as per maxNoOfTasks
 * 	-	creating no of worker thread (sharing the same BQ across all threads), as per noOfThreads
 * 	-	start all the worker threads
 * 					
 */
public class ThreadPool {

	private final BlockingQueue<Runnable> taskQueue;
	private final List<WorkerThreads> threads = new ArrayList<>();
	private boolean isStopped = false;

	public ThreadPool(int noOfThreads, int maxNoOfTasks) {
		taskQueue = new ArrayBlockingQueue<>(maxNoOfTasks);

		for (int i = 1; i <= noOfThreads; i++) {
			threads.add(new WorkerThreads("thread - "+i,taskQueue));
		}
		for (WorkerThreads thread : threads) {
			thread.start();
		}
	}

	public synchronized void submitTask(Runnable task) throws Exception {
		if (this.isStopped)
			throw new IllegalStateException("ThreadPool is stopped");

		this.taskQueue.put(task);
	}

	// implemented in shutdownNow() manner
	public synchronized void shutDown() {
		this.isStopped = true;
		for (WorkerThreads thread : threads) {
			thread.doStop();
		}
	}
	
//	ExecutorService es = Executors.newFixedThreadPool(4);
//	es.shutdownNow();
}

/*
How to Create a Thread pool
		- Create n threads. Call them as workers.
		- For each worker, Implement run method with two constraints
			1. Wait for a task on queue
			2. Execute the task and go back to waiting state.
		- Expose addTask method that adds a task to that task Queue.

*/