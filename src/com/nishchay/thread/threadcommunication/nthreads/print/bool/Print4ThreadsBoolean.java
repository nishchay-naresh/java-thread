package com.nishchay.thread.threadcommunication.nthreads.print.bool;

public class Print4ThreadsBoolean {

	public static void main(String[] args) {

		SharedObject sharedObject = new SharedObject();

		new Thread(new MyTask(sharedObject, 1), "Thread 1").start();
		new Thread(new MyTask(sharedObject, 2), "Thread 2").start();
		new Thread(new MyTask(sharedObject, 3), "Thread 3").start();
		new Thread(new MyTask(sharedObject, 4), "Thread 4").start();
	}

}

