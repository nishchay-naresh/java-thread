package com.nishchay.concurrentpkg.pool.ownthreadpool;

import static com.nishchay.Utils.sleep0;

public class ThreadPoolDemo {

	public static void main(String[] args) throws Exception {

		ThreadPool pool = new ThreadPool(10, 10);

		// now lets submit task
		pool.submitTask(new Runnable() {

			@Override
			public void run() {
				System.out.println("Task 1 executed by " + Thread.currentThread().getName());
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					System.out.println("Task1 interrupted by " + Thread.currentThread().getName());
				}
				System.out.println("Task 1 Completed....");
			}
		});

		pool.submitTask(new Runnable() {

			@Override
			public void run() {
				System.out.println("Task 2 executed by " + Thread.currentThread().getName());
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					System.out.println("Task 2 interrupted by " + Thread.currentThread().getName());
				}
				System.out.println("Task 2 Completed....");
			}
		});

		sleep0(5000);
		pool.shutDown();
/*
		pool.submitTask(new Runnable() {

			@Override
			public void run() {
				System.out.println("Task 3 executed by " + Thread.currentThread().getName());
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					System.out.println("Task 3 interrupted by " + Thread.currentThread().getName());
				}
				System.out.println("Task 3 Completed....");
			}
		});

*/
	}

}
